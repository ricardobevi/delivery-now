package com.ricardobevi.delivernow.dto;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDto {
	
	public Long id;
	
	public Double rating;
	public List<ReviewDto> reviews;
	
	public RestaurantDto() {
		this.id = 0L;
		this.reviews = new ArrayList<ReviewDto>();
	}

	public RestaurantDto(Long id) {
		this.id = id;
	}
	
}
