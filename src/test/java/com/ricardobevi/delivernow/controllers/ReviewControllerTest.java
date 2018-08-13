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
import com.ricardobevi.delivernow.controllers.requests.ReviewRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class ReviewControllerTest {
		
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
    public void addReview() throws Exception {
    	
    	ReviewRequest reviewRequest = new ReviewRequest("Ricky", "Another Review", 1.0);
    	
        String reviewJson = json(reviewRequest);

        this.mockMvc.perform(post("/review/" + restaurantId)
                .contentType(contentType)
                .content(reviewJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(restaurantId.intValue())))
                .andExpect(jsonPath("$.rating", is(3.3333333333333335)))
                .andExpect(jsonPath("$.reviews[2].name", is("Ricky")))
                .andExpect(jsonPath("$.reviews[2].review", is("Another Review")))
                .andExpect(jsonPath("$.reviews[2].rating", is(1.0)))
                .andExpect(jsonPath("$.commercialEmail", is("commercial.email@mail.com")))
                ;
    }
    
    @Test
    public void addReviewWithHighRating() throws Exception {
    	
    	ReviewRequest reviewRequest = new ReviewRequest("Ricky", "Another Review", 20.0);
    	
        String reviewJson = json(reviewRequest);

        this.mockMvc.perform(post("/review/" + restaurantId)
                .contentType(contentType)
                .content(reviewJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.message", is("Rating out of bounds. Should be between 1.0 and 5.0.")))
                ;
    }
    
    @Test
    public void addReviewWithLowRating() throws Exception {
    	
    	ReviewRequest reviewRequest = new ReviewRequest("Ricky", "Another Review", 0.5);
    	
        String reviewJson = json(reviewRequest);

        this.mockMvc.perform(post("/review/" + restaurantId)
                .contentType(contentType)
                .content(reviewJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.message", is("Rating out of bounds. Should be between 1.0 and 5.0.")))
                ;
    }
    
    @Test
    public void addReviewWithIncompleteReview() throws Exception {
    	
    	ReviewRequest reviewRequest = new ReviewRequest("Ricky", null, 1.0);
    	
        String reviewJson = json(reviewRequest);

        this.mockMvc.perform(post("/review/" + restaurantId)
                .contentType(contentType)
                .content(reviewJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.message", is("Something is wrong with your request. There is a missing or null parameter.")))
                ;
    }
    
    
    @Test
    public void addReviewWithInvalidJson() throws Exception {
    	
        String reviewJson = "{\"hello\": \"im a malformed entry\"}";

        this.mockMvc.perform(post("/review/" + restaurantId)
                .contentType(contentType)
                .content(reviewJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.message", is("Something is wrong with your request. There is a missing or null parameter.")))
                ;
    }
    
    @Test
    public void addReviewToMissingRestaurant() throws Exception {
    	
    	ReviewRequest reviewRequest = new ReviewRequest("Ricky", "Another Review", 1.0);
    	
        String reviewJson = json(reviewRequest);

        this.mockMvc.perform(post("/review/2000")
                .contentType(contentType)
                .content(reviewJson))
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
