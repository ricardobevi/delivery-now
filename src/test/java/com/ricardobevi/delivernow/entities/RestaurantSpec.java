package com.ricardobevi.delivernow.entities;

import org.junit.Assert;
import org.junit.Test;

public class RestaurantSpec {

	@Test
	public void test_when_a_customer_reviews_a_restaurant_should_add_that_review_to_the_restaurant() {
		
		Restaurant restaurant = new Restaurant();
		
		Review review = new Review("Ricky", "I did like the soup", new Rating(2.3));
		
		restaurant.addReview(review);
		
		Assert.assertEquals(1, restaurant.countReviews());
	}
	
	
	@Test
	public void test_when_a_customer_reviews_a_restaurant_with_rating_2_should_update_the_rating_to_2() {
		
		Restaurant restaurant = new Restaurant();
		
		Review review = new Review("Ricky", "I did like the soup", new Rating(2.0));
		
		restaurant.addReview(review);
		
		Assert.assertTrue(restaurant.rating().equals(new Rating(2.0)));
	}
	
	@Test
	public void test_when_2_customers_reviews_a_restaurant_with_rating_2_and_5_should_update_the_rating_to_3point5() {
		
		Restaurant restaurant = new Restaurant();
		
		Review review1 = new Review("Ricky", "I did like the soup", new Rating(2.0));
		Review review2 = new Review("Ricky", "I did like the soup", new Rating(5.0));

		
		restaurant.addReview(review1);
		restaurant.addReview(review2);
		
		Assert.assertTrue(restaurant.rating().equals(new Rating(3.5)));
	}
	
}
