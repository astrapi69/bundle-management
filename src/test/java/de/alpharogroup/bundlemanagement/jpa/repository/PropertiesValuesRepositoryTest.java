package de.alpharogroup.bundlemanagement.jpa.repository;

import de.alpharogroup.bundlemanagement.jpa.entity.PropertiesValues;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PropertiesValuesRepositoryTest extends BaseJpaTest
{

	@Autowired
	private PropertiesValuesRepository repository;

	@Test
	public void whenFindByNameThenReturnPropertiesValues()
	{
		PropertiesValues entity = PropertiesValues.builder().name("foo-value").build();

		entityManager.persist(entity);
		entityManager.flush();

		// when
		List<PropertiesValues> byName = repository.findByName(entity.getName());
		// then
		assertNotNull(byName);
		assertTrue(byName.contains(entity));
	}

}
