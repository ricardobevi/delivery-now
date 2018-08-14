package com.ricardobevi.delivernow.gateways;

import com.ricardobevi.delivernow.dto.OrderDto;
import com.ricardobevi.delivernow.dto.RestaurantDto;

public interface SMSGateway {
	void sendSms(OrderDto orderDto, RestaurantDto restaurantDto);
}
