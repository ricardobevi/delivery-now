package com.ricardobevi.delivernow.dto;

public class OrderStatusDto {
	
	private final Boolean error;
	private final String status;

	public OrderStatusDto(String status, Boolean error) {
		this.status = status;
		this.error = error;
	}

	public String getStatus() {
		return status;
	}

	public Boolean isError() {
		return error;
	}
	
	

}
