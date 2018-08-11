package com.ricardobevi.delivernow.usecase;

import com.ricardobevi.delivernow.dto.RestaurantDto;
import com.ricardobevi.delivernow.dto.ReviewDto;
import com.ricardobevi.delivernow.gateways.RestaurantGateway;
import com.ricardobevi.delivernow.usecase.addreview.AddReviewUseCase;
import com.ricardobevi.delivernow.usecase.addreview.AddReviewUseCaseInput;
import com.ricardobevi.delivernow.usecase.addreview.AddReviewUseCaseOutput;
import org.junit.Assert;
import org.junit.Test;

public class RestaurantReviewUseCaseSpec {
	
	
	private static final ReviewDto createReviewDto() {
		
		ReviewDto reviewDto = new ReviewDto();
		
		reviewDto.name = "Amelie";
		reviewDto.review = "Me gusto mucho el restaurant, rica comida y buena atención.";
		reviewDto.rating = 4.5;
		
		return reviewDto;
		
	}

	@Test
	public void test_add_review_to_a_restaurant() {
		
		Long restaurantId = 123456L;
		
		AddReviewUseCaseInput addReviewUseCaseInput =
				new AddReviewUseCaseInput(restaurantId, createReviewDto(), new MockedRestaurantGateway());

		AddReviewUseCaseOutput addReviewUseCaseOutput =
				new AddReviewUseCase(addReviewUseCaseInput).execute();

		Assert.assertTrue(addReviewUseCaseOutput.getRestaurantDto().reviews.size() == 1);
	}
	

	private class MockedRestaurantGateway implements RestaurantGateway {

		
		public RestaurantDto getRestaurantFromId(Long restaurantId) {
			return new RestaurantDto();
		}


		public void save(RestaurantDto asDto) {
		}
		
	}
	
}
