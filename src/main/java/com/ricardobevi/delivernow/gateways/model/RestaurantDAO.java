package com.ricardobevi.delivernow.gateways.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ricardobevi.delivernow.dto.RestaurantDto;

@Entity(name = "restaurant")
public class RestaurantDAO {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<ReviewDAO> reviews = new ArrayList<ReviewDAO>();

	public RestaurantDAO() {}

	public Long getId() {
		return id;
	}
	
	public RestaurantDAO setReviews(List<ReviewDAO> reviews) {
		this.reviews = reviews;
		return this;
	}
	
	
	public RestaurantDto asDto() {
		
		RestaurantDto restaurantDto = new RestaurantDto(id);
		
		restaurantDto.reviews = reviews.stream().map(ReviewDAO::asDto).collect(Collectors.toList());
		
		return restaurantDto;
	}

	public void updateWithDto(RestaurantDto restaurantDto) {
		this.id = restaurantDto.id;
		this.reviews = restaurantDto.reviews.stream().map(reviewDto -> new ReviewDAO(reviewDto)).collect(Collectors.toList());
	}
	
}


/*
COMPLETE MODEL

{
     id : any
logo : text (url)
commercialName : text
legalName : text
rating : (float max 5 min 1)
reviews : [
{
name : text
review : text
      rating : number
}
]
meals : [
{
name : text
description : text
price : float
}
]
     commercialEmail : text
     adminNumber : text
     address : text
     Location : latLng
}

*/