package de.alpharogroup.bundlemanagement.jpa.repository;

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
		Languages distinctByName;
		Languages entity;
		String iso639Dash1;
		String name;

		iso639Dash1 = "xy";
		name = "alienish";

		entity = Languages.builder().iso639Dash1(iso639Dash1).name(name).build();

		entityManager.persist(entity);
		entityManager.flush();

		// when
		distinctByName = repository.findDistinctByName(entity.getName());
		// then
		assertThat(distinctByName.getName()).isEqualTo(entity.getName());
		// cleanup
		repository.delete(entity);
		// when
		distinctByName = repository.findDistinctByName(name);
		assertThat(distinctByName).isNull();
	}

}
