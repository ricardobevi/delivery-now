package com.ricardobevi.delivernow.controllers.requests;

import java.util.List;
import java.util.stream.Collectors;

import com.ricardobevi.delivernow.dto.LatLongLocationDto;
import com.ricardobevi.delivernow.dto.RestaurantDto;

public class RestaurantRequest {

	private Long id;
	private Double rating;
	private List<ReviewRequest> reviews;
	private List<MealRequest> meals;
	private LatLongLocationDto location;
	private String commercialEmail;
	
	private String logo;
	private String commercialName;
	private String legalName;
	private String adminNumber;
	private String address;
	
	

	public RestaurantRequest(
			Long id, 
			Double rating, 
			List<ReviewRequest> reviews, 
			List<MealRequest> meals,
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
		this.location = location;
		this.commercialEmail = commercialEmail;
		this.logo = logo;
		this.commercialName = commercialName;
		this.legalName = legalName;
		this.adminNumber = adminNumber;
		this.address = address;
	}


	public RestaurantDto asDto() {
		
		RestaurantDto restaurantDto = new RestaurantDto(
				this.id,
				this.rating,
				this.reviews.stream().map(ReviewRequest::asDto).collect(Collectors.toList()),
				this.meals.stream().map(MealRequest::asDto).collect(Collectors.toList()),
				null, // @Smell: it should be a NoOrder class
				this.location,
				this.commercialEmail,
				
				this.logo,
				this.commercialName,
				this.legalName,
				this.adminNumber,
				this.address
		);
		
		return restaurantDto;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public List<ReviewRequest> getReviews() {
		return reviews;
	}
	public void setReviews(List<ReviewRequest> reviews) {
		this.reviews = reviews;
	}
	public List<MealRequest> getMeals() {
		return meals;
	}
	public void setMeals(List<MealRequest> meals) {
		this.meals = meals;
	}
	public LatLongLocationDto getLocation() {
		return location;
	}
	public void setLocation(LatLongLocationDto location) {
		this.location = location;
	}
	public String getCommercialEmail() {
		return commercialEmail;
	}
	public void setCommercialEmail(String commercialEmail) {
		this.commercialEmail = commercialEmail;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getCommercialName() {
		return commercialName;
	}
	public void setCommercialName(String commercialName) {
		this.commercialName = commercialName;
	}
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	public String getAdminNumber() {
		return adminNumber;
	}
	public void setAdminNumber(String adminNumber) {
		this.adminNumber = adminNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
}
