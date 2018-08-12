package com.ricardobevi.delivernow.entities;

import java.util.Collection;

public class Rating {

	private Double rating;
	
	public Rating(Double rating) {
		this.rating = rating;
	}
	
	public static final Rating average(Collection<Rating> ratings) {
		return new Rating( ratings.stream().mapToDouble(Rating::getRating).average().orElse(0.0) );
	}
	
	private Double getRating() {
		return this.rating;
	}
	
	public Double asDouble() {
		return this.rating;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rating other = (Rating) obj;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		return true;
	}
	
	

}
