package com.ricardobevi.delivernow.gateways.model;

import java.util.ArrayList;
import java.util.List;
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

	@OneToMany(cascade=CascadeType.ALL)
	private List<MealDAO> meals = new ArrayList<MealDAO>();
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<OrderDAO> orders = new ArrayList<OrderDAO>();
	
	public RestaurantDAO() {}
	
	public RestaurantDAO(Long id, List<ReviewDAO> reviews, List<MealDAO> meals, List<OrderDAO> orders) {
		this.id = id;
		this.reviews = reviews;
		this.meals = meals;
		this.orders = orders;
	}



	public Long getId() {
		return id;
	}
	
	public RestaurantDto asDto() {
		
		RestaurantDto restaurantDto = new RestaurantDto(
				id,
				1.0,
				reviews.stream().map(ReviewDAO::asDto).collect(Collectors.toList()),
				meals.stream().map(MealDAO::asDto).collect(Collectors.toList())
		);
		
		return restaurantDto;
	}

	public void updateWithDto(RestaurantDto restaurantDto) {
		this.reviews = restaurantDto.getReviews().stream().map(reviewDto -> new ReviewDAO(reviewDto)).collect(Collectors.toList());
		this.meals = restaurantDto.getMeals().stream().map(mealDto -> new MealDAO(mealDto)).collect(Collectors.toList());
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