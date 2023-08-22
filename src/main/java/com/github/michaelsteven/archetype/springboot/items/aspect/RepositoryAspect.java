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

import jakarta.persistence.PersistenceException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * The Repository Aspect class.
 */
@Aspect
public class RepositoryAspect {
	
	private MessageSource messageSource;
	
	/**
	 * Constructor
	 * 
	 * @param messageSource the message source
	 */
	public RepositoryAspect(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	/**
	 * Repository point cut.
	 */
	@Pointcut("execution(* org.springframework.data.jpa.repository.JpaRepository+.*(..))")
	public void repositoryPointCut() {
	}
	
	/**
	 * Convert to persistence exception.
	 *
	 * @param joinPoint the join point
	 * @param e the e
	 */
	@AfterThrowing(pointcut = "repositoryPointCut()", throwing = "e")
	public void convertToPersistenceException(JoinPoint joinPoint, Throwable e) {
		String message = messageSource.getMessage("repositoryaspect.persistenceexception.message", null, LocaleContextHolder.getLocale());
		throw new PersistenceException(message, e);
	}
}