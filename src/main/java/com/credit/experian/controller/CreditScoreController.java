package com.credit.experian.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.credit.experian.EnumConstants.ApplicationId;
import com.credit.experian.EnumConstants.RequestType;
import com.credit.experian.entities.CreditScoreRequest;
import com.credit.experian.entities.CreditScoreResponse;
import com.credit.experian.entities.Customer;
import com.credit.experian.entities.ProviderRequest;
import com.credit.experian.service.ExperianCreditService;

@RestController
public class CreditScoreController {
	
	@Autowired
	private ExperianCreditService expCreditService;
	
	@GetMapping("/greet")
	public String test() {
		return "Hello";
	}
 
	@PostMapping(path="/getCredit",consumes = MediaType.APPLICATION_JSON_VALUE)
	public CreditScoreResponse getCreditScore(@RequestBody CreditScoreRequest request) {
	switch (request.getProviderType()) {
	case EXPERIAN:
		if(request.getRequestType()==RequestType.PULL) {
		return expCreditService.pullCreditScore(request);
		}else if(request.getRequestType()==RequestType.QUERY) {
			return expCreditService.queryCreditScore(request);
		}

	default:
		break;
	}
		return null;
	}
}
