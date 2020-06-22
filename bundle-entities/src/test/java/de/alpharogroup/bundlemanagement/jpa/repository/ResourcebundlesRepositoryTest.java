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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.alpharogroup.bundlemanagement.jpa.entity.BaseNames;
import de.alpharogroup.bundlemanagement.jpa.entity.BundleApplications;
import de.alpharogroup.bundlemanagement.jpa.entity.BundleNames;
import de.alpharogroup.bundlemanagement.jpa.entity.LanguageLocales;
import de.alpharogroup.bundlemanagement.jpa.entity.PropertiesKeys;
import de.alpharogroup.bundlemanagement.jpa.entity.PropertiesValues;
import de.alpharogroup.bundlemanagement.jpa.entity.Resourcebundles;
import de.alpharogroup.collections.set.SetFactory;

public class ResourcebundlesRepositoryTest extends BaseJpaTest
{

	@Autowired
	private LanguageLocalesRepository languageLocalesRepository;

	@Autowired
	private ResourcebundlesRepository repository;


	@Test
	public void findResourceKey()
	{
		Resourcebundles entity;
		String owner;
		String baseName;
		String locale;
		String key;
		// new scenario...
		owner = "test-bundle-application";
		baseName = "test";
		locale = "de_DE";
		key = "com.example.gui.prop.with.params.label";
		entity = repository.findDistinctByOwnerAndBaseNameAndLocaleAndKeyAndValue(owner, baseName,
			locale, key);

		assertNotNull(entity);
	}

	@Test
	public void whenFindByNameThenReturnResourcebundles()
	{
		String locale;
		LanguageLocales languageLocales;
		BundleApplications bundleApplications;
		BaseNames baseNames;
		BundleNames bundleNames;
		PropertiesKeys propertiesKeys;
		PropertiesValues propertiesValues;
		Resourcebundles entity;
		List<Resourcebundles> byName;

		locale = "de";
		languageLocales = languageLocalesRepository.findDistinctByLocale(locale);
		if (languageLocales == null)
		{
			languageLocales = LanguageLocales.builder().locale(locale).build();
			entityManager.persist(languageLocales);
			entityManager.flush();
		}

		bundleApplications = BundleApplications.builder().name("test-bundle-app")
			.defaultLocale(languageLocales).supportedLocales(SetFactory.newHashSet(languageLocales))
			.build();

		entityManager.persist(bundleApplications);
		entityManager.flush();

		baseNames = BaseNames.builder().name("messages").build();

		entityManager.persist(baseNames);
		entityManager.flush();

		bundleNames = BundleNames.builder().baseName(baseNames).filepath("/opt/i18n")
			.owner(bundleApplications).locale(languageLocales).build();

		entityManager.persist(bundleNames);
		entityManager.flush();

		propertiesKeys = PropertiesKeys.builder().name("foo.key").build();

		entityManager.persist(propertiesKeys);
		entityManager.flush();

		propertiesValues = PropertiesValues.builder().name("bar value").build();

		entityManager.persist(propertiesValues);
		entityManager.flush();

		entity = Resourcebundles.builder().bundleName(bundleNames).key(propertiesKeys)
			.value(propertiesValues).build();

		entityManager.persist(entity);
		entityManager.flush();

		// when
		byName = repository.findByOwnerAndBaseNameAndLocaleAndKeyAndValue(
			bundleApplications.getName(), baseNames.getName(), languageLocales.getLocale(),
			propertiesKeys.getName());
		// then
		assertNotNull(byName);
		assertTrue(byName.contains(entity));
	}

}
