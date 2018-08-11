package com.ricardobevi.delivernow.entities;

import com.ricardobevi.delivernow.dto.ReviewDto;

public class Review {

	private final String name;
	private final String review;
	private final Rating rating;
	

	public Review(String name, String review, Rating rating) {
		this.name = name;
		this.review = review;
		this.rating = rating;
	}
	
	public Review(ReviewDto reviewDto) {
		this(reviewDto.name, reviewDto.review, new Rating(reviewDto.rating));
	}

	public Rating getRating() {
		return this.rating;
	}
	
	public ReviewDto asDto() {
		ReviewDto reviewDto = new ReviewDto();
		
		reviewDto.name = this.name;
		reviewDto.review = this.review;
		reviewDto.rating = this.rating.asDouble();
		
		return reviewDto;
	}
	
}
