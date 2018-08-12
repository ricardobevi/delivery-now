package com.ricardobevi.delivernow.gateways;

import com.ricardobevi.delivernow.dto.RestaurantDto;

public interface RestaurantGateway {

	public RestaurantDto getRestaurantFromId(Long restaurantId);
	public void save(RestaurantDto restaurantDto);
	public void delete(RestaurantDto restaurantDto);

}
