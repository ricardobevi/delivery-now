package com.ricardobevi.delivernow.mocks;

import java.util.Arrays;

import com.ricardobevi.delivernow.dto.MealDto;
import com.ricardobevi.delivernow.dto.RestaurantDto;
import com.ricardobevi.delivernow.dto.ReviewDto;
import com.ricardobevi.delivernow.gateways.RestaurantGateway;

public class MockedRestaurantGateway implements RestaurantGateway {

	public static final MealDto friedPotatoes = new MealDto("Fried Potatoes", "Yummy potatoes", 2.5);
	public static final MealDto bakedPotatoes = new MealDto("Baked Potatoes", "Dummy potatoes", 3.5);
	public static final MealDto smashedPotatoes = new MealDto("Smashed Potatoes", "Nice potatoes", 4.5);
	
	public static final MealDto invisiblePotatoes = new MealDto("Invisible Potatoes", "I swear I saw them!", 4.5);
	
	public RestaurantDto getRestaurantFromId(Long restaurantId) {
		return new RestaurantDto(
				1L, 
				1.0, 
				Arrays.asList(
						new ReviewDto("John", "Nice place to hang out with friends!", 4.5)
				),
				Arrays.asList(
						friedPotatoes,
						bakedPotatoes,
						smashedPotatoes
				)
		);
	}


	public void save(RestaurantDto asDto) {
	}


	public void delete(RestaurantDto restaurantDto) {			
	}
	
	
	
}