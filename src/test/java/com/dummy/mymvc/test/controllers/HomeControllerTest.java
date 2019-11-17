package com.dummy.mymvc.test.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.web.context.WebApplicationContext;

import com.dummy.mymvc.configuration.AppConfigurer;
import com.dummy.mymvc.controllers.HomeController;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfigurer.class)
@WebAppConfiguration
public class HomeControllerTest {
	
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void testHome() {
		try {
			MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/add")).andDo(MockMvcResultHandlers.log()).andReturn();
			System.out.println("app contect -- " + context.getApplicationName());
			System.out.println("response -- " + result.getResponse());
		} catch (Exception e) {
			System.out.println("exception msg: " + e.getMessage());
			e.printStackTrace();
		}
	}


}
