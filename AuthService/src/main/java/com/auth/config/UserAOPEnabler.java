package com.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class UserAOPEnabler {
	
	@Bean
	public UserAspectLogger getaspectData()
	{
		return new UserAspectLogger();
	}
	

}
