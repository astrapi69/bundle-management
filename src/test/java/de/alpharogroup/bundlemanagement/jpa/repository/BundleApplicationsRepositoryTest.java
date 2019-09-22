package de.alpharogroup.bundlemanagement.jpa.repository;

import de.alpharogroup.bundlemanagement.jpa.entity.BaseNames;
import de.alpharogroup.bundlemanagement.jpa.entity.BundleApplications;
import de.alpharogroup.bundlemanagement.jpa.entity.LanguageLocales;
import de.alpharogroup.collections.set.SetFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BundleApplicationsRepositoryTest extends BaseJpaTest
{

	@Autowired
	private BundleApplicationsRepository repository;

	@Autowired
	private LanguageLocalesRepository languageLocalesRepository;

	@Test
	public void whenFindByNameThenReturnBundleApplications()
	{
		String locale = "de";
		LanguageLocales languageLocales = languageLocalesRepository.findDistinctByLocale(locale);
		if(languageLocales==null){
			languageLocales = LanguageLocales.builder().locale(locale).build();
			entityManager.persist(languageLocales);
			entityManager.flush();
		}

		BundleApplications entity = BundleApplications.builder()
			.name("test-bundle-app")
			.defaultLocale(languageLocales).supportedLocales(SetFactory.newHashSet(languageLocales))
			.build();

		entityManager.persist(entity);
		entityManager.flush();

		// when
		BundleApplications distinctByName = repository.findDistinctByName(entity.getName());
		// then
		assertThat(distinctByName.getName()).isEqualTo(entity.getName());
	}

}
