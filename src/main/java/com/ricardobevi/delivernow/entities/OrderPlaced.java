package com.ricardobevi.delivernow.entities;

import com.ricardobevi.delivernow.dto.OrderStatusDto;

public class OrderPlaced implements OrderStatus {

	public OrderPlaced() {}

	public OrderStatusDto asDto() {
		return new OrderStatusDto("Order placed successfully", false);
	}
	
}
