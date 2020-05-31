package de.alpharogroup.bundlemanagement.jpa.repository;

import de.alpharogroup.bundlemanagement.jpa.entity.BaseNames;
import de.alpharogroup.bundlemanagement.jpa.entity.BundleApplications;
import de.alpharogroup.bundlemanagement.jpa.entity.BundleNames;
import de.alpharogroup.bundlemanagement.jpa.entity.LanguageLocales;
import de.alpharogroup.collections.set.SetFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BundleNamesRepositoryTest extends BaseJpaTest
{

	@Autowired
	private LanguageLocalesRepository languageLocalesRepository;

	@Autowired
	private BundleNamesRepository repository;

	@Autowired
	private BundleApplicationsRepository bundleApplicationsRepository;

	@Test
	public void whenFindByNameThenReturnBundleNames()
	{
		BundleApplications bundleApplications;
		BaseNames baseNames;
		BundleNames entity;
		LanguageLocales languageLocales;
		BundleNames distinctByName;
		String locale;

		locale = "xy";
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

		entity = BundleNames.builder().baseName(baseNames).filepath("/opt/i18n")
			.owner(bundleApplications).locale(languageLocales).build();

		entityManager.persist(entity);
		entityManager.flush();

		// when
		distinctByName = repository.findDistinctByOwnerAndBaseNameAndLocale(
			bundleApplications.getName(), baseNames.getName(), locale);
		// then
		assertThat(distinctByName.getFilepath()).isEqualTo(entity.getFilepath());
		// cleanup
		repository.delete(entity);
		// when
		distinctByName = repository.findDistinctByOwnerAndBaseNameAndLocale(bundleApplications.getName(), baseNames.getName(), locale);
		assertThat(distinctByName).isNull();
	}

	@Test
	public void testFindByOwner() {
		BundleApplications distinctByName = bundleApplicationsRepository
			.findDistinctByName("foo-bar.com");
		List<BundleNames> byOwner = repository.findByOwner(distinctByName);

		assertThat(byOwner.size()).isEqualTo(2);
	}


}
