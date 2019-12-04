package com.credit.experian.service;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.StringEscapeUtils;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.credit.experian.CustomExceptions.ErrorMessage;
import com.credit.experian.EnumConstants.ApplicationId;
import com.credit.experian.EnumConstants.RequestType;
import com.credit.experian.Repository.CreditInfoRepo;
import com.credit.experian.entities.CreditScoreRequest;
import com.credit.experian.entities.CreditScoreResponse;
import com.credit.experian.entities.Customer;
import com.credit.experian.entities.ExperianProviderRequest;
import com.credit.experian.entities.ExperianProviderResponse;
import com.credit.experian.entities.ProviderRequest;
import com.credit.experian.entities.ProviderRequest.ProviderType;
import com.credit.experian.model.CreditScoreInfo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.sentry.Sentry;
import kong.unirest.*;

@Service
public class ExperianCreditService implements ICreditScoreService {

	@Autowired
	private Environment env;

	@Autowired
	private CreditInfoRepo creditInfoRep;
	
//	@Value("${expcrediturl}")
//	private String url;
//	
//	@Value("${expcredithost}")
//	private String host;


	
	public CreditScoreResponse pullCreditScore(CreditScoreRequest request) {
		
		System.out.println("Env"+env+env.getProperty("expcrediturl"));

		ProviderType providerType = request.getProviderType();
		Map<String, Object> providerRequest = request.getProviderRequest();
		List<ErrorMessage> errorList = new ArrayList<ErrorMessage>();
		CreditScoreInfo responseData = new CreditScoreInfo();
		String requestUid = request.getRequestUid();
		CreditScoreResponse creditscoreresponse = new CreditScoreResponse();

		CreditScoreInfo creditScore = creditInfoRep.findByUid1(requestUid);
		if (creditScore != null) {
			if (request.getApplicationId() == creditScore.getRequest().getApplicationId()
					&& request.getRequestUid().equals(creditScore.getUid1())) {
				System.out.println("Duplicate Uid and Application Id please call Query Request ");
				ErrorMessage error=new ErrorMessage();
				error.setMessage("please call query request");
				error.setDetails("Duplicate Uid and Application Id");
				error.setErrorcode(100);
				errorList.add(error);
				creditscoreresponse.setErrors(errorList);
				creditscoreresponse.setProviderResponse(null);
				creditscoreresponse.setProviderType(providerType);
				return creditscoreresponse;

			}
		}

		responseData.setProvider_request_timestamp(new Timestamp(System.currentTimeMillis()));
		responseData.setRequest(request);
		responseData.setUid(requestUid);
		System.out.println("123" + providerRequest);
		ExperianProviderRequest experianRequest = new ExperianProviderRequest(providerRequest);
		
		ExperianProviderResponse experianResponse = getExperianResponse(experianRequest);
		Map<String,Object> mp=new HashMap<String, Object>();
		mp.put("providerResponse", experianResponse);
		creditscoreresponse.setProviderResponse(mp);
		creditscoreresponse.setProviderType(providerType);
		creditscoreresponse.setResponseUid("1234rt");

		if (experianResponse.getErrorString() != null) {
			ErrorMessage error = new ErrorMessage();
			error.setMessage("consumer record not found");
			error.setErrorcode(404);
			error.setDetails("Invalid Data");
			errorList.add(error);
			creditscoreresponse.setErrors(errorList);
			responseData.setProvider_response_timestamp(new Timestamp(System.currentTimeMillis()));
		} else {
			creditscoreresponse.setErrors(errorList);
			responseData.setResponse(creditscoreresponse);
			responseData.setProvider_response_timestamp(new Timestamp(System.currentTimeMillis()));
			System.out.println("909090" + requestUid);
			responseData.setUid(requestUid);
		}
		creditInfoRep.save(responseData);
		return creditscoreresponse;

	}

	private ExperianProviderResponse getExperianResponse(ExperianProviderRequest erperianrequest) {
		Sentry.init(env.getProperty("dsn"));
		Map<String, Object> result = new HashMap<String, Object>();
		HttpResponse<JsonNode> response = null;
		ExperianProviderResponse experiaresponse = new ExperianProviderResponse();
		ProviderRequest requestedData = erperianrequest;
		String url = env.getProperty("expcrediturl");
		String host = env.getProperty("expcredithost");
		System.out.println(url);
		System.out.println(host);
		System.out.println("clientName=KREDX_EM&allowInput=1&allowEdit=1&allowCaptcha=1&allowConsent=1&allowEmailVerify=1&allowVoucher=1&voucherCode=KREDXVSuW3&firstName="
							+ erperianrequest.getFullName() + "&surName=Oza&mobileNo=" + erperianrequest.getPhno()
							+ "&email=" + erperianrequest.getEmail() + "&noValidationByPass=0&emailConditionalByPass=1");
		try {
			response = Unirest.post(url).header("Content-Type", "application/x-www-form-urlencoded")
					.header("Host", host).header("Accept-Encoding", "gzip, deflate")
					.body("clientName=KREDX_EM&allowInput=1&allowEdit=1&allowCaptcha=1&allowConsent=1&allowEmailVerify=1&allowVoucher=1&voucherCode=KREDXVSuW3&firstName="
							+ erperianrequest.getFullName() + "&surName=Oza&mobileNo=" + erperianrequest.getPhno()
							+ "&email=" + erperianrequest.getEmail() + "&noValidationByPass=0&emailConditionalByPass=1")
					.asJson();

		} catch (UnirestException e) {
			Sentry.capture(e);
			System.out.println("Exception caught while calling external Api " + e.getMessage());
		}
		if (response != null) {

			JsonNode data = response.getBody();
//			if (data.getObject().get("errorString") == null) {
//				String validXml = StringEscapeUtils
//						.unescapeXml(data.getObject().get("showHtmlReportForCreditReport").toString());
//				JSONObject xmlJson = XML.toJSONObject(validXml);

			ObjectMapper objectMapper = new ObjectMapper();

			try {
				experiaresponse = objectMapper.readValue(data.toString(), ExperianProviderResponse.class);

				return experiaresponse;
			} catch (JsonParseException e) {
				Sentry.capture(e);
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				Sentry.capture(e);
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				Sentry.capture(e);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			} else {
//				experiaresponse.setErrorString("consumer data not found");
//				return experiaresponse;
//			}
		}
		return experiaresponse;
	}

//	private static Document convertStringToXMLDocument(String xmlString) {
//		// Parser that produces DOM object trees from XML content
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//
//		// API to obtain DOM Document instance
//		DocumentBuilder builder = null;
//		try {
//			// Create DocumentBuilder with default configuration
//			builder = factory.newDocumentBuilder();
//
//			// Parse the content to Document object
//			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
//			return doc;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	@Override
	public CreditScoreResponse queryCreditScore(CreditScoreRequest creditrequest) {
		
		CreditScoreResponse creditscoreresponse = new CreditScoreResponse();
		List<ErrorMessage> errorList = new ArrayList<ErrorMessage>();
		String requestUid = creditrequest.getRequestUid();
		
	
			CreditScoreInfo creditScore = creditInfoRep.findByUid1(requestUid);
			if(creditScore!=null && creditScore.getRequest().getApplicationId().equals(creditrequest.getApplicationId())) {
				creditscoreresponse.setResponseUid(creditScore.getUid1());
				System.out.println(creditScore.getUid1()+creditScore.getRequest()+creditScore.getResponse());
				creditscoreresponse.setProviderResponse(creditScore.getResponse().getProviderResponse());
				creditscoreresponse.setProviderType(creditScore.getResponse().getProviderType());
				creditscoreresponse.setErrors(null);
				return creditscoreresponse;
			}else {
				creditscoreresponse.setResponseUid(null);
				creditscoreresponse.setProviderResponse(null);
				creditscoreresponse.setProviderType(null);
				ErrorMessage error=new ErrorMessage();
				error.setMessage("Invalid uid or applicationId");
				error.setDetails("No Reponse with given UID or Application Id");
				error.setErrorcode(101);
				errorList.add(error);
				creditscoreresponse.setErrors(errorList);
			}
		
		return creditscoreresponse;
	}
}
