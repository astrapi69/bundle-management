package de.alpharogroup.bundlemanagement.jpa.repository;

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
		LanguageLocales distinctByName;
		LanguageLocales entity;
		String locale;

		locale = "xy";
		entity = LanguageLocales.builder().locale(locale).build();

		entityManager.persist(entity);
		entityManager.flush();

		// when
		distinctByName = repository.findDistinctByLocale(entity.getLocale());
		// then
		assertThat(distinctByName.getLocale()).isEqualTo(entity.getLocale());
		// cleanup
		repository.delete(entity);
		// when
		distinctByName = repository.findDistinctByLocale(locale);
		assertThat(distinctByName).isNull();
	}

}
