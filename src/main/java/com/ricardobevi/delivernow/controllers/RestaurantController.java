package com.ricardobevi.delivernow.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ricardobevi.delivernow.controllers.requests.ErrorResponse;
import com.ricardobevi.delivernow.controllers.requests.RestaurantRequest;
import com.ricardobevi.delivernow.dto.RestaurantDto;
import com.ricardobevi.delivernow.gateways.RestaurantGateway;
import com.ricardobevi.delivernow.gateways.exceptions.RestaurantNotFoundException;

@RestController
public class RestaurantController {
	
	@Autowired
	RestaurantGateway restaurantGateway;
	
	@RequestMapping(value = "/restaurant/{restaurantId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable Long restaurantId) {
		
		try {
		
			RestaurantDto restaurantDto = restaurantGateway.getRestaurantFromId(restaurantId);
			
			restaurantGateway.delete(restaurantDto);
			
			return ResponseEntity.ok(restaurantDto);
		
		} catch (RestaurantNotFoundException restaurantNotFoundException) {
			
			return ResponseEntity.badRequest().body(new ErrorResponse(restaurantNotFoundException.message()));
			
		}
		
		
    }
	

	@RequestMapping(value = "/restaurant", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@RequestBody RestaurantRequest restaurantRequest) {

		RestaurantDto restaurantDto = restaurantRequest.asDto();
		
		RestaurantDto savedRestaurantDto = restaurantGateway.save(restaurantDto);
		
		return ResponseEntity.ok(savedRestaurantDto);
		
    }

	@RequestMapping(value = "/restaurant", method = RequestMethod.GET)
    public ResponseEntity<Object> listRestaurants(@RequestParam(value = "minRating", required = false) Double minRating) {

		List<RestaurantDto> restaurantsDtos = new ArrayList<>();
		
		if (minRating != null) {
			restaurantsDtos = restaurantGateway.listAllWithRatingGreaterThan(minRating);
		} else {
			restaurantsDtos = restaurantGateway.listAll();
		}
		
		return ResponseEntity.ok(restaurantsDtos);
		
    }

	
}
