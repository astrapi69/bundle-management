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
		String owner;
		String baseName;
		String locale;
		String key;
		// new scenario...
		owner = "test-bundle-application";
		baseName = "test";
		locale = "de_DE";
		key = "com.example.gui.prop.with.params.label";
		Resourcebundles entity = repository
			.findDistinctByOwnerAndBaseNameAndLocaleAndKeyAndValue(owner, baseName, locale, key);

		assertNotNull(entity);
	}

	@Test
	public void whenFindByNameThenReturnResourcebundles()
	{

		String locale = "de";
		LanguageLocales languageLocales = languageLocalesRepository.findDistinctByLocale(locale);
		if (languageLocales == null)
		{
			languageLocales = LanguageLocales.builder().locale(locale).build();
			entityManager.persist(languageLocales);
			entityManager.flush();
		}

		BundleApplications bundleApplications = BundleApplications.builder().name("test-bundle-app")
			.defaultLocale(languageLocales).supportedLocales(SetFactory.newHashSet(languageLocales))
			.build();

		entityManager.persist(bundleApplications);
		entityManager.flush();

		BaseNames baseNames = BaseNames.builder().name("messages").build();

		entityManager.persist(baseNames);
		entityManager.flush();

		BundleNames bundleNames = BundleNames.builder().baseName(baseNames)
			.filepath("/opt/i18n/foo.yml").owner(bundleApplications).locale(languageLocales)
			.build();

		entityManager.persist(bundleNames);
		entityManager.flush();

		PropertiesKeys propertiesKeys = PropertiesKeys.builder().name("foo.key").build();

		PropertiesValues propertiesValues = PropertiesValues.builder().name("bar value").build();

		Resourcebundles entity = Resourcebundles.builder().bundleName(bundleNames)
			.key(propertiesKeys).value(propertiesValues).build();

		entityManager.persist(entity);
		entityManager.flush();

		// when
		List<Resourcebundles> byName = repository.findByOwnerAndBaseNameAndLocaleAndKeyAndValue(
			bundleApplications.getName(), baseNames.getName(), languageLocales.getLocale(),
			propertiesKeys.getName());
		// then
		assertNotNull(byName);
		assertTrue(byName.contains(entity));
	}

}
