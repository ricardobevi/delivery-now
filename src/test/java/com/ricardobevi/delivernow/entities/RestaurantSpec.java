package com.ricardobevi.delivernow.entities;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RestaurantSpec {
	
	private Restaurant restaurantWithMeals;
	private Meal friedPotatoes;
	private Meal bakedPotatoes;
	private Meal smashedPotatoes;

	@Before
	public void setup() {
		
		this.friedPotatoes = new Meal("Fried Potatoes", "Yummy potatoes", new Price(2.5));
		this.bakedPotatoes = new Meal("Baked Potatoes", "Dummy potatoes", new Price(3.5));
		this.smashedPotatoes = new Meal("Smashed Potatoes", "Nice potatoes", new Price(4.5));
		
		List<Meal> mealList = Arrays.asList(friedPotatoes, bakedPotatoes);
		
		this.restaurantWithMeals = new Restaurant(mealList);
	}

	@Test
	public void test_when_a_customer_reviews_a_restaurant_should_add_that_review_to_the_restaurant() {
		
		Restaurant restaurant = restaurantWithMeals;
		
		Review review = new Review("Ricky", "I did like the soup", new Rating(2.3));
		
		restaurant.addReview(review);
		
		Assert.assertEquals(1, restaurant.countReviews());
	}
	
	
	@Test
	public void test_when_a_customer_reviews_a_restaurant_with_rating_2_should_update_the_rating_to_2() {
		
		Restaurant restaurant = restaurantWithMeals;
		
		Review review = new Review("Ricky", "I did like the soup", new Rating(2.0));
		
		restaurant.addReview(review);
		
		Assert.assertTrue(restaurant.rating().equals(new Rating(2.0)));
	}
	
	@Test
	public void test_when_2_customers_reviews_a_restaurant_with_rating_2_and_5_should_update_the_rating_to_3point5() {
		
		Restaurant restaurant = restaurantWithMeals;
		
		Review review1 = new Review("Ricky", "I did like the soup", new Rating(2.0));
		Review review2 = new Review("Ricky", "I did like the soup", new Rating(5.0));

		
		restaurant.addReview(review1);
		restaurant.addReview(review2);
		
		Assert.assertTrue(restaurant.rating().equals(new Rating(3.5)));
	}
	
	
	@Test
	public void test_place_an_order_should_save_it() {
		
		Order order = new Order(
				Arrays.asList(friedPotatoes), 
				new Price(2.5), 
				new Address("221b Baker Street"), 
				new LatLongLocation(51.523767, -0.1607498)
		);
		
		OrderStatus orderStatus = restaurantWithMeals.placeOrder(order);
		
		Assert.assertTrue(orderStatus instanceof OrderPlaced);
	}
	
	@Test
	public void test_place_an_order_with_meal_not_served_by_restaurant_should_return_order_error() {
		
		Order order = new Order(
				Arrays.asList(smashedPotatoes), 
				new Price(4.5), 
				new Address("221b Baker Street"), 
				new LatLongLocation(51.523767, -0.1607498)
		);
		
		OrderStatus orderStatus = restaurantWithMeals.placeOrder(order);
		
		Assert.assertTrue(orderStatus instanceof OrderError);
	}
	

	@Test
	public void test_place_an_order_with_total_cost_not_matching_sum_of_meals_should_return_order_error() {
		
		Order order = new Order(
				Arrays.asList(friedPotatoes, bakedPotatoes), 
				new Price(1.0), 
				new Address("221b Baker Street"), 
				new LatLongLocation(51.523767, -0.1607498)
		);
		
		OrderStatus orderStatus = restaurantWithMeals.placeOrder(order);
		
		Assert.assertTrue(orderStatus instanceof OrderError);
	}
	
}
