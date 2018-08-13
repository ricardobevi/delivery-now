package com.ricardobevi.delivernow.gateways;

import java.util.List;
import java.util.stream.Collectors;

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

	public List<RestaurantDto> listAll(){
		return restaurantRepository.findAll().stream().map(RestaurantDAO::asDto).collect(Collectors.toList());
	}
	
	public List<RestaurantDto> listAllWithRatingGreaterThan(Double minRating){
		return restaurantRepository.findByRatingGreaterThan(minRating).stream().map(RestaurantDAO::asDto).collect(Collectors.toList());
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
