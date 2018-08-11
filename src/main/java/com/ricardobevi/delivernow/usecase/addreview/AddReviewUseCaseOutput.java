package com.ricardobevi.delivernow.usecase.addreview;

import com.ricardobevi.delivernow.dto.RestaurantDto;

public class AddReviewUseCaseOutput {
	private final RestaurantDto restaurantDto;

	public AddReviewUseCaseOutput(RestaurantDto restaurantDto) {
		this.restaurantDto = restaurantDto;
	}

	public RestaurantDto getRestaurantDto() {
		return restaurantDto;
	}
}
