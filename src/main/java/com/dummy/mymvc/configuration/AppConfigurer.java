package com.dummy.mymvc.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.dummy.mymvc"})
@Import(DatabaseConfigurer.class)
public class AppConfigurer implements WebMvcConfigurer {
	
	@Bean
	public ViewResolver viewResolver(ApplicationContext applicationContext) {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setApplicationContext(applicationContext);
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/**")
			.addResourceLocations("/statics/");
	}

}
