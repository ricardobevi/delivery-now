package com.ricardobevi.delivernow.controllers;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.ricardobevi.delivernow.MainApplication;
import com.ricardobevi.delivernow.dto.LatLongLocationDto;
import com.ricardobevi.delivernow.dto.OrderDto;
import com.ricardobevi.delivernow.dto.RestaurantDto;
import com.ricardobevi.delivernow.dto.ReviewDto;
import com.ricardobevi.delivernow.gateways.mocks.MockedETAGateway;
import com.ricardobevi.delivernow.gateways.model.MealDAO;
import com.ricardobevi.delivernow.gateways.model.RestaurantDAO;
import com.ricardobevi.delivernow.gateways.model.RestaurantRepository;
import com.ricardobevi.delivernow.gateways.model.ReviewDAO;
import com.ricardobevi.delivernow.mocks.MockedRestaurantGateway;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class RestaurantControllerTest {
		
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
	        MediaType.APPLICATION_JSON.getSubtype(),
	        Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
    @SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	
    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
	
	
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
    private RestaurantRepository restaurantRepository;
	
	@Before
	public void setup() throws Exception {
	    this.mockMvc = webAppContextSetup(webApplicationContext).build();
	    
	}
	
    @Test
    public void deleteRestaurant() throws Exception {
    	
    	Long restaurantId = restaurantRepository.save(
				new RestaurantDAO(
						2L,
						Arrays.asList(new ReviewDAO("Richard", "Nice place!", 4.0)),
						Arrays.asList(
								new MealDAO(MockedRestaurantGateway.friedPotatoes), 
								new MealDAO(MockedRestaurantGateway.bakedPotatoes)
						),
						MockedETAGateway.ciudadelaHood,
						"commercial.email@mail.com",
						4.0,
						"http://restaurant.com/logo.png",
						"Betty's",
						"BETT",
						"343444442233",
						"221b Baker Street"
				)
		).getId();
    	
        this.mockMvc.perform(delete("/restaurant/" + restaurantId)
                .contentType(contentType))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.id", is(restaurantId.intValue())))
                ;
        
        assertFalse(restaurantRepository.existsById(restaurantId));
        
    }
    
    
    @Test
    public void listRestaurants() throws Exception {
    	
    	Long restaurantId = 1L;
    	
        this.mockMvc.perform(get("/restaurant")
                .contentType(contentType))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$[0].id", is(restaurantId.intValue())))
                ;
        
    }
    
    
    @Test
    public void listRestaurantsWithReviewMoreThan4point5() throws Exception {
    	
    	Long restaurantId = restaurantRepository.save(
				new RestaurantDAO(
						2L,
						Arrays.asList(new ReviewDAO("Richard", "Nice place!", 5.0)),
						Arrays.asList(
								new MealDAO(MockedRestaurantGateway.friedPotatoes), 
								new MealDAO(MockedRestaurantGateway.bakedPotatoes)
						),
						MockedETAGateway.ciudadelaHood,
						"commercial.email@mail.com",
						5.0,
						"http://restaurant.com/logo.png",
						"Betty's",
						"BETT",
						"343444442233",
						"221b Baker Street"
				)
		).getId();
    	
        this.mockMvc.perform(get("/restaurant?minRating=4.5")
                .contentType(contentType))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$[0].id", is(restaurantId.intValue())))
                ;
        
    }
    
    
    @Test
    @Ignore
    public void updateRestaurantInformation() throws Exception {
    	
    	RestaurantDAO restaurantDAO = restaurantRepository.save(
				new RestaurantDAO(
						100L,
						Arrays.asList(new ReviewDAO("Richard", "Nice place!", 5.0)),
						Arrays.asList(
								new MealDAO(MockedRestaurantGateway.friedPotatoes), 
								new MealDAO(MockedRestaurantGateway.bakedPotatoes)
						),
						MockedETAGateway.ciudadelaHood,
						"commercial.email@mail.com",
						1.0,
						
						"http://restaurant.com/logo.png",
						"Betty's",
						"BETT",
						"343444442233",
						"221b Baker Street"
				)
		);
    	
    
    	RestaurantDto restaurantDto = new RestaurantDto(
    			100L, 
				1.0, 
				Arrays.asList(
						new ReviewDto("John", "Nice place to hang out with friends!", 4.5)
				),
				Arrays.asList(
						MockedRestaurantGateway.friedPotatoes,
						MockedRestaurantGateway.bakedPotatoes,
						MockedRestaurantGateway.smashedPotatoes
				),
				Arrays.asList(
						new OrderDto(
							Arrays.asList(MockedRestaurantGateway.friedPotatoes),
							2.5,
							"221b Baker Street",
							new LatLongLocationDto(MockedETAGateway.haedoCity)
						)
				),
				new LatLongLocationDto(MockedETAGateway.ciudadelaHood),
				"commercial.email@mail.com",
				
				"http://restaurant.com/logo.png",
				"Betty's",
				"BETT",
				"343444442233",
				"221b Baker Street"
    	);
    	
    	
        this.mockMvc.perform(put("/restaurant")
                .contentType(contentType)
                .content(json(restaurantDto)))
        		.andExpect(status().isOk())
                ;
        
        String savedJson = json(restaurantDAO.asDto());
        String expectedJson = json(restaurantDto);
        
        
        assertEquals(expectedJson, savedJson);
    	
    	
    }
    
    
    @Test
    public void deleteRestaurantWithNonExistingOne() throws Exception {
    	
        this.mockMvc.perform(delete("/restaurant/2000")
                .contentType(contentType))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.message", is("Couldn't find restaurant with id: 2000")))
                ;
        
        
    }
    
    
    
    @SuppressWarnings("unchecked")
 	private String json(Object o) throws IOException {
         MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
         this.mappingJackson2HttpMessageConverter.write(
                 o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
         return mockHttpOutputMessage.getBodyAsString();
     }
}
