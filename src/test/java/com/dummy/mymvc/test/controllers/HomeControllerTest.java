package com.dummy.mymvc.test.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dummy.mymvc.configuration.AppConfigurer;
import com.dummy.mymvc.controllers.HomeController;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfigurer.class)
@WebAppConfiguration
public class HomeControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private HomeController controller = new HomeController();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void testInsert() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders
				.get("/add"));
		
				
				
	}

}
