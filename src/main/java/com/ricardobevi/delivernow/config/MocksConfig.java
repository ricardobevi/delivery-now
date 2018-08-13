package com.ricardobevi.delivernow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.ricardobevi.delivernow.gateways.ETAGateway;
import com.ricardobevi.delivernow.gateways.MockedETAGateway;


@Profile("test")
@Configuration
public class MocksConfig {

	@Bean
	@Primary
	public ETAGateway GoogleMapsETAGateway() {
		return new MockedETAGateway();
	}

}
