package com.ricardobevi.delivernow.usecase.placeorder;

import com.ricardobevi.delivernow.dto.OrderStatusDto;
import com.ricardobevi.delivernow.entities.Order;
import com.ricardobevi.delivernow.entities.OrderStatus;
import com.ricardobevi.delivernow.entities.Restaurant;
import com.ricardobevi.delivernow.gateways.RestaurantGateway;

public class PlaceOrderUseCase {
	
	private final PlaceOrderUseCaseInput placeOrderUseCaseinput;
	private final RestaurantGateway restaurantGateway;

	public PlaceOrderUseCase(PlaceOrderUseCaseInput placeOrderUseCaseinput) {
		this.placeOrderUseCaseinput = placeOrderUseCaseinput;
		this.restaurantGateway = placeOrderUseCaseinput.getRestaurantGateway();
	}

	public PlaceOrderUseCaseOutput execute() {
		
		Restaurant restaurant = new Restaurant(this.restaurantGateway.getRestaurantFromId( this.placeOrderUseCaseinput.getRestaurantId() ));

		Order order = new Order(this.placeOrderUseCaseinput.getOrderDto());
		
		OrderStatus orderStatus = restaurant.placeOrder(order);
		
		return new PlaceOrderUseCaseOutput(orderStatus.asDto());
	}
	
	
	 
}
