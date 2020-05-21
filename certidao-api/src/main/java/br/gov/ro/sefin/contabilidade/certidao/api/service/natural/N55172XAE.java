package br.gov.ro.sefin.contabilidade.certidao.api.service.natural;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.softwareag.entirex.aci.Broker;
import com.softwareag.entirex.aci.BrokerException;
import com.softwareag.entirex.aci.RPCService;

import br.gov.ro.sefin.contabilidade.certidao.api.context.ContextProvider;
import br.gov.ro.sefin.contabilidade.certidao.api.model.Certidao;
import br.gov.ro.sefin.contabilidade.certidao.api.model.Interessado;
import br.gov.ro.sefin.contabilidade.certidao.api.model.Pendencia;
import br.gov.ro.sefin.contabilidade.certidao.api.model.SituacaoDocumento;
import br.gov.ro.sefin.contabilidade.certidao.api.model.Status;
import br.gov.ro.sefin.contabilidade.certidao.api.model.Tipo;
import br.gov.ro.sefin.contabilidade.certidao.api.property.CertidaoProperty;
import br.gov.ro.sefin.contabilidade.certidao.api.util.SituacaoPendencia;

/* Emissão de certidão de convênios */
public class N55172XAE extends RPCService {

	public static final String DEFAULT_BROKERID = ContextProvider.getBean(CertidaoProperty.class).getEntirex().getBrokerId();
	public static final String DEFAULT_SERVER = ContextProvider.getBean(CertidaoProperty.class).getEntirex().getServerAddress();
	public static final String DEFAULT_USERID = ContextProvider.getBean(CertidaoProperty.class).getEntirex().getUserId();;

	private String tipo;

	private String cnpj;

	private String razaoSocialConsultada;

	private String razaoSocialEncontrada;

	private String dataHoraEmissao;

	private String codigoControle;

	private String dataValidade;

	private String status;

	private String saldo;

	private String situacaoDocumento;

	private String pendencias;

	private String ultimoRegistro;
	
	private List<Pendencia> listaDePendencias = new ArrayList<>();

	private final static String N5517XAE_FORMAT = // from IDL
			("MA1,0MA14,0MA45,0MA45,0MA19,0MA11,0MA10,0MA1,0MA17,0MA1," + "0MA1020,0MA20,0.");

	public N55172XAE() {
		super(new Broker(DEFAULT_BROKERID, DEFAULT_USERID), DEFAULT_SERVER, "A3312019", true);
	}
	
	public static String getStubVersion() {
		return "EntireX RPC for Java Stub Version=9.12.0.0, Patch Level=332";
	}
	
	public void submit() throws BrokerException {

		enterStub();
		buildHeader(N5517XAE_FORMAT, "12", 1204, 0, "N5517XAE", true, 1140, false, false, 2040);
		if (getVersion() >= 2000) {

			if (tipo == null) {
				super.marshal.addDataA(null, 1);
			} else {
				super.marshal.addDataA(tipo, 1);
			}

			if (cnpj == null) {
				super.marshal.addDataA(null, 14);
			} else {
				super.marshal.addDataA(cnpj, 14);
			}

			if (razaoSocialConsultada == null) {
				super.marshal.addDataA(null, 45);
			} else {
				super.marshal.addDataA(razaoSocialConsultada, 45);
			}

			if (razaoSocialEncontrada == null) {
				super.marshal.addDataA(null, 45);
			} else {
				super.marshal.addDataA(razaoSocialEncontrada, 45);
			}

			if (dataHoraEmissao == null) {
				super.marshal.addDataA(null, 19);
			} else {
				super.marshal.addDataA(dataHoraEmissao, 19);
			}

			if (codigoControle == null) {
				super.marshal.addDataA(null, 11);
			} else {
				super.marshal.addDataA(codigoControle, 11);
			}

			if (dataValidade == null) {
				super.marshal.addDataA(null, 10);
			} else {
				super.marshal.addDataA(dataValidade, 10);
			}

			if (status == null) {
				super.marshal.addDataA(null, 1);
			} else {
				super.marshal.addDataA(status, 1);
			}

			if (saldo == null) {
				super.marshal.addDataA(null, 17);
			} else {
				super.marshal.addDataA(saldo, 17);
			}

			if (situacaoDocumento == null) {
				super.marshal.addDataA(null, 1);
			} else {
				super.marshal.addDataA(situacaoDocumento, 1);
			}

			if (pendencias == null) {
				super.marshal.addDataA(null, 1020);
			} else {
				super.marshal.addDataA(pendencias, 1020);
			}

			if (ultimoRegistro == null) {
				super.marshal.addDataA(null, 20);
			} else {
				super.marshal.addDataA(ultimoRegistro, 20);
			}
		} else {
			super.marshal.addDataA(tipo, 1);
			super.marshal.addDataA(cnpj, 14);
			super.marshal.addDataA(razaoSocialConsultada, 45);
			super.marshal.addDataA(razaoSocialEncontrada, 45);
			super.marshal.addDataA(dataHoraEmissao, 19);
			super.marshal.addDataA(codigoControle, 11);
			super.marshal.addDataA(dataValidade, 10);
			super.marshal.addDataA(status, 1);
			super.marshal.addDataA(saldo, 17);
			super.marshal.addDataA(situacaoDocumento, 1);
			super.marshal.addDataA(pendencias, 1020);
			super.marshal.addDataA(ultimoRegistro, 20);
		}

		callServer();

		if (getVersion() >= 2000) {
			tipo = super.marshal.getDataA(1);
			cnpj = super.marshal.getDataA(14);
			razaoSocialConsultada = super.marshal.getDataA(45);
			razaoSocialEncontrada = super.marshal.getDataA(45);
			dataHoraEmissao = super.marshal.getDataA(19);
			codigoControle = super.marshal.getDataA(11);
			dataValidade = super.marshal.getDataA(10);
			status = super.marshal.getDataA(1);
			saldo = super.marshal.getDataA(17);
			situacaoDocumento = super.marshal.getDataA(1);
			pendencias = super.marshal.getDataA(1020);
			ultimoRegistro = super.marshal.getDataA(20);
		} else {
			tipo = super.marshal.getDataA(1);
			cnpj = super.marshal.getDataA(14);
			razaoSocialConsultada = super.marshal.getDataA(45);
			razaoSocialEncontrada = super.marshal.getDataA(45);
			dataHoraEmissao = super.marshal.getDataA(19);
			codigoControle = super.marshal.getDataA(11);
			dataValidade = super.marshal.getDataA(10);
			status = super.marshal.getDataA(1);
			saldo = super.marshal.getDataA(17);
			situacaoDocumento = super.marshal.getDataA(1);
			pendencias = super.marshal.getDataA(1020);
			ultimoRegistro = super.marshal.getDataA(20);
		}
		leaveStub();
		
		popularPendencias();
		if(!this.ultimoRegistro.trim().isEmpty()) {
			this.pendencias = "";
			this.codigoControle = "";
			submit();
		}

	}

	public static final Method enterStubMethod = getStubMethod("enterClientStub");
	public static final Method leaveStubMethod = getStubMethod("leaveClientStub");

	private static Method getStubMethod(String name) {
		Method method = null;
		try {
			method = RPCService.class.getMethod(name, (Class<?>[]) null);
		} catch (final Exception ex) {
		}
		return method;
	}

	private void enterStub() {
		if (enterStubMethod != null) {
			try {
				enterStubMethod.invoke(this, (Object[]) null);
			} catch (final Exception ex) {
			}
		}
	}

	private void leaveStub() {
		if (leaveStubMethod != null) {
			try {
				leaveStubMethod.invoke(this, (Object[]) null);
			} catch (final Exception ex) {
			}
		}
	}

	public void setFields(Interessado interessado) {
		this.tipo = String.valueOf(interessado.getTipo().ordinal() + 1);
		this.cnpj = interessado.getCpfCnpjSemFormatacao();
		this.razaoSocialConsultada = interessado.getNomeRazaoSocial();
		this.codigoControle = gerarCodigoControle();
		this.dataHoraEmissao = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		this.dataValidade = LocalDate.now().plusDays(29).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	public Certidao getCertidao() {
		Certidao certidao= new Certidao();
		certidao.setTipo(Tipo.values()[Integer.valueOf(this.tipo) - 1]);
		certidao.setCodigoControle(this.codigoControle);
		certidao.setNomeRazaoSocial(this.razaoSocialEncontrada);
		certidao.setCpfCnpj(certidao.getTipo().formatar(this.cnpj));
		certidao.setDataHoraEmissao(LocalDateTime.parse(this.dataHoraEmissao, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
		certidao.setDataValidade(LocalDate.parse(this.dataValidade, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		certidao.setStatus(this.status.equals("9") ? null : Status.values()[Integer.valueOf(this.status)]);
		certidao.setSituacaoDocumento(this.situacaoDocumento.equals(" ") ?
				SituacaoDocumento.INEXISTENTE : this.situacaoDocumento.equals("A") ?
						SituacaoDocumento.ATIVO : SituacaoDocumento.INATIVO);
		certidao.setPendencias(this.listaDePendencias);
		BigDecimal saldoTotal = certidao.getPendencias().stream()
				.map(p -> p.getValor())
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
		certidao.setSaldo(saldoTotal);
		
		return certidao;
	}
	
	public void popularPendencias() {
		if(this.pendencias.trim().length() > 0) {
			Arrays.asList(this.pendencias.trim().split("#"))
				.forEach(p -> {
					List<String> stringList = Arrays.asList(p.trim().split(";"));
					
					Pendencia pend = new Pendencia();
					pend.setConvenio(stringList.get(0));
					pend.setUg(stringList.get(1));
					pend.setData(LocalDate.parse(stringList.get(2), DateTimeFormatter.ofPattern("ddMMyyyy")));
					pend.setSituacao(SituacaoPendencia.situacoes.get(stringList.get(3)));
					pend.setValor(new BigDecimal(stringList.get(4)).divide(new BigDecimal(100)));
					
					listaDePendencias.add(pend);
				});
		}
	}
	
	private String gerarCodigoControle() {
		return UUID.randomUUID().toString().substring(0, 5).toUpperCase()
				+ "-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
	}
}
