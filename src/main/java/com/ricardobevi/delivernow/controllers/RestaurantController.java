package com.ricardobevi.delivernow.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ricardobevi.delivernow.controllers.requests.ErrorResponse;
import com.ricardobevi.delivernow.dto.RestaurantDto;
import com.ricardobevi.delivernow.gateways.RestaurantGateway;
import com.ricardobevi.delivernow.gateways.exceptions.RestaurantNotFoundException;

@RestController

public class RestaurantController {
	
	@Autowired
	RestaurantGateway restaurantGateway;
	
	@RequestMapping("/restaurant/{restaurantId}")
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
	
	@RequestMapping("/restaurant")
	@GetMapping
    public ResponseEntity<Object> listRestaurants() {

		List<RestaurantDto> restaurantsDtos = restaurantGateway.listAll();
		
		return ResponseEntity.ok(restaurantsDtos);
		
    }
	
	@RequestMapping(value = "/restaurant", params = "minRating")
	@GetMapping
    public ResponseEntity<Object> listRestaurants(@RequestParam("minRating") Double minRating) {

		List<RestaurantDto> restaurantsDtos = restaurantGateway.listAllWithRatingGreaterThan(minRating);
		
		return ResponseEntity.ok(restaurantsDtos);
		
    }
	
	
}
