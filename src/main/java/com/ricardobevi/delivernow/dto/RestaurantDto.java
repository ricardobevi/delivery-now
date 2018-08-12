package com.ricardobevi.delivernow.dto;

import java.util.List;

public class RestaurantDto {
	
	private final Long id;
	private final Double rating;
	private final List<ReviewDto> reviews;
	
	public RestaurantDto(Long id, Double rating, List<ReviewDto> reviews) {
		this.id = id;
		this.rating = rating;
		this.reviews = reviews;
	}

	public Long getId() {
		return id;
	}

	public Double getRating() {
		return rating;
	}

	public List<ReviewDto> getReviews() {
		return reviews;
	}
	
}
