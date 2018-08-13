package com.ricardobevi.delivernow.entities;

import com.ricardobevi.delivernow.dto.OrderStatusDto;

public class OrderPlaced implements OrderStatus {

	public OrderPlaced() {}

	public OrderStatusDto asDto() {
		return new OrderStatusDto("Order placed successfully", false);
	}

	@Override
	public OrderStatusDto asDto(String eta) {
		return new OrderStatusDto("Order placed successfully", false, eta);
	}

	@Override
	public Boolean isOk() {
		return true;
	}
	
}
