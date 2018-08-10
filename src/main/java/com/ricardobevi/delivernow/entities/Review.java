package com.ricardobevi.delivernow.entities;

public class Review {

	private final String name;
	private final String review;
	private final Rating rating;
	

	public Review(String name, String review, Rating rating) {
		this.name = name;
		this.review = review;
		this.rating = rating;
	}
	
	public Rating getRating() {
		return this.rating;
	}
	
}
