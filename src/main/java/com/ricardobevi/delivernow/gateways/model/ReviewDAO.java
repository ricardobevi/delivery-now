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
    
    public ReviewDAO() {
    	this.name = "";
    	this.review = "";
    	this.rating = 1.0;
    }
    
	public ReviewDAO(ReviewDto reviewDto) {
		this.name = reviewDto.getName();
		this.review = reviewDto.getReview();
		this.rating = reviewDto.getRating();
	}

    
	public ReviewDto asDto() {
		
		ReviewDto reviewDto = new ReviewDto(this.name, this.review, this.rating);
		
		return reviewDto;
		
	}
    
}