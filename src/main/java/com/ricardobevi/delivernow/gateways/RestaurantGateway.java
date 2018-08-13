package com.ricardobevi.delivernow.gateways;

import java.util.List;

import com.ricardobevi.delivernow.dto.RestaurantDto;

public interface RestaurantGateway {

	RestaurantDto getRestaurantFromId(Long restaurantId);
	void save(RestaurantDto restaurantDto);
	void delete(RestaurantDto restaurantDto);
	List<RestaurantDto> listAll();
	List<RestaurantDto> listAllWithRatingGreaterThan(Double minRating);

}
