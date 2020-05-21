package br.gov.ro.sefin.contabilidade.certidao.api.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ConfigurationProperties("certidao")
public class CertidaoProperty {

	private Entirex entirex = new Entirex();
	private Google google = new Google();
	
	private String origemPermitida = "http://localhost:4200";
	
	
	@Getter @Setter
	public static class Entirex {
		private String brokerId;
		private String serverAddress;
		private String userId;
		
	}
	
	@Getter @Setter
	public static class Google {
		private String site;
		private String secret;
	}
	
}
