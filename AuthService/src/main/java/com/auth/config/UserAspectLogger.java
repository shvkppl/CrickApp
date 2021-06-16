package com.auth.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAspectLogger {
	
	Logger mylogger=LoggerFactory.getLogger(UserAspectLogger.class);
	


	@Before("execution (* com.auth.controller.UserController.adduser(..))")
	public void beforeadduser(JoinPoint jp) {
		
		mylogger.info("Client is Adding a new user ");
	}
	
	@After("execution(* com.auth.controller.UserController.adduser(..))")
	public void afteradduser(JoinPoint jp) {
		
		mylogger.info(" new user has been added ");
	}
}
