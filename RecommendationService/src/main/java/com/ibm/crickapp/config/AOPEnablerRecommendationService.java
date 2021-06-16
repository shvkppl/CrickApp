package com.ibm.crickapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AOPEnablerRecommendationService {
//Factory class which returns the object of logger class
	@Bean
	public RecommendationServiceAspectLogger getaspectData()
	{
		return new RecommendationServiceAspectLogger();
	}
	

}
