package com.ricardobevi.delivernow.dto;

import java.util.List;

public class OrderDto {
	
	private final List<MealDto> meals;
	private final Double totalCost;
	private final String address;
	private final LatLongLocationDto latLong;
	
	public OrderDto(List<MealDto> meals, Double totalCost, String address, LatLongLocationDto latLong) {
		this.meals = meals;
		this.totalCost = totalCost;
		this.address = address;
		this.latLong = latLong;
	}

	public List<MealDto> getMeals() {
		return meals;
	}
	
	public Double getTotalCost() {
		return totalCost;
	}

	public String getAddress() {
		return address;
	}

	public LatLongLocationDto getLatLong() {
		return latLong;
	}


}
