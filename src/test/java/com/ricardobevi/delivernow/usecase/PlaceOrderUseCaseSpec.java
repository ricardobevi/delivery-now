package com.ricardobevi.delivernow.usecase;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.ricardobevi.delivernow.dto.LatLongLocationDto;
import com.ricardobevi.delivernow.dto.OrderDto;
import com.ricardobevi.delivernow.gateways.MockedETAGateway;
import com.ricardobevi.delivernow.gateways.MockedMailGateway;
import com.ricardobevi.delivernow.mocks.MockedRestaurantGateway;
import com.ricardobevi.delivernow.usecase.placeorder.PlaceOrderUseCase;
import com.ricardobevi.delivernow.usecase.placeorder.PlaceOrderUseCaseInput;
import com.ricardobevi.delivernow.usecase.placeorder.PlaceOrderUseCaseOutput;

public class PlaceOrderUseCaseSpec {

	private static final OrderDto createOrderDto() {
		return new OrderDto(
				Arrays.asList(
						MockedRestaurantGateway.friedPotatoes,
						MockedRestaurantGateway.bakedPotatoes
				),
				6.0, // --> Price (I should probably create a builder)
				"221b Baker Street",
				new LatLongLocationDto(1.0, 1.0)
		);
	}
	
	private static final OrderDto createImpossibleToFullfillOrderDto() {
		return new OrderDto(
				Arrays.asList(
						MockedRestaurantGateway.invisiblePotatoes
				),
				4.5, // --> Price (I should probably create a builder)
				"221b Baker Street",
				new LatLongLocationDto(1.0, 1.0)
		);
	}
	
	@Test
	public void given_an_order_should_place_it() {
		
		Long restaurantId = 123456L;
		
		PlaceOrderUseCaseInput placeOrderUseCaseinput = new PlaceOrderUseCaseInput(
				restaurantId, 
				createOrderDto(), 
				new MockedRestaurantGateway(),
				new MockedETAGateway(),
				new MockedMailGateway()
		);
		
		PlaceOrderUseCaseOutput placeOrderUseCaseOutput = new PlaceOrderUseCase(placeOrderUseCaseinput).execute();
		
		Assert.assertTrue(placeOrderUseCaseOutput.getOrderStatusDto().getStatus().equals("Order placed successfully"));
		
	}
	
	@Test
	public void given_an_order_to_a_restaurant_without_meal_should_return_error() {
		
		Long restaurantId = 123456L;
		
		PlaceOrderUseCaseInput placeOrderUseCaseinput = new PlaceOrderUseCaseInput(
				restaurantId, 
				createImpossibleToFullfillOrderDto(), 
				new MockedRestaurantGateway(),
				new MockedETAGateway(),
				new MockedMailGateway()
		);
		
		PlaceOrderUseCaseOutput placeOrderUseCaseOutput = new PlaceOrderUseCase(placeOrderUseCaseinput).execute();
		
		Assert.assertTrue(placeOrderUseCaseOutput.getOrderStatusDto().getStatus().equals("The restaurant can't fullfill the order"));
		
	}

	
	@Test
	public void given_an_order_should_place_it_and_return_the_ETA() {
		
		Long restaurantId = 123456L;
		
		PlaceOrderUseCaseInput placeOrderUseCaseinput = new PlaceOrderUseCaseInput(
				restaurantId, 
				createOrderDto(), 
				new MockedRestaurantGateway(),
				new MockedETAGateway(),
				new MockedMailGateway()
		);
		
		PlaceOrderUseCaseOutput placeOrderUseCaseOutput = new PlaceOrderUseCase(placeOrderUseCaseinput).execute();
		
		Assert.assertTrue(placeOrderUseCaseOutput.getOrderStatusDto().getStatus().equals("Order placed successfully"));
		Assert.assertTrue(placeOrderUseCaseOutput.getOrderStatusDto().getEta().equals("Your order will arrive in 50 minutes"));
		
	}
	
	@Test
	public void given_an_order_should_place_it_and_send_an_email() {
		
		Long restaurantId = 123456L;
		
		MockedMailGateway mockedMailGateway = new MockedMailGateway();
		
		PlaceOrderUseCaseInput placeOrderUseCaseinput = new PlaceOrderUseCaseInput(
				restaurantId, 
				createOrderDto(), 
				new MockedRestaurantGateway(),
				new MockedETAGateway(),
				mockedMailGateway
		);
		
		PlaceOrderUseCaseOutput placeOrderUseCaseOutput = new PlaceOrderUseCase(placeOrderUseCaseinput).execute();
		
		Assert.assertEquals("You have a new order! Address: 221b Baker Street", mockedMailGateway.getBody());
		
	}
	
}
