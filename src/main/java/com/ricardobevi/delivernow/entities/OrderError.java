package com.ricardobevi.delivernow.entities;

import com.ricardobevi.delivernow.dto.OrderStatusDto;

public class OrderError implements OrderStatus {

	public OrderError() {}
	

	public OrderStatusDto asDto() {
		return new OrderStatusDto("The restaurant can't fullfill the order", true);
	}

	@Override
	public OrderStatusDto asDto(String eta) {
		return this.asDto();
	}


	@Override
	public Boolean isOk() {
		return false;
	}
	
}
