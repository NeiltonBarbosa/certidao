package br.gov.ro.sefin.contabilidade.certidao.api.service.exception;

public class InteressadoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InteressadoNaoEncontradoException(String message) {
		super(message);
	}
}
