package de.alpharogroup.bundlemanagement.jpa.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.alpharogroup.bundlemanagement.jpa.entity.BaseNames;
import de.alpharogroup.bundlemanagement.jpa.entity.BundleApplications;
import de.alpharogroup.bundlemanagement.jpa.entity.BundleNames;
import de.alpharogroup.bundlemanagement.jpa.entity.LanguageLocales;
import de.alpharogroup.collections.set.SetFactory;

public class BundleNamesRepositoryTest extends BaseJpaTest
{

	@Autowired
	private LanguageLocalesRepository languageLocalesRepository;

	@Autowired
	private BundleNamesRepository repository;

	@Test
	public void whenFindByNameThenReturnBundleNames()
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

		BundleNames entity = BundleNames.builder().baseName(baseNames).filepath("/opt/i18n/foo.yml")
			.owner(bundleApplications).locale(languageLocales).build();

		entityManager.persist(entity);
		entityManager.flush();

		// when
		BundleNames distinctByName = repository.findDistinctByOwnerAndBaseNameAndLocale(
			bundleApplications.getName(), baseNames.getName(), locale);
		// then
		assertThat(distinctByName.getFilepath()).isEqualTo(entity.getFilepath());
	}

}
