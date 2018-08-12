package com.ricardobevi.delivernow.entities;

import java.util.List;

public class Meal {

	private final String name;
	private final String description;
	private final Price price;
	
	public Meal(String name, String description, Price price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public static Price sumCosts(List<Meal> meals) {
		return meals.stream().map(meal -> meal.price).reduce(Price::sum).orElse(new Price(0.0));
	}

}
