package com.credit.experian.converters;

import java.io.IOException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.credit.experian.entities.CreditScoreRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Converter(autoApply = true )
public class CreditRequestJsonConverter implements AttributeConverter<CreditScoreRequest, String> {

	@Override
	public String convertToDatabaseColumn(CreditScoreRequest attribute) {
		ObjectMapper objectmapper = new ObjectMapper();
		String creditrequest = null;
		try {
			creditrequest = objectmapper.writeValueAsString(attribute);
		} catch (final JsonProcessingException e) {
			System.out.println("Error Ocuured in json converter");
		}

		return creditrequest;
	}

	@Override
	public CreditScoreRequest convertToEntityAttribute(String dbData) {
		CreditScoreRequest creditrequest = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			creditrequest = objectMapper.readValue(dbData, CreditScoreRequest.class);
		} catch (final IOException e) {
//	            logger.error("JSON reading error", e);
		}

		return creditrequest;
	}
}
