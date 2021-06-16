package com.favorite.config;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class FavoriteAspectLogger {

Logger mylogger=LoggerFactory.getLogger(FavoriteAspectLogger.class);
	


	@Before("execution (* com.favorite.controllers.FavoriteController.addToFav(..))")
	public void beforeadduser(JoinPoint jp) {
		
		mylogger.info("User is trying to add to favorite ");
	}
	
	@After("execution(* com.favorite.controllers.FavoriteController.addToFav(..))")
	public void afteradduser(JoinPoint jp) {
		
		mylogger.info(" successfully added ");
	}
	
}




