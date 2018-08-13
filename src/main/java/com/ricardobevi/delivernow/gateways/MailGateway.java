package com.ricardobevi.delivernow.gateways;

import com.ricardobevi.delivernow.dto.OrderDto;
import com.ricardobevi.delivernow.dto.RestaurantDto;

public interface MailGateway {

	void send(RestaurantDto restaurantDto, OrderDto orderDto);
 
}
