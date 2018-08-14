package com.ricardobevi.delivernow.gateways.mocks;

import com.ricardobevi.delivernow.dto.OrderDto;
import com.ricardobevi.delivernow.dto.RestaurantDto;
import com.ricardobevi.delivernow.gateways.MailGateway;

public class MockedMailGateway implements MailGateway {

	private String mailTo;
	private String body;
	
	public void send(RestaurantDto restaurantDto, OrderDto orderDto) {
		this.mailTo = restaurantDto.getCommercialEmail();
		this.body = "You have a new order! Address: " + orderDto.getAddress();
	}

	public String getMailTo() {
		return mailTo;
	}

	public String getBody() {
		return body;
	}


}
