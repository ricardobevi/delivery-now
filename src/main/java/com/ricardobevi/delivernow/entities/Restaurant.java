package com.ricardobevi.delivernow.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ricardobevi.delivernow.dto.RestaurantDto;

public class Restaurant {

	private final Long restaurantId;
	
	private List<Review> reviews;
	private List<Order> orders;
	private List<Meal> meals;
	private LatLongLocation location;
	private String commercialEmail;
	
	public Restaurant(List<Meal> meals) {
		restaurantId = null;
		this.reviews = new ArrayList<Review>();
		this.orders = new ArrayList<Order>();
		this.meals = meals;
	}
	
	public Restaurant(RestaurantDto restaurantDto) {
		this.restaurantId = restaurantDto.getId();
		this.reviews = restaurantDto.getReviews().stream().map(reviewDto -> new Review(reviewDto)).collect(Collectors.toList());
		this.meals = restaurantDto.getMeals().stream().map(mealDto -> new Meal(mealDto)).collect(Collectors.toList());
		this.orders = new ArrayList<Order>();
		this.location = new LatLongLocation(restaurantDto.getLocation());
	}

	public void addReview(Review review) {
		reviews.add(review);
	}

	public int countReviews() {
		return reviews.size();
	}

	public Rating rating() { 
		return Rating.average( reviews.stream().map(Review::getRating).collect(Collectors.toList()) );
	}
	
	public OrderStatus placeOrder(Order order) {
		
		if( order.canBeFullfilledWith(meals) && order.checkPrice() ) {
			
			this.orders.add(order);
			return new OrderPlaced();
			
		} else {
			
			return new OrderError();
			
		}
		
	}

	public RestaurantDto asDto() {
		RestaurantDto restaurantDto = new RestaurantDto(
				this.restaurantId,
				this.rating().asDouble(),
				reviews.stream().map(Review::asDto).collect(Collectors.toList()),
				meals.stream().map(Meal::asDto).collect(Collectors.toList()),
				orders.stream().map(Order::asDto).collect(Collectors.toList()),
				this.location.asDto(),
				this.commercialEmail
		);
		
		return restaurantDto;
	}




}
