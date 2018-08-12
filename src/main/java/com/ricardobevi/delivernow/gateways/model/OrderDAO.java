package com.ricardobevi.delivernow.gateways.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
	
	
	public Long getId() {
		return id;
	}
	
	
}
