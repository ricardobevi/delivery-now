package com.ricardobevi.delivernow.controllers;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
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
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.ricardobevi.delivernow.MainApplication;
import com.ricardobevi.delivernow.controllers.requests.MealRequest;
import com.ricardobevi.delivernow.controllers.requests.OrderRequest;
import com.ricardobevi.delivernow.gateways.mocks.MockedETAGateway;
import com.ricardobevi.delivernow.mocks.MockedRestaurantGateway;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class OrderControllerTest {

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
	
    
    private static final Long restaurantId = 1L;
	
    @Autowired
    private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup() throws Exception {
	    this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}
	
    @Test
    public void placeOrder() throws Exception {
    
    	OrderRequest orderRequest = new OrderRequest(
    			Arrays.asList(new MealRequest(
    					MockedRestaurantGateway.bakedPotatoes.getName(), 
    					MockedRestaurantGateway.bakedPotatoes.getDescription(), 
    					MockedRestaurantGateway.bakedPotatoes.getPrice())),
    			3.5,
    			"221b Baker Street",
    			MockedETAGateway.haedoCity
    	);
    	
        String orderJson = json(orderRequest);
    	
        this.mockMvc.perform(post("/order/" + restaurantId)
                .contentType(contentType)
                .content(orderJson))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.status", is("Order placed successfully")))
        		.andExpect(jsonPath("$.eta", is("Your order will arrive in 50 minutes")))
                ;
        
        
    }
    
    @Test
    public void placeOrderRestaurantCantFullfill() throws Exception {
    	
    	OrderRequest orderRequest = new OrderRequest(
    			Arrays.asList(new MealRequest("Magic Potatoes", "Potatoes", 2.0)),
    			2.0,
    			"221b Baker Street",
    			MockedETAGateway.haedoCity
    	);
    	
        String orderJson = json(orderRequest);
    	
        this.mockMvc.perform(post("/order/" + restaurantId)
                .contentType(contentType)
                .content(orderJson))
			    .andExpect(status().isBadRequest())
			    .andExpect(content().contentType(contentType))
        		.andExpect(jsonPath("$.status", is("The restaurant can't fullfill the order")))
                ;
        
        
    }
    
    @Test
    public void placeOrderWithFieldMissing() throws Exception {
    	
    	OrderRequest orderRequest = new OrderRequest(
    			Arrays.asList(new MealRequest("Potatoes", "Potatoes", 2.0)),
    			null,
    			"221b Baker Street",
    			MockedETAGateway.haedoCity
    	);
    	
        String orderJson = json(orderRequest);
    	
        this.mockMvc.perform(post("/order/" + restaurantId)
                .contentType(contentType)
                .content(orderJson))
			    .andExpect(status().isBadRequest())
			    .andExpect(content().contentType(contentType))
			    .andExpect(jsonPath("$.message", is("Something is wrong with your request. There is a missing or null parameter.")))
                ;
        
        
    }
    
    
    @Test
    public void placeOrderWithWrongLatLongParam() throws Exception {

    	OrderRequest orderRequest = new OrderRequest(
    			Arrays.asList(new MealRequest("Potatoes", "Potatoes", 2.0)),
    			2.0,
    			"221b Baker Street",
    			"51.523767,-1110.1607498"
    	);
    	
        String orderJson = json(orderRequest);
    	
        this.mockMvc.perform(post("/order/" + restaurantId)
                .contentType(contentType)
                .content(orderJson))
			    .andExpect(status().isBadRequest())
			    .andExpect(content().contentType(contentType))
			    .andExpect(jsonPath("$.message", is("Wrong Coordinates")))
                ;
        
        
    }
    
    
    @Test
    public void restaurantDoesntExists() throws Exception {
    	
    	OrderRequest orderRequest = new OrderRequest(
    			Arrays.asList(new MealRequest("Potatoes", "Potatoes", 2.0)),
    			2.0,
    			"221b Baker Street",
    			MockedETAGateway.haedoCity
    	);
    	
        String orderJson = json(orderRequest);

        this.mockMvc.perform(post("/order/2000")
                .contentType(contentType)
                .content(orderJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.message", is("Couldn't find restaurant with id: 2000")))
                ;
    }
    
    @SuppressWarnings("unchecked")
	protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
	
}
