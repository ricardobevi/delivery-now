package com.ricardobevi.delivernow.dto;

public class ReviewDto {

	private final String name;
	private final String review;
	private final Double rating;
	
	public ReviewDto(String name, String review, Double rating) {
		this.name = name;
		this.review = review;
		this.rating = rating;
	}

	public String getName() {
		return name;
	}

	public Double getRating() {
		return rating;
	}

	public String getReview() {
		return review;
	}
	
}
