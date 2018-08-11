package com.ricardobevi.delivernow.dto;

public class ReviewDto {

	public String name;
	public String review;
	public Double rating;
	
	public ReviewDto() {}
	
	public ReviewDto(String name, String review, Double rating) {
		this.name = name;
		this.review = review;
		this.rating = rating;
	}
	
	
	
}
