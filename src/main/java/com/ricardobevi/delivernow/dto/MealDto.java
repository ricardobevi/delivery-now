package com.ricardobevi.delivernow.dto;

public class MealDto {
	private final String name;
	private final String description;
	private final Double price;
	
	public MealDto(String name, String description, Double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}
	
	
	
}
