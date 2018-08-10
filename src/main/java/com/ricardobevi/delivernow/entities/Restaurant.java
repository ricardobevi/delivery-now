package com.ricardobevi.delivernow.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Restaurant {

	private List<Review> reviewList;
	
	public Restaurant() {
		this.reviewList = new ArrayList<Review>();
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

}
