package com.ricardobevi.delivernow.gateways;

import com.ricardobevi.delivernow.dto.OrderDto;
import com.ricardobevi.delivernow.dto.RestaurantDto;

public class GoogleMapsETAGateway implements ETAGateway {

	@Override
	public String etaString(RestaurantDto restaurantFrom, OrderDto orderTo) {
		return "";
	}

	
}
