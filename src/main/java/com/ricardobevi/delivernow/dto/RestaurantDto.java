package com.ricardobevi.delivernow.dto;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDto {
	public List<ReviewDto> reviews;
	
	public RestaurantDto() {
		this.reviews = new ArrayList<ReviewDto>();
	}
	
}
