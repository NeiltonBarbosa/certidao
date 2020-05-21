package br.gov.ro.sefin.contabilidade.certidao.api.model.validation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import br.gov.ro.sefin.contabilidade.certidao.api.model.Interessado;

public class InteressadoGroupSequenceProvider implements DefaultGroupSequenceProvider<Interessado> {

	@Override
	public List<Class<?>> getValidationGroups(Interessado interessado) {
		List<Class<?>> grupos = new ArrayList<>();
		grupos.add(Interessado.class);
		
		if(isContaSelecionada(interessado)) {
			grupos.add(interessado.getTipo().getGrupo());
		}
		
		return grupos;
	}
	
	private boolean isContaSelecionada(Interessado interessado) {
		return interessado != null && interessado.getTipo() != null;
	}

}
