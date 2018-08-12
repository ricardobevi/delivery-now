package com.ricardobevi.delivernow.usecase.addreview;

import com.ricardobevi.delivernow.entities.Restaurant;
import com.ricardobevi.delivernow.entities.Review;
import com.ricardobevi.delivernow.gateways.RestaurantGateway;

public class AddReviewUseCase {
	
	private final AddReviewUseCaseInput addReviewUseCaseInput;
	
	private final RestaurantGateway restaurantGateway;
	
	public AddReviewUseCase(AddReviewUseCaseInput addReviewUseCaseInput) {
		this.addReviewUseCaseInput = addReviewUseCaseInput;
		this.restaurantGateway = addReviewUseCaseInput.getRestaurantGateway();
	}

	public AddReviewUseCaseOutput execute() {
		
		Restaurant restaurant = new Restaurant(this.restaurantGateway.getRestaurantFromId( this.addReviewUseCaseInput.getRestaurantId() ));
		
		Review review = new Review(addReviewUseCaseInput.getReviewDto());
		
		restaurant.addReview(review);
		
		this.restaurantGateway.save(restaurant.asDto());
		
		AddReviewUseCaseOutput addReviewUseCaseOutput = new AddReviewUseCaseOutput(restaurant.asDto());
		
		return addReviewUseCaseOutput;
	}
}
