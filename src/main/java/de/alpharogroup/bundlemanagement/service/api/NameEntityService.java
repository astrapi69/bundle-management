/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.bundlemanagement.service.api;

import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.db.entity.name.NameUUIDEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NameEntityService<T extends NameUUIDEntity>
{

	/**
	 * Find the entities objects from the given name value.
	 *
	 * @param nameValue
	 *            the name value
	 * @return the list with the entities
	 */
	List<T> findEntities(final String nameValue);

	/**
	 * Find the entity object from the given name value.
	 * 
	 * @param nameValue
	 *            the name value
	 * @return the found entity object or null if not.
	 */
	default T findFirst(final String nameValue)
	{
		final List<T> nameEntities = findEntities(nameValue);
		return ListExtensions.getFirst(nameEntities);
	}

	/**
	 * Gets the or creates a new entity object
	 *
	 * @param value
	 *            the value
	 * @return the entity object
	 */
	@Transactional
	default T getOrCreateNewNameEntity(final String value)
	{
		T nameEntity = findFirst(value);
		if (nameEntity == null)
		{
			nameEntity = newNameEntity(value);
			nameEntity = merge(nameEntity);
		}
		return nameEntity;
	}

	/**
	 * Merges the given object. @see Hibernate documentation.
	 *
	 * @param object
	 *            the object
	 * @return the object
	 */
	T merge(final T object);

	/**
	 * Factory method for create a new name entity.
	 *
	 * @param value
	 *            the value
	 * @return the new name entity
	 */
	T newNameEntity(final String value);
}