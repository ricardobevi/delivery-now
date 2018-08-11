package com.ricardobevi.delivernow.gateways.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.ricardobevi.delivernow.dto.ReviewDto;

@Entity(name = "review")
public class ReviewDAO {
	
	@Id
	@GeneratedValue
	private Long id;
	
	
	private String name;
    private String review;
    private Double rating;
    
    public ReviewDAO() {}
    
    
	public ReviewDAO(String name, String review, Double rating) {
		this.name = name;
		this.review = review;
		this.rating = rating;
	}

	public ReviewDAO(ReviewDto reviewDto) {
		this.name = reviewDto.name;
		this.review = reviewDto.review;
		this.rating = reviewDto.rating;
	}


	public String getName() {
		return name;
	}
	public String getReview() {
		return review;
	}
	public Double getRating() {
		return rating;
	}
	
    
	public ReviewDto asDto() {
		
		ReviewDto reviewDto = new ReviewDto(this.name, this.review, this.rating);
		
		return reviewDto;
		
	}
    
}