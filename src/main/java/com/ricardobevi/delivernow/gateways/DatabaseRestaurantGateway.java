package com.ricardobevi.delivernow.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ricardobevi.delivernow.dto.RestaurantDto;
import com.ricardobevi.delivernow.gateways.exceptions.RestaurantNotFoundException;
import com.ricardobevi.delivernow.gateways.model.RestaurantDAO;
import com.ricardobevi.delivernow.gateways.model.RestaurantRepository;

public class DatabaseRestaurantGateway implements RestaurantGateway {

    @Autowired
    private RestaurantRepository restaurantRepository;
	
	public RestaurantDto getRestaurantFromId(Long restaurantId){
		RestaurantDAO restaurantDao = findRestaurantById(restaurantId);
		return restaurantDao.asDto();
	}

	@Transactional
	public void save(RestaurantDto restaurantDto) {
		RestaurantDAO restaurantDao = findRestaurantById(restaurantDto.getId());

		restaurantDao.updateWithDto(restaurantDto);
		
		restaurantRepository.save(restaurantDao);
	}

	@Transactional
	public void delete(RestaurantDto restaurantDto) {
		restaurantRepository.deleteById(restaurantDto.getId());
	}

	private RestaurantDAO findRestaurantById(Long restaurantId) {
		RestaurantDAO restaurantDao = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
		return restaurantDao;
	}
	
}
