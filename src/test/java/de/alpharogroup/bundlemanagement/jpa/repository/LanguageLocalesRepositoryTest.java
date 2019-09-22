package de.alpharogroup.bundlemanagement.jpa.repository;

import de.alpharogroup.bundlemanagement.jpa.entity.BaseNames;
import de.alpharogroup.bundlemanagement.jpa.entity.LanguageLocales;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LanguageLocalesRepositoryTest extends BaseJpaTest
{

	@Autowired
	private LanguageLocalesRepository repository;

	@Test
	public void whenFindByNameThenReturnLanguageLocales()
	{
		LanguageLocales entity = LanguageLocales.builder().locale("el").build();

		entityManager.persist(entity);
		entityManager.flush();

		// when
		LanguageLocales distinctByName = repository.findDistinctByLocale(entity.getLocale());
		// then
		assertThat(distinctByName.getLocale()).isEqualTo(entity.getLocale());
	}

}
