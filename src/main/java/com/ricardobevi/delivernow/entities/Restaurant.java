package com.ricardobevi.delivernow.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ricardobevi.delivernow.dto.RestaurantDto;

public class Restaurant {

	private final Long restaurantId;
	private List<Review> reviewList;
	
	public Restaurant() {
		restaurantId = null;
		this.reviewList = new ArrayList<Review>();
	}
	
	public Restaurant(RestaurantDto restaurantDto) {
		this.restaurantId = restaurantDto.getId();
		this.reviewList = restaurantDto.getReviews().stream().map(reviewDto -> new Review(reviewDto)).collect(Collectors.toList());
	}

	public void addReview(Review review) {
		reviewList.add(review);
	}

	public int countReviews() {
		return reviewList.size();
	}

	public Rating rating() { 
		return Rating.average( reviewList.stream().map(Review::getRating).collect(Collectors.toList()) );
	}

	public RestaurantDto asDto() {
		RestaurantDto restaurantDto = new RestaurantDto(
				this.restaurantId,
				this.rating().asDouble(),
				reviewList.stream().map(Review::asDto).collect(Collectors.toList())
		);
		
		return restaurantDto;
	}  

}
