package de.alpharogroup.bundlemanagement.jpa.repository;

import de.alpharogroup.bundlemanagement.jpa.entity.BaseNames;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BaseNamesRepositoryTest extends BaseJpaTest
{

	@Autowired
	private BaseNamesRepository repository;

	@Test
	public void whenFindByNameThenReturnBaseNames()
	{
		BaseNames entity = BaseNames.builder().name("messages").build();

		entityManager.persist(entity);
		entityManager.flush();

		// when
		BaseNames distinctByName = repository.findDistinctByName(entity.getName());
		// then
		assertThat(distinctByName.getName()).isEqualTo(entity.getName());
	}

}
