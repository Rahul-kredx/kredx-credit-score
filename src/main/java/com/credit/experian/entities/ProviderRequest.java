package com.credit.experian.entities;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class ProviderRequest extends Base {

	public enum ProviderType {
		EXPERIAN
	}

}
