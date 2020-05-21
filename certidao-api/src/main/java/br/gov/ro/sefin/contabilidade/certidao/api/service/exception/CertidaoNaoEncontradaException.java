package br.gov.ro.sefin.contabilidade.certidao.api.service.exception;

public class CertidaoNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CertidaoNaoEncontradaException(String message) {
		super(message);
	}

}
