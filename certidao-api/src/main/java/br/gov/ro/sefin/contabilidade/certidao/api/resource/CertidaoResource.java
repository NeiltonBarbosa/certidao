package br.gov.ro.sefin.contabilidade.certidao.api.resource;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.ro.sefin.contabilidade.certidao.api.model.Certidao;
import br.gov.ro.sefin.contabilidade.certidao.api.model.Interessado;
import br.gov.ro.sefin.contabilidade.certidao.api.service.CertidaoService;
import br.gov.ro.sefin.contabilidade.certidao.api.service.google.ICaptchaService;

@RestController
@RequestMapping("/")
public class CertidaoResource {
	
	@Autowired
	private CertidaoService certidaoService;
	
	@Autowired
	private ICaptchaService captchaService;

	@PostMapping
	public ResponseEntity<Certidao> emitir(@Valid @RequestBody Interessado interessado, HttpServletRequest httpServletRequest) {
		captchaService.processResponse(httpServletRequest.getParameter("g-recaptcha-response"));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(certidaoService.emitir(interessado));
	}
	
	@PostMapping(params = "autenticar")
	public ResponseEntity<Certidao> autenticar(@Valid @RequestBody Certidao certidao, HttpServletRequest httpServletRequest) {
		captchaService.processResponse(httpServletRequest.getParameter("g-recaptcha-response"));
		return ResponseEntity.ok(certidaoService.autenticar(certidao));
	}
}
