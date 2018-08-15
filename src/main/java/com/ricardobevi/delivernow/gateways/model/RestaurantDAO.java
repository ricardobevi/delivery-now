package com.ricardobevi.delivernow.gateways.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ricardobevi.delivernow.dto.LatLongLocationDto;
import com.ricardobevi.delivernow.dto.RestaurantDto;

@Entity(name = "restaurant")
public class RestaurantDAO {
	
	@Id
	private Long id;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<ReviewDAO> reviews = new ArrayList<ReviewDAO>();

	@OneToMany(cascade=CascadeType.ALL)
	private List<MealDAO> meals = new ArrayList<MealDAO>();
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<OrderDAO> orders = new ArrayList<OrderDAO>();
	
	private String latLong;
	
	private String commercialEmail;
	
	private Double rating;
	
	private String logo;
	private String commercialName;
	private String legalName;
	private String adminNumber;
	private String address;
	
	public RestaurantDAO() {}
	
	public RestaurantDAO(
			Long id, 
			List<ReviewDAO> reviews, 
			List<MealDAO> meals, 
			String latLong,
			String commercialEmail,
			Double rating,
			
			String logo,
			String commercialName, 
			String legalName,
			String adminNumber, 
			String address) {
		this.id = id;
		this.reviews = reviews;
		this.meals = meals;
		this.latLong = latLong;
		this.commercialEmail = commercialEmail;
		this.rating = rating;
		
		this.logo = logo;
		this.commercialName = commercialName;
		this.legalName = legalName;
		this.adminNumber = adminNumber;
		this.address = address;
	}

	public Long getId() {
		return id;
	}
	
	public RestaurantDto asDto() {
		
		RestaurantDto restaurantDto = new RestaurantDto(
				id,
				rating,
				reviews.stream().map(ReviewDAO::asDto).collect(Collectors.toList()),
				meals.stream().map(MealDAO::asDto).collect(Collectors.toList()),
				orders.stream().map(OrderDAO::asDto).collect(Collectors.toList()),
				LatLongLocationDto.parseString(latLong),
				this.commercialEmail,
				
				this.logo,
				this.commercialName,
				this.legalName,
				this.adminNumber,
				this.address
		);
		
		return restaurantDto;
	}

	public void updateWithDto(RestaurantDto restaurantDto) {
		this.reviews = restaurantDto.getReviews().stream().map(reviewDto -> new ReviewDAO(reviewDto)).collect(Collectors.toList());
		this.meals = restaurantDto.getMeals().stream().map(mealDto -> new MealDAO(mealDto)).collect(Collectors.toList());
		this.orders = restaurantDto.getOrders().stream().map(orderDto -> new OrderDAO(orderDto)).collect(Collectors.toList());
	}
	
}