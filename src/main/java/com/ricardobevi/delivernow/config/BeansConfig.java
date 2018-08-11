package com.ricardobevi.delivernow.config;

import java.awt.font.NumericShaper.Range;
import java.util.Arrays;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ricardobevi.delivernow.gateways.RestaurantGateway;
import com.ricardobevi.delivernow.gateways.model.RestaurantDAO;
import com.ricardobevi.delivernow.gateways.model.RestaurantRepository;
import com.ricardobevi.delivernow.gateways.DatabaseRestaurantGateway;

@Configuration
public class BeansConfig {
	
	@Bean
	public RestaurantGateway DatabaseRestaurantGateway() {
		return new DatabaseRestaurantGateway();
	}

	@Bean
	CommandLineRunner init(RestaurantRepository restaurantRepository) {
		return args ->
				IntStream.range(0, 10).parallel()
				.forEach(restaurantId -> {
					restaurantRepository.save(new RestaurantDAO());
				});
	}
	
}
