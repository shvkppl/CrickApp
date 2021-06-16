package com.ibm.crickapp.config;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.stereotype.Component;

@Aspect
@Component
public class RecommendationServiceAspectLogger {
	//Writing All the aspect
	Logger mylogger=LoggerFactory.getLogger(RecommendationServiceAspectLogger.class);
	
	@Before("execution (* com.ibm.crickapp.controller.RecommendationController.storeMatch(..))")
	public void beforestoreMatch(JoinPoint jp) {
		
		mylogger.info("Client is Adding a new Match ");
	}
	
	@After("execution (* com.ibm.crickapp.controller.RecommendationController.storeMatch(..))")
	public void afterstoreMatch(JoinPoint jp) {
		
		mylogger.info("new Match added ");
	}
	
	@Before("execution (* com.ibm.crickapp.controller.RecommendationController.removeMatch(..))")
	public void beforeremoveMatch(JoinPoint jp) {
		
		mylogger.info("Client is removing Match ");
	}
	
	@After("execution (* com.ibm.crickapp.controller.RecommendationController.removeMatch(..))")
	public void afterremoveMatch(JoinPoint jp) {
		
		mylogger.info("Match removed: Counter Decremented");
	}
	

}
