package com.ricardobevi.delivernow.usecase;

import org.junit.Assert;
import org.junit.Test;

import com.ricardobevi.delivernow.dto.ReviewDto;
import com.ricardobevi.delivernow.mocks.MockedRestaurantGateway;
import com.ricardobevi.delivernow.usecase.addreview.AddReviewUseCase;
import com.ricardobevi.delivernow.usecase.addreview.AddReviewUseCaseInput;
import com.ricardobevi.delivernow.usecase.addreview.AddReviewUseCaseOutput;

public class RestaurantReviewUseCaseSpec {
	
	
	private static final ReviewDto createReviewDto() {
		ReviewDto reviewDto = new ReviewDto("Amelie", "Me gusto mucho el restaurant, rica comida y buena atención.", 4.5);
		return reviewDto;
	}

	@Test
	public void test_add_review_to_a_restaurant() {
		
		Long restaurantId = 123456L;
		
		AddReviewUseCaseInput addReviewUseCaseInput =
				new AddReviewUseCaseInput(restaurantId, createReviewDto(), new MockedRestaurantGateway());

		AddReviewUseCaseOutput addReviewUseCaseOutput =
				new AddReviewUseCase(addReviewUseCaseInput).execute();

		Assert.assertTrue(addReviewUseCaseOutput.getRestaurantDto().getReviews().size() == 2);
	}
	


	
}
