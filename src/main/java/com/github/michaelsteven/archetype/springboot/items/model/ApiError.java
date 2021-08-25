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
package com.github.michaelsteven.archetype.springboot.items.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data
@NoArgsConstructor
public class ApiError {

	/** The status. */
	private HttpStatus status;
	
	/** The message. */
	private String message;
	
	/** The errors. */
	private List<String> errors;
	
    /**
     * Instantiates a new api error.
     *
     * @param status the status
     * @param message the message
     * @param errors the errors
     */
    public ApiError(HttpStatus status, String message, List<String> errors) {
		this.status =  status;
		this.message = message;
		this.errors = errors;
	}
    
    /**
     * Instantiates a new api error.
     *
     * @param status the status
     * @param message the message
     * @param error the error
     */
    public ApiError(HttpStatus status, String message, String error) {
 		this.status =  status;
 		this.message = message;
 		this.errors = Arrays.asList(error);
 	}
}
