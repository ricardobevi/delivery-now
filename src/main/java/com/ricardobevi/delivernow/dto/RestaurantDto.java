package com.ricardobevi.delivernow.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RestaurantDto {
	
	private final Long id;
	private final Double rating;
	private final List<ReviewDto> reviews;
	private final List<MealDto> meals;
	private final LatLongLocationDto location;
	private final String commercialEmail;
	
	private final String logo;
	private final String commercialName;
	private final String legalName;
	private final String adminNumber;
	private final String address;
	
	
	@JsonIgnore
	private final List<OrderDto> orders;
	
	public RestaurantDto(
			Long id, 
			Double rating, 
			List<ReviewDto> reviews, 
			List<MealDto> meals, 
			List<OrderDto> orders,
			LatLongLocationDto location, 
			String commercialEmail,
			
			String logo,
			String commercialName, 
			String legalName,
			String adminNumber, 
			String address) {
		this.id = id;
		this.rating = rating;
		this.reviews = reviews;
		this.meals = meals;
		this.orders = orders;
		this.location = location;
		this.commercialEmail = commercialEmail;
		
		this.logo = logo;
		this.commercialName = commercialName;
		this.legalName = legalName;
		this.adminNumber = adminNumber;
		this.address = address;
	}



	public Long getId() {
		return id;
	}

	public Double getRating() {
		return rating;
	}

	public List<ReviewDto> getReviews() {
		return reviews;
	}

	public List<MealDto> getMeals() {
		return meals;
	}

	public List<OrderDto> getOrders() {
		Optional<List<OrderDto>> ordersOptional = Optional.ofNullable(orders);
		return ordersOptional.orElse(new ArrayList<OrderDto>());
	}

	public LatLongLocationDto getLocation() {
		return location;
	}

	public String getCommercialEmail() {
		return commercialEmail;
	}



	public String getLogo() {
		return logo;
	}



	public String getCommercialName() {
		return commercialName;
	}



	public String getLegalName() {
		return legalName;
	}



	public String getAdminNumber() {
		return adminNumber;
	}



	public String getAddress() {
		return address;
	}
	
	
	
}
