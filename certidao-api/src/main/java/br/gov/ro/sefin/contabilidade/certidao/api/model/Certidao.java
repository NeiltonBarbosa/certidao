package br.gov.ro.sefin.contabilidade.certidao.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.gov.ro.sefin.contabilidade.certidao.api.model.validation.CertidaoGroupSequenceProvider;
import br.gov.ro.sefin.contabilidade.certidao.api.model.validation.group.CnpjGroup;
import br.gov.ro.sefin.contabilidade.certidao.api.model.validation.group.CpfGroup;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode(of = "codigoControle")
@GroupSequenceProvider(CertidaoGroupSequenceProvider.class)
public class Certidao {

	@NotNull
	private Tipo tipo;
	
	private String nomeRazaoSocial;
	
	@NotBlank
	@CPF(groups = CpfGroup.class)
	@CNPJ(groups = CnpjGroup.class)
	private String cpfCnpj;
	
	@NotBlank
	private String codigoControle;
	
	@NotNull
	private LocalDateTime dataHoraEmissao;
	
	private LocalDate dataValidade;
	private Status status;
	private SituacaoDocumento situacaoDocumento;
	private BigDecimal saldo;
	private List<Pendencia> pendencias = new ArrayList<>();
	
	@JsonIgnore
	public boolean isInteressadoNaoEncontrado() {
		return this.nomeRazaoSocial.equals("NAO ENCONTRADO NA BASE DE DADOS              ");
	}
	
	@JsonIgnore
	public String getCpfOuCnpjSemFormatacao() {
		return this.cpfCnpj.replaceAll("\\.|-|/", "").trim();
	}
	
	public String getTipoDescription() {
		return this.tipo.getDescricao();
	}
}
