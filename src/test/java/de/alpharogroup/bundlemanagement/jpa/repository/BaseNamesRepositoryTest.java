package de.alpharogroup.bundlemanagement.jpa.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import de.alpharogroup.bundlemanagement.jpa.entity.BaseNames;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BaseNamesRepositoryTest
{

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private BaseNamesRepository repository;

	@Test
	public void whenFindByNameThenReturnBaseNames()
	{
		BaseNames baseNames = BaseNames.builder().name("messages").build();

		entityManager.persist(baseNames);
		entityManager.flush();

		// when
		BaseNames distinctByName = repository.findDistinctByName(baseNames.getName());
		// then
		assertThat(distinctByName.getName()).isEqualTo(baseNames.getName());
	}
}
