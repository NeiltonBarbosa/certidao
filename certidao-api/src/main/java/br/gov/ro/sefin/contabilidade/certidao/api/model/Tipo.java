package br.gov.ro.sefin.contabilidade.certidao.api.model;

import br.gov.ro.sefin.contabilidade.certidao.api.model.validation.group.CnpjGroup;
import br.gov.ro.sefin.contabilidade.certidao.api.model.validation.group.CpfGroup;


public enum Tipo {

	DIARIAS(CpfGroup.class, "Diárias"){
		@Override
		public String formatar(String cpfOuCnpj) {
			return cpfOuCnpj.trim().replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");
		}
	},
	SUP_FUNDOS(CpfGroup.class, "Suprimento de Fundos"){
		@Override
		public String formatar(String cpfOuCnpj) {
			return cpfOuCnpj.trim().replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");
		}
	},
	DIV_RESP(CpfGroup.class, "Diversos Responsáveis"){
		@Override
		public String formatar(String cpfOuCnpj) {
			return cpfOuCnpj.trim().replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");
		}
	},
	CONVENIOS(CnpjGroup.class, "Convênios"){
		@Override
		public String formatar(String cpfOuCnpj) {
			return cpfOuCnpj.trim().replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})", "$1.$2.$3/$4-");
		}
	};
	
	private Class<?> grupo;
	private String descricao;
	
	private Tipo(Class<?> grupo, String descricao) {
		this.grupo = grupo;
		this.descricao = descricao;
	}
	
	public Class<?> getGrupo() { 
		return grupo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public abstract String formatar(String cpfOuCnpj);
}
