package com.ibm.crickapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.ibm.crickapp.jwtfilter.RecommendationJWTFilter;

@SpringBootApplication
public class RecommendationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecommendationServiceApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean jwtFilter()
	{
		UrlBasedCorsConfigurationSource urlconfig=new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsconfig=new CorsConfiguration();
		corsconfig.setAllowCredentials(true);
		corsconfig.addAllowedOrigin("*");
		corsconfig.addAllowedMethod("*");
		corsconfig.addAllowedHeader("*");
		
		urlconfig.registerCorsConfiguration("/**", corsconfig);
	
		FilterRegistrationBean filterbean=new FilterRegistrationBean(new org.springframework.web.filter.CorsFilter(urlconfig));
		
		
		filterbean.setFilter(new RecommendationJWTFilter());
		
		filterbean.addUrlPatterns("/api/recommendation/*");
		return filterbean;
		
	}

	

}

