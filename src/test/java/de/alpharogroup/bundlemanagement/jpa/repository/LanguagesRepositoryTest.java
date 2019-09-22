package de.alpharogroup.bundlemanagement.jpa.repository;

import de.alpharogroup.bundlemanagement.jpa.entity.LanguageLocales;
import de.alpharogroup.bundlemanagement.jpa.entity.Languages;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LanguagesRepositoryTest extends BaseJpaTest
{

	@Autowired
	private LanguagesRepository repository;

	@Test
	public void whenFindByNameThenReturnLanguages()
	{
		Languages entity = Languages.builder().iso639Dash1("el").build();

		entityManager.persist(entity);
		entityManager.flush();

		// when
		Languages distinctByName = repository.findDistinctByName(entity.getName());
		// then
		assertThat(distinctByName.getName()).isEqualTo(entity.getName());
	}

}
