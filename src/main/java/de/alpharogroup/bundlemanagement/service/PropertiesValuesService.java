/**
 * The MIT License
 *
 * Copyright (C) 2007 - 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *  *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *  *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.bundlemanagement.service;

import de.alpharogroup.bundlemanagement.jpa.entity.PropertiesValues;
import de.alpharogroup.bundlemanagement.jpa.repository.PropertiesValuesRepository;
import de.alpharogroup.bundlemanagement.service.api.NameEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The class {@link PropertiesValuesService}.
 */
@Transactional
@Service
public class PropertiesValuesService
	implements NameEntityService<PropertiesValues, Integer>
{
	@Autowired
	PropertiesValuesRepository propertiesValuesRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public PropertiesValues getOrCreateNewNameEntity(String value)
	{
		return NameEntityService.super.getOrCreateNewNameEntity(value);
	}

	@Override public PropertiesValues merge(PropertiesValues object)
	{
		return propertiesValuesRepository.save(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PropertiesValues newNameEntity(String value)
	{
		return PropertiesValues.builder().name(value).build();
	}

}
