package com.dummy.mymvc.test.controllers;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.dummy.mymvc.configuration.AppConfigurer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfigurer.class)
@WebAppConfiguration
public class HomeControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Autowired
	private DataSource dataSource;

	@Before
	public void setup() {
		log.info("starting test module");
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@After
	public void end() {
		log.info("end of test module");
	}

	@Test
	public void testHome() {
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/add")).andDo(MockMvcResultHandlers.log()).andReturn();
		} catch (Exception e) {
			log.info("exception!! {}", e);
		}
	}

	@Test
	public void testGen() {
		log.info("gen test --");
		log.info("datasource -- {}", this.dataSource);
	}

}
