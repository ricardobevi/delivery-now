package com.ricardobevi.delivernow.dto;

public class OrderStatusDto {
	
	private final Boolean error;
	private final String status;
	private String eta;

	public OrderStatusDto(String status, Boolean error) {
		this.status = status;
		this.error = error;
	}
	
	public OrderStatusDto(String status, Boolean error, String eta) {
		this.status = status;
		this.error = error;
		this.eta = eta;
	}

	public String getStatus() {
		return status;
	}

	public Boolean isError() {
		return error;
	}

	public String getEta() {
		return eta;
	}
	
	

}
