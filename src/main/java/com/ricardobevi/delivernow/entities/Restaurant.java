package com.ricardobevi.delivernow.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.ricardobevi.delivernow.dto.RestaurantDto;

public class Restaurant {

	private List<Review> reviewList;
	
	public Restaurant() {
		this.reviewList = new ArrayList<Review>();
	}
	
	public Restaurant(RestaurantDto restaurantDto) {
		this.reviewList = restaurantDto.reviews.stream().map(reviewDto -> new Review(reviewDto)).collect(Collectors.toList());
	}

	public void addReview(Review review) {
		reviewList.add(review);
	}

	public int countReviews() {
		return reviewList.size();
	}

	public Rating computeAverageRating() { 
		return Rating.average( reviewList.stream().map(Review::getRating).collect(Collectors.toList()) );
	}

	public RestaurantDto asDto() {
		RestaurantDto restaurantDto = new RestaurantDto();
		
		restaurantDto.rating = this.computeAverageRating().asDouble();
		restaurantDto.reviews = reviewList.stream().map(Review::asDto).collect(Collectors.toList());
		
		return restaurantDto;
	}  

}
