package de.alpharogroup.bundlemanagement.jpa.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.alpharogroup.bundlemanagement.jpa.entity.PropertiesKeys;

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
