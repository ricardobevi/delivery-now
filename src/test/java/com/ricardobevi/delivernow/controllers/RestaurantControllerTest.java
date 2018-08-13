package com.ricardobevi.delivernow.controllers;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.ricardobevi.delivernow.MainApplication;
import com.ricardobevi.delivernow.gateways.MockedETAGateway;
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
						Arrays.asList(new ReviewDAO(1L, "Richard", "Nice place!", 4.0)),
						Arrays.asList(
								new MealDAO(MockedRestaurantGateway.friedPotatoes), 
								new MealDAO(MockedRestaurantGateway.bakedPotatoes)
						),
						MockedETAGateway.ciudadelaHood
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
    public void deleteRestaurantWithNonExistingOne() throws Exception {
    	
        this.mockMvc.perform(delete("/restaurant/2000")
                .contentType(contentType))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.message", is("Couldn't find restaurant with id: 2000")))
                ;
        
        
    }
    
}
