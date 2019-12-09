package com.dummy.mymvc.configuration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.dummy.mymvc.*"})
@PropertySource(value = "classpath:application.properties")
@Import(DatabaseConfigurer.class)
public class AppConfigurer implements WebMvcConfigurer {
	
	@Autowired
	private Environment env;
	
	@Bean
	public ViewResolver viewResolver(ApplicationContext applicationContext) {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setApplicationContext(applicationContext);
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer configurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@PostConstruct
	public void startup() {
		log.info("application starting -- {}", env.getProperty("application.name", "no app name found"));
		log.info("db host -- {}", this.env.getProperty("db.host", "localhost"));
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/**")
			.addResourceLocations("/statics/");
	}

}
