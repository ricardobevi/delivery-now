package com.ricardobevi.delivernow.usecase.addreview;

import com.ricardobevi.delivernow.dto.ReviewDto;
import com.ricardobevi.delivernow.gateways.RestaurantGateway;

public class AddReviewUseCaseInput {

	private final Long restaurantId;
	private final ReviewDto reviewDto;
	private final RestaurantGateway restaurantGateway;
	
	
	public AddReviewUseCaseInput(Long restaurantId, ReviewDto reviewDto,
			RestaurantGateway restaurantGateway) {
		
		this.restaurantId = restaurantId;
		this.reviewDto = reviewDto;
		this.restaurantGateway = restaurantGateway;
		
	}


	public Long getRestaurantId() {
		return restaurantId;
	}


	public ReviewDto getReviewDto() {
		return reviewDto;
	}


	public RestaurantGateway getRestaurantGateway() {
		return restaurantGateway;
	}
	
	
}
