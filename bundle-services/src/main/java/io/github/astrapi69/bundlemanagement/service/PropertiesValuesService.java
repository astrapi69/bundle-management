/**
 * The MIT License
 * <p>
 * Copyright (C) 2015 Asterios Raptis
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.bundlemanagement.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import io.github.astrapi69.bundlemanagement.jpa.entity.PropertiesValues;
import io.github.astrapi69.bundlemanagement.jpa.repository.PropertiesValuesRepository;
import io.github.astrapi69.spring.service.api.GenericService;
import io.github.astrapi69.spring.service.api.NameEntityService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * The class {@link PropertiesValuesService}.
 */
@Transactional
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class PropertiesValuesService
	implements
		NameEntityService<PropertiesValues>,
		GenericService<PropertiesValues, UUID, PropertiesValuesRepository>
{

	PropertiesValuesRepository repository;

	@Override
	public List<PropertiesValues> findEntities(final String nameValue)
	{
		return repository.findByName(nameValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public PropertiesValues getOrCreateNewNameEntity(String value)
	{
		return NameEntityService.super.getOrCreateNewNameEntity(value);
	}

	@Override
	public PropertiesValues merge(PropertiesValues object)
	{
		return repository.save(object);
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
