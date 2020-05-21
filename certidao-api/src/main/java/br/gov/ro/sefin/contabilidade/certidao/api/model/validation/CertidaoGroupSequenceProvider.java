package br.gov.ro.sefin.contabilidade.certidao.api.model.validation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import br.gov.ro.sefin.contabilidade.certidao.api.model.Certidao;

public class CertidaoGroupSequenceProvider implements DefaultGroupSequenceProvider<Certidao> {

	@Override
	public List<Class<?>> getValidationGroups(Certidao certidao) {
		List<Class<?>> grupos = new ArrayList<>();
		grupos.add(Certidao.class);
		
		if(isContaSelecionada(certidao)) {
			grupos.add(certidao.getTipo().getGrupo());
		}
		
		return grupos;
	}
	
	private boolean isContaSelecionada(Certidao certidao) {
		return certidao != null && certidao.getTipo() != null;
	}

}
