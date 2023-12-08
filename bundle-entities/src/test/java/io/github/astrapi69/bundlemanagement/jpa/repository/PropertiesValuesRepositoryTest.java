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
package io.github.astrapi69.bundlemanagement.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.astrapi69.bundlemanagement.integration.AbstractIntegrationTest;
import io.github.astrapi69.bundlemanagement.jpa.entity.PropertiesValues;

@Disabled
public class PropertiesValuesRepositoryTest extends AbstractIntegrationTest
{

	@Autowired
	private PropertiesValuesRepository repository;

	@Test
	@Disabled
	public void whenFindByNameThenReturnPropertiesValues()
	{
		PropertiesValues entity = PropertiesValues.builder().name("foo-value").build();

		entityManager.persist(entity);
		entityManager.flush();

		// when
		List<PropertiesValues> byName = repository.findByName(entity.getName());
		// then
		assertNotNull(byName);
		assertTrue(byName.contains(entity));
	}

}
