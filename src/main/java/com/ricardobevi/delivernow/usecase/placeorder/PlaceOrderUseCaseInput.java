package com.ricardobevi.delivernow.usecase.placeorder;

import com.ricardobevi.delivernow.dto.OrderDto;
import com.ricardobevi.delivernow.gateways.ETAGateway;
import com.ricardobevi.delivernow.gateways.MailGateway;
import com.ricardobevi.delivernow.gateways.RestaurantGateway;
import com.ricardobevi.delivernow.gateways.SMSGateway;

public class PlaceOrderUseCaseInput {
	
	private final Long restaurantId;
	private final OrderDto orderDto;
	private final RestaurantGateway restaurantGateway;
	private final ETAGateway etaGateway;
	private final MailGateway mailGateway;
	private final SMSGateway smsGateway;
	
	public PlaceOrderUseCaseInput(
			Long restaurantId,
			OrderDto orderDto,
			RestaurantGateway restaurantGateway,
			ETAGateway etaGateway,
			MailGateway mailGateway,
			SMSGateway smsGateway) {
		this.restaurantId = restaurantId;
		this.orderDto = orderDto;
		this.restaurantGateway = restaurantGateway;
		this.etaGateway = etaGateway;
		this.mailGateway = mailGateway;
		this.smsGateway = smsGateway;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public OrderDto getOrderDto() {
		return orderDto;
	}

	public RestaurantGateway getRestaurantGateway() {
		return restaurantGateway;
	}

	public ETAGateway getEtaGateway() {
		return etaGateway;
	}

	public MailGateway getMailGateway() {
		return mailGateway;
	}

	public SMSGateway getSmsGateway() {
		return smsGateway;
	}
}
