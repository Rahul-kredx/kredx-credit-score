package com.credit.experian.model;

import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.validator.constraints.Length;

import com.credit.experian.converters.CreditRequestJsonConverter;
import com.credit.experian.converters.CreditResponseJsonConverter;
import com.credit.experian.entities.BaseObj;
import com.credit.experian.entities.CreditScoreRequest;
import com.credit.experian.entities.CreditScoreResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
public class CreditScoreInfo extends BaseObj {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String uid1;
	@Convert(converter = CreditRequestJsonConverter.class)
	private CreditScoreRequest request;
	private Timestamp provider_request_timestamp;
	private Timestamp provider_response_timestamp;
	@Convert(converter = CreditResponseJsonConverter.class)
	@Column(length = 15000)
	private CreditScoreResponse response;

	public CreditScoreInfo(){
		super();
	}


	public Long getId() {
		return id;
	}

	public CreditScoreRequest getRequest() {
		return request;
	}

	public void setRequest(CreditScoreRequest request) {
		this.request = request;
	}

	public CreditScoreResponse getResponse() {
		return response;
	}

	public void setResponse(CreditScoreResponse response) {
		this.response = response;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUid1() {
		return uid1;
	}

	public void setUid(String uid) {
		uid1 = uid;
	}

	public Timestamp getProvider_request_timestamp() {
		return provider_request_timestamp;
	}

	public void setProvider_request_timestamp(Timestamp provider_request_timestamp) {
		this.provider_request_timestamp = provider_request_timestamp;
	}

	public Timestamp getProvider_response_timestamp() {
		return provider_response_timestamp;
	}

	public void setProvider_response_timestamp(Timestamp provider_response_timestamp) {
		this.provider_response_timestamp = provider_response_timestamp;
	}

}
