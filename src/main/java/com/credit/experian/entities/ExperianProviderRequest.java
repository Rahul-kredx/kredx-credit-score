package com.credit.experian.entities;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
public class ExperianProviderRequest extends ProviderRequest{
	
	private String fullName;
	private String lastName;
	private String phno;
	private String email;
	
	
	
	public ExperianProviderRequest() {
		super();
	}


	public ExperianProviderRequest(Map<String, Object> obj) {
		super();
		this.fullName=obj.get("firstName").toString();
		this.lastName=obj.get("lastName").toString();
		this.phno=obj.get("phno").toString();
		this.email=obj.get("email").toString();
	}
	
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

	
}
