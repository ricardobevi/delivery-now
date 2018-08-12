package com.ricardobevi.delivernow.gateways.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.ricardobevi.delivernow.dto.LatLongLocationDto;
import com.ricardobevi.delivernow.dto.OrderDto;

@Entity(name = "delivery_order")
public class OrderDAO {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToMany(cascade=CascadeType.ALL, targetEntity = MealDAO.class)
	private List<MealDAO> meals = new ArrayList<MealDAO>();
	
	private Double totalCost;
	private String address;
	private Double latitude;
	private Double longitude;
	
	public OrderDAO() {}
	
	public OrderDAO(OrderDto orderDto) {
		this.meals = orderDto.getMeals().stream().map(mealDto -> new MealDAO(mealDto)).collect(Collectors.toList());
		this.totalCost = orderDto.getTotalCost();
		this.address = orderDto.getAddress();
		this.latitude = orderDto.getLatLong().getLatitude();
		this.latitude = orderDto.getLatLong().getLongitude();
	}

	public OrderDto asDto() {
		return new OrderDto(
				meals.stream().map(MealDAO::asDto).collect(Collectors.toList()),
				totalCost,
				address,
				new LatLongLocationDto(latitude, longitude)
		);
	} 
	
}
