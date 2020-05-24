package de.alpharogroup.bundlemanagement.jpa.repository;

import de.alpharogroup.bundlemanagement.jpa.entity.*;
import de.alpharogroup.collections.set.SetFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
		entity = repository
			.findDistinctByOwnerAndBaseNameAndLocaleAndKeyAndValue(owner, baseName, locale, key);

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

		bundleNames = BundleNames.builder().baseName(baseNames)
			.filepath("/opt/i18n").owner(bundleApplications).locale(languageLocales)
			.build();

		entityManager.persist(bundleNames);
		entityManager.flush();

		propertiesKeys = PropertiesKeys.builder().name("foo.key").build();

		entityManager.persist(propertiesKeys);
		entityManager.flush();

		propertiesValues = PropertiesValues.builder().name("bar value").build();

		entityManager.persist(propertiesValues);
		entityManager.flush();

		entity = Resourcebundles.builder().bundleName(bundleNames)
			.key(propertiesKeys).value(propertiesValues).build();

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
