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
package com.github.michaelsteven.archetype.springboot.items.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.michaelsteven.archetype.springboot.items.aspect.ComplianceEventAspect;
import com.github.michaelsteven.archetype.springboot.items.aspect.LoggerAspect;
import com.github.michaelsteven.archetype.springboot.items.aspect.RepositoryAspect;

/**
 * The Class AspectConfig.
 */
@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {

	private ObjectMapper objectMapper;
	private MessageSource messageSource;
	
	/**
	 * Constructor.
	 *
	 * @param objectMapper the object mapper
	 * @param messageSource the message source
	 */
	public AspectConfig(ObjectMapper objectMapper, MessageSource messageSource) {
		this.objectMapper = objectMapper;
		this.messageSource = messageSource;
	}
	
	/**
	 * Logger aspect.
	 *
	 * @param environment the environment
	 * @return the logger aspect
	 */
	@Bean
	public LoggerAspect loggerAspect(Environment environment) {
		return new LoggerAspect(objectMapper);
	}
	
	/**
	 * Repository aspect.
	 *
	 * @param enviornment the environment
	 * @return the repository aspect
	 */
	@Bean
	public RepositoryAspect repositoryAspect(Environment enviornment) {
		return new RepositoryAspect(messageSource);
	}
	
	/**
	 * Compliance event.
	 *
	 * @param environment the environment
	 * @return the compliance event aspect
	 */
	@Bean
	public ComplianceEventAspect complianceEvent(Environment environment) {
		return new ComplianceEventAspect();
	}
}