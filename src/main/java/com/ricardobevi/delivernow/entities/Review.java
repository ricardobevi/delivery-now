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
		this(reviewDto.getName(), reviewDto.getReview(), new Rating(reviewDto.getRating()));
	}

	public Rating getRating() {
		return this.rating;
	}
	
	public ReviewDto asDto() {
		ReviewDto reviewDto = new ReviewDto(this.name, this.review, this.rating.asDouble());
		return reviewDto;
	}
	
}
