package com.ricardobevi.delivernow.gateways.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.ricardobevi.delivernow.dto.MealDto;

@Entity(name = "meal")
public class MealDAO {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private String description;
	private Double price;
	
	public MealDAO(MealDto mealDto) {
		this.name = mealDto.getName();
		this.description = mealDto.getDescription();
		this.price = mealDto.getPrice();
	}

	public MealDto asDto() {
		return new MealDto(name, description, price);
	}
}
