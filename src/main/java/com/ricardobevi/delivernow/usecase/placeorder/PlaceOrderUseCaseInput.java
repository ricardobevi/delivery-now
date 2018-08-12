package com.ricardobevi.delivernow.usecase.placeorder;

import com.ricardobevi.delivernow.dto.OrderDto;
import com.ricardobevi.delivernow.gateways.RestaurantGateway;

public class PlaceOrderUseCaseInput {
	
	private final Long restaurantId;
	private final OrderDto orderDto;
	private final RestaurantGateway restaurantGateway;
	
	public PlaceOrderUseCaseInput(Long restaurantId, OrderDto orderDto, RestaurantGateway restaurantGateway) {
		this.restaurantId = restaurantId;
		this.orderDto = orderDto;
		this.restaurantGateway = restaurantGateway;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public OrderDto getOrderDto() {
		return orderDto;
	}

	public RestaurantGateway getRestaurantGateway() {
		return restaurantGateway;
	}


}
