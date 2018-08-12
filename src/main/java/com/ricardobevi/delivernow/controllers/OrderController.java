package com.ricardobevi.delivernow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ricardobevi.delivernow.controllers.requests.ErrorResponse;
import com.ricardobevi.delivernow.controllers.requests.OrderRequest;
import com.ricardobevi.delivernow.controllers.requests.validations.RequestValidation;
import com.ricardobevi.delivernow.gateways.RestaurantGateway;
import com.ricardobevi.delivernow.gateways.exceptions.RestaurantNotFoundException;
import com.ricardobevi.delivernow.usecase.placeorder.PlaceOrderUseCase;
import com.ricardobevi.delivernow.usecase.placeorder.PlaceOrderUseCaseInput;
import com.ricardobevi.delivernow.usecase.placeorder.PlaceOrderUseCaseOutput;

@RestController
@RequestMapping("/order/{restaurantId}")
public class OrderController {

	@Autowired
	RestaurantGateway restaurantGateway;

	@PostMapping
	public ResponseEntity<Object> placeOrder(@PathVariable Long restaurantId, @RequestBody OrderRequest orderRequest) {

		RequestValidation requestValidation = orderRequest.validate();

		if (requestValidation.isValid()) {
			
			PlaceOrderUseCaseInput placeOrderUseCaseinput = new PlaceOrderUseCaseInput(
					restaurantId, 
					orderRequest.asDto(), 
					restaurantGateway
			);

			try {
				
				PlaceOrderUseCaseOutput placeOrderUseCaseOutput = new PlaceOrderUseCase(placeOrderUseCaseinput).execute();
				
				return ResponseEntity.ok(placeOrderUseCaseOutput.getOrderStatusDto());

			} catch (RestaurantNotFoundException restaurantNotFoundException) {

				return ResponseEntity.badRequest().body(new ErrorResponse(restaurantNotFoundException.message()));

			}
			
		} else {
			return requestValidation.response();
		}

	}

}
