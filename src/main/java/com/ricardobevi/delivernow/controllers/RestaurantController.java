package com.ricardobevi.delivernow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ricardobevi.delivernow.controllers.requests.ErrorResponse;
import com.ricardobevi.delivernow.dto.RestaurantDto;
import com.ricardobevi.delivernow.gateways.RestaurantGateway;
import com.ricardobevi.delivernow.gateways.exceptions.RestaurantNotFoundException;

@RestController
@RequestMapping("/restaurant/{restaurantId}")
public class RestaurantController {
	
	@Autowired
	RestaurantGateway restaurantGateway;
	
	@DeleteMapping
    public ResponseEntity<Object> delete(@PathVariable Long restaurantId) {
		
		try {
		
			RestaurantDto restaurantDto = restaurantGateway.getRestaurantFromId(restaurantId);
			
			restaurantGateway.delete(restaurantDto);
			
			return ResponseEntity.ok(restaurantDto);
		
		} catch (RestaurantNotFoundException restaurantNotFoundException) {
			
			return ResponseEntity.badRequest().body(new ErrorResponse(restaurantNotFoundException.message()));
			
		}
		
		
    }
	
	
	
}
