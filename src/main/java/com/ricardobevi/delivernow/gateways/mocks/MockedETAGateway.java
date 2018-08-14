package com.ricardobevi.delivernow.gateways.mocks;

import com.ricardobevi.delivernow.dto.OrderDto;
import com.ricardobevi.delivernow.dto.RestaurantDto;
import com.ricardobevi.delivernow.gateways.ETAGateway;

public class MockedETAGateway implements ETAGateway {

	public static final String haedoCity = "-34.645914,-58.592202";
	public static final String ciudadelaHood = "-34.629359,-58.537554";
	
	public String etaString(RestaurantDto restaurantFrom, OrderDto orderTo) {
		return "Your order will arrive in 50 minutes";
	}

}
