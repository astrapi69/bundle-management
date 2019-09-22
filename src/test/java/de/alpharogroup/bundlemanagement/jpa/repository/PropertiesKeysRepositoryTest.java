package de.alpharogroup.bundlemanagement.jpa.repository;

import de.alpharogroup.bundlemanagement.jpa.entity.Languages;
import de.alpharogroup.bundlemanagement.jpa.entity.PropertiesKeys;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PropertiesKeysRepositoryTest extends BaseJpaTest
{

	@Autowired
	private PropertiesKeysRepository repository;

	@Test
	public void whenFindByNameThenReturnPropertiesKeys()
	{
		PropertiesKeys entity = PropertiesKeys.builder().name("foo.key").build();

		entityManager.persist(entity);
		entityManager.flush();

		// when
		List<PropertiesKeys> byName = repository.findByName(entity.getName());
		// then
		assertNotNull(byName);
		assertTrue(byName.contains(entity));
	}

}
