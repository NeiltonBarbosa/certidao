package br.gov.ro.sefin.contabilidade.certidao.api.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.gov.ro.sefin.contabilidade.certidao.api.service.exception.CertidaoNaoEncontradaException;
import br.gov.ro.sefin.contabilidade.certidao.api.service.exception.EntirexException;
import br.gov.ro.sefin.contabilidade.certidao.api.service.exception.InteressadoNaoEncontradoException;
import br.gov.ro.sefin.contabilidade.certidao.api.service.exception.WebServiceSitafeException;
import br.gov.ro.sefin.contabilidade.certidao.api.service.google.exception.ReCaptchaInvalidException;
import br.gov.ro.sefin.contabilidade.certidao.api.service.google.exception.ReCaptchaUnavailableException;
import lombok.Getter;
import lombok.Setter;

@ControllerAdvice
public class CertidaoExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Erro> erros = criarListaErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ InteressadoNaoEncontradoException.class } )
	private ResponseEntity<Object> handleInteressadoNaoEncontradoException(InteressadoNaoEncontradoException ex, WebRequest request) {
		Erro erro = new Erro("404",  ex.getMessage(), ex.getClass().getSimpleName());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Arrays.asList(erro));
	}
	
	@ExceptionHandler({ CertidaoNaoEncontradaException.class } )
	private ResponseEntity<Object> handleCertidaoNaoEncontradaException(CertidaoNaoEncontradaException ex, WebRequest request) {
		Erro erro = new Erro("400",  ex.getMessage(), ex.getClass().getSimpleName());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Arrays.asList(erro));
	}
	
	@ExceptionHandler({ WebServiceSitafeException.class } )
	private ResponseEntity<Object> handleWebServiceSitafeException(WebServiceSitafeException ex, WebRequest request) {
		Erro erro = new Erro("500",  ex.getMessage(), ex.getClass().getSimpleName());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Arrays.asList(erro));
	}
	
	@ExceptionHandler({ EntirexException.class } )
	private ResponseEntity<Object> handleEntirexException(EntirexException ex, WebRequest request) {
		Erro erro = new Erro("500",  ex.getMessage(), ex.getClass().getSimpleName());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Arrays.asList(erro));
	}
	
	@ExceptionHandler({ReCaptchaInvalidException.class})
	private ResponseEntity<Object> handleReCaptchaInvalidException(ReCaptchaInvalidException ex, WebRequest request) {
		Erro erro = new Erro("400", ex.getMessage(), ex.getClass().getSimpleName());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Arrays.asList(erro));
	}
	
	@ExceptionHandler({ReCaptchaUnavailableException.class})
	private ResponseEntity<Object> handleReCaptchaUnavailableException(ReCaptchaUnavailableException ex, WebRequest request) {
		Erro erro = new Erro("400", ex.getMessage(), ex.getClass().getSimpleName());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Arrays.asList(erro));
	}
	
	private List<Erro> criarListaErros(BindingResult bindingResult) {
		List<Erro> erros = new ArrayList<>();
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String trace = fieldError.toString();
			erros.add(new Erro("400", message, trace));
		}
			
		return erros;
	}
	
	@Getter @Setter
	public static class Erro {
		
		private String code;
		private String message;
		private String type;
		
		public Erro(String code, String message, String type) {
			this.code = code;
			this.message = message;
			this.type = type;
		}
		
	}
	
}
