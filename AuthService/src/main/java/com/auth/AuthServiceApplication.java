package com.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.auth.filter.UserJWTFilter;

@SpringBootApplication
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
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
		
		
		filterbean.setFilter(new UserJWTFilter());
		
		filterbean.addUrlPatterns("/api/authenticate/updateuser");
		return filterbean;
		
	}


}
