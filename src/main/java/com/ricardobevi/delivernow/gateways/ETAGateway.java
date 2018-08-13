package com.ricardobevi.delivernow.gateways;

import com.ricardobevi.delivernow.dto.OrderDto;
import com.ricardobevi.delivernow.dto.RestaurantDto;

public interface ETAGateway {
	
	String etaString(RestaurantDto restaurantFrom, OrderDto orderTo);
	
}
