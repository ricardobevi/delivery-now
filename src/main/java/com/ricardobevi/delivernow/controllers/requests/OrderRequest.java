package com.ricardobevi.delivernow.controllers.requests;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.ricardobevi.delivernow.controllers.requests.validations.MissingOrNullParameter;
import com.ricardobevi.delivernow.controllers.requests.validations.RequestValidation;
import com.ricardobevi.delivernow.controllers.requests.validations.ValidationOk;
import com.ricardobevi.delivernow.controllers.requests.validations.WrongCoordinates;
import com.ricardobevi.delivernow.dto.LatLongLocationDto;
import com.ricardobevi.delivernow.dto.OrderDto;

public class OrderRequest implements Request {

	private List<MealRequest> meals;
	private Double totalCost;
	private String address;
	private String latLong;
	
	public OrderRequest() {}

	
	public OrderRequest(List<MealRequest> meals, Double totalCost, String address, String latLong) {
		this.meals = meals;
		this.totalCost = totalCost;
		this.address = address;
		this.latLong = latLong;
	}


	public RequestValidation validate() {
		
		if(meals == null || totalCost == null || address == null || latLong == null ) {
			return new MissingOrNullParameter();
		} else if ( !latLong.matches("^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?),\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$") ) {
			return new WrongCoordinates();
		}
		
		return new ValidationOk();
	}
	
	public OrderDto asDto() {
		
		List<Double> latLongDouble = Arrays.asList(latLong.split(",")).stream().map(s -> Double.parseDouble(s)).collect(Collectors.toList());
		
		return new OrderDto(
				meals.stream().map(MealRequest::asDto).collect(Collectors.toList()),
				totalCost,
				address,
				new LatLongLocationDto(latLongDouble.get(0), latLongDouble.get(1))
		);
		
	}

	public List<MealRequest> getMeals() {
		return meals;
	}

	public void setMeals(List<MealRequest> meals) {
		this.meals = meals;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLatLong() {
		return latLong;
	}

	public void setLatLong(String latLong) {
		this.latLong = latLong;
	}



}
