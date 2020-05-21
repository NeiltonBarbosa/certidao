package br.gov.ro.sefin.contabilidade.certidao.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.gov.ro.sefin.contabilidade.certidao.api.model.validation.InteressadoGroupSequenceProvider;
import br.gov.ro.sefin.contabilidade.certidao.api.model.validation.group.CnpjGroup;
import br.gov.ro.sefin.contabilidade.certidao.api.model.validation.group.CpfGroup;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@GroupSequenceProvider(InteressadoGroupSequenceProvider.class)
public class Interessado {

	@NotNull
	private Tipo tipo;
	
	@Size(max = 45)
	private String nomeRazaoSocial;
	
	@NotBlank
	@CPF(groups = CpfGroup.class)
	@CNPJ(groups = CnpjGroup.class)
	private String cpfCnpj;

	@JsonIgnore
	public String getCpfCnpjSemFormatacao() {
		return this.cpfCnpj.replaceAll("\\.|-|/", "").trim();
	}
	
}
