package com.ricardobevi.delivernow.entities;

import java.util.List;
import java.util.stream.Collectors;

import com.ricardobevi.delivernow.dto.OrderDto;

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


	public Order(OrderDto orderDto) {
		this.meals = orderDto.getMeals().stream().map(mealDto -> new Meal(mealDto)).collect(Collectors.toList());
		this.totalCost = new Price(orderDto.getTotalCost());
		this.address = new Address(orderDto.getAddress());
		this.latLongLocation = new LatLongLocation(orderDto.getLatLong());
	}

	public OrderDto asDto() {
		return new OrderDto(
				this.meals.stream().map(Meal::asDto).collect(Collectors.toList()),
				this.totalCost.asDouble(),
				this.address.toString(),
				this.latLongLocation.asDto()
		);
	}

	public boolean canBeFullfilledWith(List<Meal> restaurantMeals) {
		return !meals.stream().filter(orderMeal -> !restaurantMeals.contains(orderMeal)).findFirst().isPresent();
	}

	public boolean checkPrice() {
		return Meal.sumCosts(meals).equals(totalCost);
	}

	
}
