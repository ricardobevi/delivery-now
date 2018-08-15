package com.ricardobevi.delivernow.gateways.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.ricardobevi.delivernow.dto.OrderDto;
import com.ricardobevi.delivernow.dto.RestaurantDto;
import com.ricardobevi.delivernow.gateways.MailGateway;

public class NotifServiceEmailGateway implements MailGateway {
	
	@Override
	public void send(RestaurantDto restaurantDto, OrderDto orderDto) {
		RestTemplate restTemplate = new RestTemplate();

		try{
			restTemplate.postForEntity(
					"http://notifications-api:8082/sms",
					new HashMap<String, Object>(){
						private static final long serialVersionUID = -6111094839696126354L;
					{
						put("to",restaurantDto.getCommercialEmail());
						put("subject", "New Order!");
						put("body", "You have a new order! Address: " + orderDto.getAddress());
					}},
					Map.class
			);
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
}
