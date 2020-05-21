package br.gov.ro.sefin.contabilidade.certidao.api.service.google;

import java.net.URI;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import br.gov.ro.sefin.contabilidade.certidao.api.property.CertidaoProperty;
import br.gov.ro.sefin.contabilidade.certidao.api.service.google.exception.ReCaptchaInvalidException;
import br.gov.ro.sefin.contabilidade.certidao.api.service.google.exception.ReCaptchaUnavailableException;

@Service("captchaService")
public class CaptchaService implements ICaptchaService  {
	private final static Logger LOGGER = LoggerFactory.getLogger(CaptchaService.class);
	
	@Autowired
	private CertidaoProperty certidaoProperty;
	
	@Autowired
    private ReCaptchaAttemptService reCaptchaAttemptService;
	
	@Autowired
    private HttpServletRequest request;
	
	@Autowired
    private RestOperations restTemplate;
	
	private static Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");
	
	@Override
	public void processResponse(String response) {
		LOGGER.debug("Attempting to validate response {}", response);
		
		if (reCaptchaAttemptService.isBlocked(getClientIP())) {
            throw new ReCaptchaInvalidException("Você excedeu o número de tentativas do reCAPTCHA");
        }
		
		if (!responseSanityCheck(response)) {
			throw new ReCaptchaInvalidException("Por favor, clique em não sou um robô");
		}
		
		 final URI verifyUri = URI.create(String.format(
				 "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s&remoteip=%s",
				 getReCaptchaSecret(), response, getClientIP()));
		 
		 try {
            final GoogleResponse googleResponse = restTemplate.getForObject(verifyUri, GoogleResponse.class);
            LOGGER.debug("Google's response: {} ", googleResponse.toString());
            
            if (!googleResponse.isSuccess()) {
                if (googleResponse.hasClientError()) {
                    reCaptchaAttemptService.reCaptchaFailed(getClientIP());
                }
                throw new ReCaptchaInvalidException("reCaptcha não foi validado com sucesso");
            }
        } catch (RestClientException rce) {
            throw new ReCaptchaUnavailableException("Não foi possível verificar o reCAPTCHA, por favor tente novamente mais tarde", rce);
        }
        reCaptchaAttemptService.reCaptchaSucceeded(getClientIP());
	}

	private boolean responseSanityCheck(String response) {
		return StringUtils.hasLength(response) && RESPONSE_PATTERN.matcher(response).matches();
	}

	@Override
	public String getReCaptchaSite() {
		return certidaoProperty.getGoogle().getSite();
	}

	@Override
	public String getReCaptchaSecret() {
		return "6LecYzsUAAAAABL0-9W07CUcASr81-RGlOYqkDKY";
	}
	
	private String getClientIP() {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

}
