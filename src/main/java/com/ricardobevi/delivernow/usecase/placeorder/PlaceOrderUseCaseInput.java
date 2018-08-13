package com.ricardobevi.delivernow.usecase.placeorder;

import com.ricardobevi.delivernow.dto.OrderDto;
import com.ricardobevi.delivernow.gateways.ETAGateway;
import com.ricardobevi.delivernow.gateways.RestaurantGateway;

public class PlaceOrderUseCaseInput {
	
	private final Long restaurantId;
	private final OrderDto orderDto;
	private final RestaurantGateway restaurantGateway;
	private final ETAGateway etaGateway;
	
	public PlaceOrderUseCaseInput(Long restaurantId, OrderDto orderDto, RestaurantGateway restaurantGateway, ETAGateway etaGateway) {
		this.restaurantId = restaurantId;
		this.orderDto = orderDto;
		this.restaurantGateway = restaurantGateway;
		this.etaGateway = etaGateway;
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

	public ETAGateway getEtaGateway() {
		return etaGateway;
	}

	

}
