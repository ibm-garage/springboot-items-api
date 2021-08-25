/* 
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.    
*/
package com.github.michaelsteven.archetype.springboot.items.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The logger aspect class
 */
@Aspect
public class LoggerAspect {
	
	private ObjectMapper objectMapper;
	
	/**
	 * Constructor
	 * 
	 * @param objectMapper
	 */
	public LoggerAspect(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	
	/**
	 * A "point cut" cut defines a predicate used to identify a collection of join points.
	 * The annotation currently identifies all public methods on the controller and service packages.
	 */
	@Pointcut("within(com.github.michaelsteven..controller..*) || within(com.github.michaelsteven..service..*)")
	public void applicationPointCut() {
	}
	
	/**
	 * After Throwing - Advice to take when exceptions are thrown
	 * 
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "applicationPointCut()", throwing = "e")
	public void logException(JoinPoint joinPoint, Throwable e) {
		this.getLogger(joinPoint).error("Exception in {}() with Cause = {} and message {} ",
					joinPoint.getSignature().getName(), 
					e.getCause() != null ? e.getCause() : "NULL Cause", 
					e.getMessage(), 
					e
				);
	}
	
	/**
	 * Log Advice - Around advice to take when entering and exiting a method 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("applicationPointCut()")
	public Object logAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		Logger logger = this.getLogger(joinPoint);
		
		logger.trace("Entered: {} () with arguments = {}" , joinPoint.getSignature().getName(), Arrays.deepToString(joinPoint.getArgs()));
	    try {
	    	Object object = joinPoint.proceed();
	    	logger.trace("Exited: {} () with result = {}", 
	    			joinPoint.getSignature().getName(), objectMapper.writeValueAsString(object)
	    		);
	    	return object;
	    } catch( Exception e) {
	    	logger.error("Exception {} in {}()", Arrays.toString(joinPoint.getArgs()),
	    			joinPoint.getSignature().getName()
	    		);
	    	throw e;
	    }
	}
	
	/**
	 * Gets the logger
	 * 
	 * left package protected to facilitate override in unit tests
	 * 
	 * @param joinPoint
	 * @return
	 */
	Logger getLogger(JoinPoint joinPoint) {
		return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
	}
}