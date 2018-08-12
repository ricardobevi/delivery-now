package com.ricardobevi.delivernow.dto;

import java.util.List;

public class RestaurantDto {
	
	private final Long id;
	private final Double rating;
	private final List<ReviewDto> reviews;
	private final List<MealDto> meals;
	
	public RestaurantDto(Long id, Double rating, List<ReviewDto> reviews, List<MealDto> meals) {
		this.id = id;
		this.rating = rating;
		this.reviews = reviews;
		this.meals = meals;
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

	public List<MealDto> getMeals() {
		return meals;
	}
	
}
