package com.credit.experian.service;

import org.json.JSONObject;

import com.credit.experian.entities.CreditScoreRequest;
import com.credit.experian.entities.CreditScoreResponse;
import com.credit.experian.entities.Customer;

public interface ICreditScoreService {

	
	CreditScoreResponse queryCreditScore(CreditScoreRequest creditrequest);
	CreditScoreResponse pullCreditScore(CreditScoreRequest creditrequest);
}
