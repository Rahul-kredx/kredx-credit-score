package com.credit.experian.entities;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.credit.experian.CustomExceptions.ErrorMessage;
import com.credit.experian.entities.ProviderRequest.ProviderType;

import lombok.Data;

@Component
@Data
public class CreditScoreResponse extends Base {
	private String responseUid;
	private List<ErrorMessage> errors;
	private ProviderType providerType;
	private Map<String,Object> providerResponse;
	public String getResponseUid() {
		return responseUid;
	}
	public void setResponseUid(String responseUid) {
		this.responseUid = responseUid;
	}
	public List<ErrorMessage> getErrors() {
		return errors;
	}
	public void setErrors(List<ErrorMessage> errors) {
		this.errors = errors;
	}
	public ProviderType getProviderType() {
		return providerType;
	}
	public void setProviderType(ProviderType providerType) {
		this.providerType = providerType;
	}
	public Map<String,Object> getProviderResponse() {
		return providerResponse;
	}
	public void setProviderResponse(Map<String,Object> providerResponse) {
		this.providerResponse = providerResponse;
	}
	
	

}
