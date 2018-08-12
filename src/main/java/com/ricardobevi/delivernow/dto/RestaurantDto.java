package com.ricardobevi.delivernow.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RestaurantDto {
	
	private final Long id;
	private final Double rating;
	private final List<ReviewDto> reviews;
	private final List<MealDto> meals;
	
	@JsonIgnore
	private final List<OrderDto> orders;
	
	public RestaurantDto(Long id, Double rating, List<ReviewDto> reviews, List<MealDto> meals, List<OrderDto> orders) {
		this.id = id;
		this.rating = rating;
		this.reviews = reviews;
		this.meals = meals;
		this.orders = orders;
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

	public List<OrderDto> getOrders() {
		return orders;
	}
	
}
