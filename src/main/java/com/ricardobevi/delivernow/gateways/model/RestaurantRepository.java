package com.ricardobevi.delivernow.gateways.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<RestaurantDAO, Long>{
	
	List<RestaurantDAO> findByRatingGreaterThan(Double minRating);
	
}
