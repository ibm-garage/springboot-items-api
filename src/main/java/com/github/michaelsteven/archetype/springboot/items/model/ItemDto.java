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

import java.time.ZonedDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Instantiates a new item dto.
 *
 * @param id the id
 * @param name the name
 * @param description the description
 * @param dateSubmitted the date submitted
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // helpful to support minor model changes
@Schema(name="Item")
public class ItemDto {

	/** The id. */
	@Schema(name = "id", description="The ID of the item", example ="1234567890")
	private Long id;
	
	/** The name. */
	@Schema(name = "name", description="The name of the item", example = "wigit5spr")
	@NotNull(message = "{itemdto.null}")
	@Size(min  = 1, max = 25, message = "{itemdto.textlimit}")
	private String name;
	
	/** The description. */
	@Schema(name = "description", description="The description of the item", example = "5 sprocket wigit")
	@Size(min  = 1, max = 200, message = "{itemdto.textlimit}")
	private String description;
	
	/** The date submitted. */
	@Schema(hidden = true)
	private ZonedDateTime dateSubmitted;
	
}
