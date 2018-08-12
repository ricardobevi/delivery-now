package com.ricardobevi.delivernow.entities;

import java.util.List;

public class Order {
	
	private final List<Meal> meals;
	private final Price totalCost;
	private final Address address; 
	private final LatLongLocation latLongLocation;
	
	
	public Order(List<Meal> meals, Price price, Address address, LatLongLocation latLongLocation) {
		this.meals = meals;
		this.totalCost = price;
		this.address = address;
		this.latLongLocation = latLongLocation;
	}


	public boolean canBeFullfilledWith(List<Meal> restaurantMeals) {
		return !meals.stream().filter(orderMeal -> !restaurantMeals.contains(orderMeal)).findFirst().isPresent();
	}

	public boolean checkPrice() {
		return Meal.sumCosts(meals).equals(totalCost);
	}

	
}
