package com.ricardobevi.delivernow.mocks;

import com.ricardobevi.delivernow.dto.OrderDto;
import com.ricardobevi.delivernow.dto.RestaurantDto;
import com.ricardobevi.delivernow.gateways.ETAGateway;

public class MockedETAGateway implements ETAGateway{

	public String etaString(RestaurantDto restaurantFrom, OrderDto orderTo) {
		return "Your order will arrive in 50 minutes";
	}

}
