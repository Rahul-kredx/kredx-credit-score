package com.credit.experian.converters;

import java.io.IOException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.credit.experian.entities.CreditScoreRequest;
import com.credit.experian.entities.CreditScoreResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Converter(autoApply = true)
public class CreditResponseJsonConverter implements AttributeConverter<CreditScoreResponse, String> {

	@Override
	public String convertToDatabaseColumn(CreditScoreResponse attribute) {
		ObjectMapper objectmapper = new ObjectMapper();
		String creditresponse = null;
		try {
			creditresponse = objectmapper.writeValueAsString(attribute);
		} catch (final JsonProcessingException e) {
			System.out.println("Error Ocuured in json converter");
		}

		return creditresponse;

	}

	@Override
	public CreditScoreResponse convertToEntityAttribute(String dbData) {
		CreditScoreResponse creditresponse = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			creditresponse = objectMapper.readValue(dbData, CreditScoreResponse.class);
		} catch (final IOException e) {
//	            logger.error("JSON reading error", e);
		}

		return creditresponse;
	}
}
