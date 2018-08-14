package com.ricardobevi.delivernow.config;

import com.ricardobevi.delivernow.gateways.*;
import com.ricardobevi.delivernow.gateways.mocks.MockedETAGateway;
import com.ricardobevi.delivernow.gateways.mocks.MockedMailGateway;
import com.ricardobevi.delivernow.gateways.mocks.MockedSMSGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;


@Profile("test")
@Configuration
public class MocksConfig {

	@Bean
	@Primary
	public ETAGateway GoogleMapsETAGateway() {
		return new MockedETAGateway();
	}
	
	
	@Bean
	@Primary
	public MailGateway MockedMailGateway() {
		return new MockedMailGateway();
	}


	@Bean
	@Primary
	public SMSGateway MockedSMSGateway() {
		return new MockedSMSGateway();
	}
}
