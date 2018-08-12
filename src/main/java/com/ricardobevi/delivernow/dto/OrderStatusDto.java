package com.ricardobevi.delivernow.dto;

public class OrderStatusDto {
	
	private final String status;

	public OrderStatusDto(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
