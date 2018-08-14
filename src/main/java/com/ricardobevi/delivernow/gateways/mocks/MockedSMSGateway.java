package com.ricardobevi.delivernow.gateways.mocks;

import com.ricardobevi.delivernow.dto.OrderDto;
import com.ricardobevi.delivernow.dto.RestaurantDto;
import com.ricardobevi.delivernow.gateways.SMSGateway;

public class MockedSMSGateway implements SMSGateway {


	private String sms;

	public String getSms() {
		return this.sms;
	}

	@Override
	public void sendSms(OrderDto orderDto, RestaurantDto restaurantDto) {
		this.sms = "Your order has been placed!";
	}
}
