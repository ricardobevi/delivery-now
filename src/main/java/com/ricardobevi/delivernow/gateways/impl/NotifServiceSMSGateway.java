package com.ricardobevi.delivernow.gateways.impl;

import com.ricardobevi.delivernow.dto.OrderDto;
import com.ricardobevi.delivernow.dto.RestaurantDto;
import com.ricardobevi.delivernow.gateways.SMSGateway;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class NotifServiceSMSGateway implements SMSGateway {
	public void sendSms(OrderDto orderDto, RestaurantDto restaurantDto) {
		RestTemplate restTemplate = new RestTemplate();

		try{
			restTemplate.postForEntity(
					"http://notifications-api:8082/sms",
					new HashMap<String, Object>(){
						private static final long serialVersionUID = -8458123647449259079L;
					{
						put("number","213123123131");
						put("text","Your order has been placed!");
					}},
					Map.class
			);
		} catch (Exception e){
			e.printStackTrace();
		}

	}
}
