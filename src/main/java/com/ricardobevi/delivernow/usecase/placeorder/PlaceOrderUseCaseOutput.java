package com.ricardobevi.delivernow.usecase.placeorder;

import com.ricardobevi.delivernow.dto.OrderStatusDto;

public class PlaceOrderUseCaseOutput {
	
	private final OrderStatusDto orderStatusDto;

	public PlaceOrderUseCaseOutput(OrderStatusDto orderStatusDto) {
		this.orderStatusDto = orderStatusDto;
	}

	public OrderStatusDto getOrderStatusDto() {
		return orderStatusDto;
	}

}
