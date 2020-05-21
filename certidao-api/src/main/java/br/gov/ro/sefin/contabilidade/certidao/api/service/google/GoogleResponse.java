package br.gov.ro.sefin.contabilidade.certidao.api.service.google;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
	"success",
	"challeng_ts",
	"hostname",
	"error-codes"
})
public class GoogleResponse {

	@JsonProperty("success")
    private boolean success;
     
    @JsonProperty("challenge_ts")
    private String challengeTs;
     
    @JsonProperty("hostname")
    private String hostname;
     
    @JsonProperty("error-codes")
    private ErrorCode[] errorCodes;
    
    @Override
    public String toString() {
        return "GoogleResponse{" + "success=" + success + ", challengeTs='" + challengeTs + '\'' + ", hostname='" + hostname + '\'' + ", errorCodes=" + Arrays.toString(errorCodes) + '}';
    }
 
    //@SuppressWarnings("incomplete-switch")
	@JsonIgnore
    public boolean hasClientError() {
        ErrorCode[] errors = getErrorCodes();
        if(errors == null) {
            return false;
        }
        for(ErrorCode error : errors) {
        	if (error != null) 
	            switch(error) {
	                case InvalidResponse:
	                case MissingResponse:
	                    return true;
				default:
					break;
	            }
        }
        return false;
    }
	
	static enum ErrorCode {
        MissingSecret,     InvalidSecret,
        MissingResponse,   InvalidResponse;
 
        private static Map<String, ErrorCode> errorsMap = new HashMap<String, ErrorCode>(4);
 
        static {
            errorsMap.put("missing-input-secret",   MissingSecret);
            errorsMap.put("invalid-input-secret",   InvalidSecret);
            errorsMap.put("missing-input-response", MissingResponse);
            errorsMap.put("invalid-input-response", InvalidResponse);
        }
 
        @JsonCreator
        public static ErrorCode forValue(String value) {
            return errorsMap.get(value.toLowerCase());
        }
    }
}
