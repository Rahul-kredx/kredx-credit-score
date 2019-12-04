package com.credit.experian.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
public class SentryConfig  {

	@Bean
	public HandlerExceptionResolver sentryExceptionSelover() {
		return new io.sentry.spring.SentryExceptionResolver();
	}
	
}
