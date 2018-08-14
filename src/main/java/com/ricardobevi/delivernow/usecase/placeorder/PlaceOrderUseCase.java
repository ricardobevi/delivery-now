package com.ricardobevi.delivernow.usecase.placeorder;

import com.ricardobevi.delivernow.entities.Order;
import com.ricardobevi.delivernow.entities.OrderStatus;
import com.ricardobevi.delivernow.entities.Restaurant;
import com.ricardobevi.delivernow.gateways.ETAGateway;
import com.ricardobevi.delivernow.gateways.MailGateway;
import com.ricardobevi.delivernow.gateways.RestaurantGateway;
import com.ricardobevi.delivernow.gateways.SMSGateway;

public class PlaceOrderUseCase {
	
	private final PlaceOrderUseCaseInput placeOrderUseCaseinput;
	private final RestaurantGateway restaurantGateway;
	private final ETAGateway etaGateway;
	private final MailGateway mailGateway;
	private final SMSGateway smsGateway;

	public PlaceOrderUseCase(PlaceOrderUseCaseInput placeOrderUseCaseinput) {
		this.placeOrderUseCaseinput = placeOrderUseCaseinput;
		this.restaurantGateway = placeOrderUseCaseinput.getRestaurantGateway();
		this.etaGateway = placeOrderUseCaseinput.getEtaGateway();
		this.mailGateway = placeOrderUseCaseinput.getMailGateway();
		this.smsGateway = placeOrderUseCaseinput.getSmsGateway();
	}

	public PlaceOrderUseCaseOutput execute() {
		
		Restaurant restaurant = new Restaurant(this.restaurantGateway.getRestaurantFromId( this.placeOrderUseCaseinput.getRestaurantId() ));

		Order order = new Order(this.placeOrderUseCaseinput.getOrderDto());
		
		OrderStatus orderStatus = restaurant.placeOrder(order);
		
		if ( orderStatus.isOk() ) {
			
			this.restaurantGateway.save(restaurant.asDto());
			
			this.mailGateway.send(restaurant.asDto(), order.asDto());

			this.smsGateway.sendSms(order.asDto(), restaurant.asDto());
			
			String eta = this.etaGateway.etaString(restaurant.asDto(), this.placeOrderUseCaseinput.getOrderDto());
			
			return new PlaceOrderUseCaseOutput(orderStatus.asDto(eta));
			
		} else {
			
			return new PlaceOrderUseCaseOutput(orderStatus.asDto());
			
		}
		
		
	}

	
	 
}
