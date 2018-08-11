package com.ricardobevi.delivernow.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.MissingRequiredPropertiesException;

import com.ricardobevi.delivernow.dto.RestaurantDto;
import com.ricardobevi.delivernow.gateways.model.RestaurantDAO;
import com.ricardobevi.delivernow.gateways.model.RestaurantRepository;

public class DatabaseRestaurantGateway implements RestaurantGateway {

    @Autowired
    private RestaurantRepository restaurantRepository;
	
	public RestaurantDto getRestaurantFromId(Long restaurantId){
		RestaurantDAO restaurantDao = restaurantRepository.findById(restaurantId).orElseThrow(() -> new MissingRequiredPropertiesException());
		return restaurantDao.asDto();
	}

	public void save(RestaurantDto restaurantDto) {
		RestaurantDAO restaurantDao = restaurantRepository.findById(restaurantDto.id).orElse(new RestaurantDAO());

		restaurantDao.updateWithDto(restaurantDto);
		
		restaurantRepository.save(restaurantDao);
	}

}
