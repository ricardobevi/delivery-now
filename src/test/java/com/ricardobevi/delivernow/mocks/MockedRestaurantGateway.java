package com.ricardobevi.delivernow.mocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.ricardobevi.delivernow.dto.LatLongLocationDto;
import com.ricardobevi.delivernow.dto.MealDto;
import com.ricardobevi.delivernow.dto.OrderDto;
import com.ricardobevi.delivernow.dto.RestaurantDto;
import com.ricardobevi.delivernow.dto.ReviewDto;
import com.ricardobevi.delivernow.gateways.MockedETAGateway;
import com.ricardobevi.delivernow.gateways.RestaurantGateway;
import com.ricardobevi.delivernow.gateways.model.RestaurantDAO;

public class MockedRestaurantGateway implements RestaurantGateway {

	public static final MealDto friedPotatoes = new MealDto("Fried Potatoes", "Yummy potatoes", 2.5);
	public static final MealDto bakedPotatoes = new MealDto("Baked Potatoes", "Dummy potatoes", 3.5);
	public static final MealDto smashedPotatoes = new MealDto("Smashed Potatoes", "Nice potatoes", 4.5);
	
	public static final MealDto invisiblePotatoes = new MealDto("Invisible Potatoes", "I swear I saw them!", 4.5);
	
	
	List<RestaurantDto> restaurants = new ArrayList<RestaurantDto>();
	
	public RestaurantDto getRestaurantFromId(Long restaurantId) {
		RestaurantDto restaurantDto = new RestaurantDto(
				1L, 
				1.0, 
				Arrays.asList(
						new ReviewDto("John", "Nice place to hang out with friends!", 4.5)
				),
				Arrays.asList(
						friedPotatoes,
						bakedPotatoes,
						smashedPotatoes
				),
				Arrays.asList(
						new OrderDto(
							Arrays.asList(friedPotatoes),
							2.5,
							"221b Baker Street",
							new LatLongLocationDto(MockedETAGateway.haedoCity)
						)
				),
				new LatLongLocationDto(MockedETAGateway.ciudadelaHood),
				"commercial.email@mail.com"
		);
		
		this.restaurants.add(restaurantDto);
		
		return restaurantDto;
	}


	public void save(RestaurantDto asDto) {
	}


	public void delete(RestaurantDto restaurantDto) {			
	}


	@Override
	public List<RestaurantDto> listAll() {
		return this.restaurants;
	}


	@Override
	public List<RestaurantDto> listAllWithRatingGreaterThan(Double minRating) {
		return this.restaurants.stream().filter(resto -> resto.getRating() > minRating).collect(Collectors.toList());
	}
	
	
	
}