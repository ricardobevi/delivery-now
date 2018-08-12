package com.ricardobevi.delivernow.gateways.exceptions;

public class RestaurantNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -6698189442218484363L;
	
	private final Long restaurantId;
	
	public RestaurantNotFoundException(Long restaurantId) {
		this.restaurantId = restaurantId;
	}
	
	
	public String message() {
		return "Couldn't find restaurant with id: " + this.restaurantId;
	}

}
