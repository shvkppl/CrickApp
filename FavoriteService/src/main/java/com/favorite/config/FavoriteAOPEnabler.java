package com.favorite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;



@Configuration
@EnableAspectJAutoProxy
public class FavoriteAOPEnabler {
	@Bean
	public FavoriteAspectLogger getaspectData()
	{
		return new FavoriteAspectLogger();
	}

}
