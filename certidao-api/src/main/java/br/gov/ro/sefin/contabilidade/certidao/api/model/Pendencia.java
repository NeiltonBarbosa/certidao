package br.gov.ro.sefin.contabilidade.certidao.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Pendencia {

	private String convenio;
	private String ug;
	private LocalDate data;
	private String situacao;
	private BigDecimal valor;
	
}
