package com.ricardobevi.delivernow.gateways.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<RestaurantDAO, Long>{
	
}
