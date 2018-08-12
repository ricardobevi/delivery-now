package com.ricardobevi.delivernow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ricardobevi.delivernow.controllers.requests.ErrorResponse;
import com.ricardobevi.delivernow.controllers.requests.ReviewRequest;
import com.ricardobevi.delivernow.controllers.requests.validations.Validation;
import com.ricardobevi.delivernow.gateways.RestaurantGateway;
import com.ricardobevi.delivernow.gateways.exceptions.RestaurantNotFoundException;
import com.ricardobevi.delivernow.usecase.addreview.AddReviewUseCase;
import com.ricardobevi.delivernow.usecase.addreview.AddReviewUseCaseInput;
import com.ricardobevi.delivernow.usecase.addreview.AddReviewUseCaseOutput;

@RestController
@RequestMapping("/review/{restaurantId}")
public class ReviewController {
	
	@Autowired
	RestaurantGateway restaurantGateway;
	
	@PostMapping
    public ResponseEntity<Object> review(@PathVariable Long restaurantId, @RequestBody ReviewRequest reviewRequest) {
		
		Validation validation = reviewRequest.validate();
		
		if(validation.isValid()) {
		
			AddReviewUseCaseInput addReviewUseCaseInput =
					new AddReviewUseCaseInput(restaurantId, reviewRequest.asDto(), restaurantGateway);
	
			try {
				
				AddReviewUseCaseOutput addReviewUseCaseOutput = new AddReviewUseCase(addReviewUseCaseInput).execute();
				
				return ResponseEntity.ok(addReviewUseCaseOutput.getRestaurantDto());
				
			} catch (RestaurantNotFoundException restaurantNotFoundException) {
				
				return ResponseEntity.badRequest().body(new ErrorResponse(restaurantNotFoundException.message()));
				
			}
			
			
			
		} else {
			return validation.response();
		}
		
		
    }
	
	
	
}
