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

import io.github.astrapi69.bundlemanagement.integration.AbstractIntegrationTest;
import io.github.astrapi69.bundlemanagement.jpa.entity.BundleApplications;
import io.github.astrapi69.bundlemanagement.jpa.entity.LanguageLocales;
import io.github.astrapi69.collections.set.SetFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BundleApplicationsRepositoryTest extends AbstractIntegrationTest
{

	@Autowired
	private LanguageLocalesRepository languageLocalesRepository;

	@Autowired
	private BundleApplicationsRepository repository;

	@Test
	public void whenFindByNameThenReturnBundleApplications()
	{
		String locale = "de";
		LanguageLocales languageLocales = languageLocalesRepository.findDistinctByLocale(locale);
		if (languageLocales == null)
		{
			languageLocales = LanguageLocales.builder().locale(locale).build();
			entityManager.persist(languageLocales);
			entityManager.flush();
		}

		BundleApplications entity = BundleApplications.builder().name("test-bundle-app")
			.defaultLocale(languageLocales).supportedLocales(SetFactory.newHashSet(languageLocales))
			.build();

		entityManager.persist(entity);
		entityManager.flush();

		// when
		BundleApplications distinctByName = repository.findDistinctByName(entity.getName());
		// then
		assertThat(distinctByName.getName()).isEqualTo(entity.getName());
	}

	@Test
	public void testFindByLanguageLocale()
	{
		List<BundleApplications> bundleApplications;

		String locale = "de";
		LanguageLocales languageLocales = languageLocalesRepository.findDistinctByLocale(locale);
		bundleApplications = repository.findAllByLanguageLocale(languageLocales);
		// then
		assertThat(bundleApplications.size()).isEqualTo(2);
	}

	@Test
	public void testFindAllByLanguageLocaleWithInnerJoin()
	{
		List<BundleApplications> bundleApplications;

		String locale = "de_DE";
		LanguageLocales languageLocales = languageLocalesRepository.findDistinctByLocale(locale);
		bundleApplications = repository
			.findAllByLanguageLocaleWithInnerJoin(languageLocales.getId());
		// then
		assertThat(bundleApplications.size()).isEqualTo(1);
	}
}
