package br.gov.ro.sefin.contabilidade.certidao.api.service.google;

import br.gov.ro.sefin.contabilidade.certidao.api.service.google.exception.ReCaptchaInvalidException;

public interface ICaptchaService {
	
	void processResponse(final String response) throws ReCaptchaInvalidException;
	
	String getReCaptchaSite();

    String getReCaptchaSecret();
}
