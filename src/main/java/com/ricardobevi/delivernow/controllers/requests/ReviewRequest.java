package com.ricardobevi.delivernow.controllers.requests;

import com.ricardobevi.delivernow.controllers.requests.validations.MissingOrNullParameter;
import com.ricardobevi.delivernow.controllers.requests.validations.RatingOutOfBounds;
import com.ricardobevi.delivernow.controllers.requests.validations.RequestValidation;
import com.ricardobevi.delivernow.controllers.requests.validations.ValidationOk;
import com.ricardobevi.delivernow.dto.ReviewDto;

public class ReviewRequest implements Request {

	private String name;
	private String review;
	private Double rating;
	
	public ReviewRequest() {}
	

	public ReviewRequest(String name, String review, Double rating) {
		this.name = name;
		this.review = review;
		this.rating = rating;
	}
	
	public ReviewDto asDto() {
		return new ReviewDto(this.name, this.review, this.rating);
	}
	
	public RequestValidation validate() {
		if(name == null || review == null || rating == null ) {
			return new MissingOrNullParameter();
		} else if ( rating < 1.0 || rating > 5.0 ) {
			return new RatingOutOfBounds();
		}
		
		return new ValidationOk();
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


	public void setName(String name) {
		this.name = name;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
	
	
}
