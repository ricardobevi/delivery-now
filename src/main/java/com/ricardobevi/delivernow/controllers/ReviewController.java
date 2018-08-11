package com.ricardobevi.delivernow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ricardobevi.delivernow.dto.ReviewDto;
import com.ricardobevi.delivernow.gateways.RestaurantGateway;
import com.ricardobevi.delivernow.usecase.addreview.AddReviewUseCase;
import com.ricardobevi.delivernow.usecase.addreview.AddReviewUseCaseInput;
import com.ricardobevi.delivernow.usecase.addreview.AddReviewUseCaseOutput;

@RestController
@RequestMapping("/review/{restaurantId}")
public class ReviewController {
	
	@Autowired
	RestaurantGateway restaurantGateway;
	
	@PostMapping
    public ResponseEntity<Object> review(@PathVariable Long restaurantId, @RequestBody ReviewDto reviewDto) {
		
		AddReviewUseCaseInput addReviewUseCaseInput =
				new AddReviewUseCaseInput(restaurantId, reviewDto, restaurantGateway);

		AddReviewUseCaseOutput addReviewUseCaseOutput = new AddReviewUseCase(addReviewUseCaseInput).execute();
		
		return ResponseEntity.ok(addReviewUseCaseOutput.getRestaurantDto());
    }
	
	
	
}
