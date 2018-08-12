package com.ricardobevi.delivernow.controllers.requests;

import com.ricardobevi.delivernow.controllers.requests.validations.RequestValidation;
import com.ricardobevi.delivernow.dto.MealDto;

public class MealRequest implements Request{

	private String name;
	private String description;
	private Double price;
	
	
	public MealRequest(String name, String description, Double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public MealRequest() {}

	
	public RequestValidation validate() {
		// TODO Auto-generated method stub
		return null;
	}

	public MealDto asDto() {
		return new MealDto(name, description, price);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}



}
