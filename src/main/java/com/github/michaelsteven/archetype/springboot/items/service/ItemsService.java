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
package com.github.michaelsteven.archetype.springboot.items.service;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.michaelsteven.archetype.springboot.items.model.ConfirmationDto;
import com.github.michaelsteven.archetype.springboot.items.model.ItemDto;

/**
 * The Interface ItemsService.
 */
public interface ItemsService {
	
	/**
	 * Gets the items.
	 *
	 * @param pageable the pageable
	 * @return the items
	 */
	public abstract Page<ItemDto> getItems(Pageable pageable);
	
	/**
	 * Gets the item by id.
	 *
	 * @param id the id
	 * @return the item by id
	 */
	public abstract Optional<ItemDto> getItemById(long id);
	
	/**
	 * Save item.
	 *
	 * @param itemDto the item dto
	 * @return the confirmation dto
	 */
	public abstract ConfirmationDto saveItem(@NotNull @Valid ItemDto itemDto);
	
	/**
	 * Edits the item.
	 *
	 * @param itemDto the item dto
	 * @return the confirmation dto
	 */
	public abstract ConfirmationDto editItem(@NotNull @Valid ItemDto itemDto);
	
	/**
	 * Delete item by id.
	 *
	 * @param id the id
	 */
	public abstract void deleteItemById(long id);

}
