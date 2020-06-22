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
package de.alpharogroup.bundlemanagement.jpa.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.alpharogroup.bundlemanagement.jpa.entity.Languages;

public class LanguagesRepositoryTest extends BaseJpaTest
{

	@Autowired
	private LanguagesRepository repository;

	@Test
	public void whenFindByNameThenReturnLanguages()
	{
		Languages distinctByName;
		Languages entity;
		String iso639Dash1;
		String name;

		iso639Dash1 = "xy";
		name = "alienish";

		entity = Languages.builder().iso639Dash1(iso639Dash1).name(name).build();

		entityManager.persist(entity);
		entityManager.flush();

		// when
		distinctByName = repository.findDistinctByName(entity.getName());
		// then
		assertThat(distinctByName.getName()).isEqualTo(entity.getName());
		// cleanup
		repository.delete(entity);
		// when
		distinctByName = repository.findDistinctByName(name);
		assertThat(distinctByName).isNull();
	}

}
