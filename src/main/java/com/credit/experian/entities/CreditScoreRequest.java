package com.credit.experian.entities;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.credit.experian.EnumConstants.ApplicationId;
import com.credit.experian.EnumConstants.RequestType;
import com.credit.experian.entities.ProviderRequest.ProviderType;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Component
@Data
public class CreditScoreRequest extends Base {

	private String requestUid;
	private ApplicationId applicationId;
	private RequestType requestType;
	private ProviderType providerType;
	private Map<String, Object> providerRequest;
	public String getRequestUid() {
		return requestUid;
	}
	public void setRequestUid(String requestUid) {
		this.requestUid = requestUid;
	}
	public ApplicationId getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(ApplicationId applicationId) {
		this.applicationId = applicationId;
	}
	public RequestType getRequestType() {
		return requestType;
	}
	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}
	public ProviderType getProviderType() {
		return providerType;
	}
	public void setProviderType(ProviderType providerType) {
		this.providerType = providerType;
	}
	public Map<String, Object> getProviderRequest() {
		return providerRequest;
	}
	public void setProviderRequest(Map<String, Object> providerRequest) {
		this.providerRequest = providerRequest;
	}
	
	

}
