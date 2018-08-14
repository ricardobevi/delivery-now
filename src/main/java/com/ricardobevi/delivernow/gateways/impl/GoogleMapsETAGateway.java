package com.ricardobevi.delivernow.gateways.impl;

import java.io.IOException;

import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.Unit;
import com.ricardobevi.delivernow.dto.OrderDto;
import com.ricardobevi.delivernow.dto.RestaurantDto;
import com.ricardobevi.delivernow.gateways.ETAGateway;

public class GoogleMapsETAGateway implements ETAGateway {

	public String etaString(RestaurantDto restaurantFrom, OrderDto orderTo) {
		
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey("AIzaSyDTTZ4sqXSa9aYHFkpwQvTJv_A7o9fotdE")
			    .queryRateLimit(1)
			    .disableRetries()
			    .build();
		
		DistanceMatrixApiRequest request = 
				new DistanceMatrixApiRequest(context)
				.origins(restaurantFrom.getLocation().toString())
				.destinations(orderTo.getLatLong().toString())
				.units(Unit.METRIC);
		
		
		try {
			
			DistanceMatrix distanceMatrix = request.await();
			
			String time = distanceMatrix.rows[0].elements[0].duration.humanReadable;
			
			return "Your order will arrive in " + time;
			
		} catch (ApiException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}

	
}
