package br.gov.ro.sefin.contabilidade.certidao.api;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import br.gov.ro.sefin.contabilidade.certidao.api.property.CertidaoProperty;

@SpringBootApplication
@EnableConfigurationProperties(CertidaoProperty.class)
public class CertidaoApiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CertidaoApiApplication.class, args);
	}
    
    @Bean
    public RestTemplate restTemplate() throws Exception {
    	SSLContext sslContext = new SSLContextBuilder()
			      .loadTrustMaterial(null, (certificate, authType) -> true).build();
		 
		 CloseableHttpClient client = HttpClients.custom()
			      .setSSLContext(sslContext)
			      .setSSLHostnameVerifier(new NoopHostnameVerifier())
			      .build();
    	
		 HttpComponentsClientHttpRequestFactory requestFactory =
			        new HttpComponentsClientHttpRequestFactory();

			requestFactory.setHttpClient(client);
    	
    	return new RestTemplate(requestFactory);
    	
    }

}
