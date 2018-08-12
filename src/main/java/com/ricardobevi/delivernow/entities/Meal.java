package com.ricardobevi.delivernow.entities;

import java.util.List;

import com.ricardobevi.delivernow.dto.MealDto;

public class Meal {

	private final String name;
	private final String description;
	private final Price price;
	
	public Meal(String name, String description, Price price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public Meal(MealDto mealDto) {
		this.name = mealDto.getName();
		this.description = mealDto.getDescription();
		this.price = new Price(mealDto.getPrice());
	}

	public static Price sumCosts(List<Meal> meals) {
		return meals.stream().map(meal -> meal.price).reduce(Price::sum).orElse(new Price(0.0));
	}
	
	public MealDto asDto() {
		return new MealDto(name, description, price.asDouble());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Meal other = (Meal) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}
	
	

}
