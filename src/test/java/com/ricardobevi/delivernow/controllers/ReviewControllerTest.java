package com.ricardobevi.delivernow.controllers;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.ricardobevi.delivernow.MainApplication;
import com.ricardobevi.delivernow.dto.ReviewDto;
import com.ricardobevi.delivernow.gateways.model.RestaurantDAO;
import com.ricardobevi.delivernow.gateways.model.RestaurantRepository;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
@WebAppConfiguration
public class ReviewControllerTest {
		
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
	        MediaType.APPLICATION_JSON.getSubtype(),
	        Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	private List<RestaurantDAO> restaurantDAOList = new ArrayList<RestaurantDAO>();
	
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
	    
	    restaurantRepository.deleteAllInBatch();
	    
	    this.restaurantDAOList.add(restaurantRepository.save(new RestaurantDAO()));
	    
	}
	
    @Test
    public void addReview() throws Exception {
    	
    	ReviewDto reviewDto = new ReviewDto("Ricky", "Another Review", 1.0);
    	
        String reviewJson = json(reviewDto);

        this.mockMvc.perform(post("/review/" + this.restaurantDAOList.get(0).getId().longValue())
                .contentType(contentType)
                .content(reviewJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(this.restaurantDAOList.get(0).getId().intValue())))
                .andExpect(jsonPath("$.rating", is(1.0)))
                .andExpect(jsonPath("$.reviews[0].name", is("Ricky")))
                .andExpect(jsonPath("$.reviews[0].review", is("Another Review")))
                .andExpect(jsonPath("$.reviews[0].rating", is(1.0)))
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
