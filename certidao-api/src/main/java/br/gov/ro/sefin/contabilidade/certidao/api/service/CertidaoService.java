package br.gov.ro.sefin.contabilidade.certidao.api.service;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.softwareag.entirex.aci.BrokerException;

import br.gov.ro.sefin.contabilidade.certidao.api.model.Certidao;
import br.gov.ro.sefin.contabilidade.certidao.api.model.Interessado;
import br.gov.ro.sefin.contabilidade.certidao.api.model.SituacaoDocumento;
import br.gov.ro.sefin.contabilidade.certidao.api.model.Tipo;
import br.gov.ro.sefin.contabilidade.certidao.api.service.exception.CertidaoNaoEncontradaException;
import br.gov.ro.sefin.contabilidade.certidao.api.service.exception.EntirexException;
import br.gov.ro.sefin.contabilidade.certidao.api.service.exception.InteressadoNaoEncontradoException;
import br.gov.ro.sefin.contabilidade.certidao.api.service.exception.WebServiceSitafeException;
import br.gov.ro.sefin.contabilidade.certidao.api.service.natural.N55172AC;
import br.gov.ro.sefin.contabilidade.certidao.api.service.natural.N55172AD;
import br.gov.ro.sefin.contabilidade.certidao.api.service.natural.N55172XAE;
import lombok.Getter;
import lombok.Setter;

@Service
public class CertidaoService {
	
	private static final Logger logger = LogManager.getLogger(CertidaoService.class);
	
	@Autowired
	private RestTemplate rest;

	public Certidao emitir(Interessado interessado) {
		try {
			Certidao certidao = new Certidao();
			
			if (interessado.getTipo().equals(Tipo.CONVENIOS)) {
				N55172XAE n55172xae = new N55172XAE();
				n55172xae.setFields(interessado);
				n55172xae.submit();
				
				certidao = n55172xae.getCertidao();
			} else {				
				N55172AD n55172ad = new N55172AD();
				n55172ad.setAttributes(interessado);
				n55172ad.submit();
				certidao = n55172ad.getCertidao();
			}
			
			if(certidao.isInteressadoNaoEncontrado()) {
				certidao = buscarNaBaseSitafe(certidao);
			}
			
			
			return certidao;
		} catch (BrokerException e) {
			logger.error(e.getMessage(), e);
			throw new EntirexException("Problema na comunicação com o Entirex");
		}
	}
	
	public Certidao autenticar(Certidao certidao) {
		try {
			N55172AC n55172ac = new N55172AC();
			n55172ac.setCodigoControle(certidao.getCodigoControle());
			n55172ac.submit();
			
			if(!n55172ac.isEncontrado()) {
				throw new CertidaoNaoEncontradaException("Certidão não encontrada. Verifique as informações e tente novamente.");
			}
			
			Certidao certidaoEncontrada = n55172ac.getCertidao();
			compararCertidoes(certidao, certidaoEncontrada);
			
			return certidaoEncontrada;
		} catch (BrokerException e) {
			logger.error(e.getMessage(), e);
			throw new EntirexException("Problema na comunicação com o Entirex");
		}
	}
	
	private void compararCertidoes(Certidao certidao, Certidao certidaoEncontrada) {
//		ZoneOffset oldZone = ZoneOffset.of("+04:00");
//		
//		LocalDateTime dateCompare = LocalDateTime.ofInstant(certidao.getDataHoraEmissao().toInstant(oldZone), ZoneOffset.UTC); // DATA ENVIADA PELO USUÁRIO
//		LocalDateTime date = LocalDateTime.of(certidaoEncontrada.getDataHoraEmissao().toLocalDate(), 
//				LocalTime.of(certidaoEncontrada.getDataHoraEmissao().getHour(), certidaoEncontrada.getDataHoraEmissao().getMinute())); // DATA ORIGINAL DA CERTIDÃO
		
		
		LocalDateTime dateCompare =  certidao.getDataHoraEmissao().withSecond(0); // DATA ENVIADA PELO USUÁRIO
		LocalDateTime date = certidaoEncontrada.getDataHoraEmissao().withSecond(0);  // DATA ORIGINAL DA CERTIDÃO
		
		if(!certidaoEncontrada.getCpfOuCnpjSemFormatacao().equals(certidao.getCpfOuCnpjSemFormatacao())
				|| !date.equals(dateCompare)
				|| !certidaoEncontrada.getTipo().equals(certidao.getTipo())) {
			throw new CertidaoNaoEncontradaException("Certidão não encontrada. Verifique as informações e tente novamente.");
		}
	}

	private Certidao buscarNaBaseSitafe(Certidao certidao) {
		try {
			String url = "https://ms.contabilidade.sefin.ro.gov.br/consulta_documento?" 
					+ "documento=" + certidao.getCpfOuCnpjSemFormatacao()
					+ "&token=f84b65795804b6699b76ecdc55becc8a";
			
			ApiResponse resp = rest.getForObject(url, ApiResponse.class);
			
			if (resp.isNaoEncontrado()) {
				throw new InteressadoNaoEncontradoException("Interessado não encontrado em nossas base de dados.");
			}
			
			certidao.setSituacaoDocumento(SituacaoDocumento.INEXISTENTE);
			certidao.setNomeRazaoSocial(resp.getNome());
			
			return certidao;
			
		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
			throw new WebServiceSitafeException("Não foi possível consumir o informações do SITAFE");
		}
		
	}

	@Getter @Setter
	public static class ApiResponse {
		private Integer cod;
		private String documento;
		private String nome;
		private String aviso;
		
		public boolean isNaoEncontrado() {
			return this.aviso != null;
		}
	}
	
}
