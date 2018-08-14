package com.ricardobevi.delivernow.config;

import java.util.Arrays;

import com.ricardobevi.delivernow.gateways.*;
import com.ricardobevi.delivernow.gateways.impl.DatabaseRestaurantGateway;
import com.ricardobevi.delivernow.gateways.impl.GmailMailGateway;
import com.ricardobevi.delivernow.gateways.impl.GoogleMapsETAGateway;
import com.ricardobevi.delivernow.gateways.impl.NotifServiceSMSGateway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ricardobevi.delivernow.dto.MealDto;
import com.ricardobevi.delivernow.gateways.model.MealDAO;
import com.ricardobevi.delivernow.gateways.model.RestaurantDAO;
import com.ricardobevi.delivernow.gateways.model.RestaurantRepository;
import com.ricardobevi.delivernow.gateways.model.ReviewDAO;

@Configuration
public class BeansConfig {
	
	public static final MealDto friedPotatoes = new MealDto("Fried Potatoes", "Yummy potatoes", 2.5);
	public static final MealDto bakedPotatoes = new MealDto("Baked Potatoes", "Dummy potatoes", 3.5);
	
	public static final String ciudadelaHood = "-34.629359,-58.537554";


	@Bean
	public RestaurantGateway DatabaseRestaurantGateway() {
		return new DatabaseRestaurantGateway();
	}
	
	@Bean
	public ETAGateway GoogleMapsETAGateway() {
		return new GoogleMapsETAGateway();
	}
	
	@Bean
	public MailGateway GmailMailGateway() {
		return new GmailMailGateway();
	}

	@Bean
	public SMSGateway NotifServiceSMSGateway() {
		return new NotifServiceSMSGateway();
	}

	@Bean
	CommandLineRunner init(RestaurantRepository restaurantRepository) {
		return args -> {
			
			restaurantRepository.save(
					new RestaurantDAO(
							1L,
							Arrays.asList(
									new ReviewDAO(1L, "Richard", "Nice place!", 4.0),
									new ReviewDAO(1L, "Anne", "I LOVE POTATOES!", 5.0)
							),
							Arrays.asList(
									new MealDAO(friedPotatoes), 
									new MealDAO(bakedPotatoes)
							),
							ciudadelaHood,
							"commercial.email@mail.com",
							4.5
					)
			);
		};
	}
	
}
