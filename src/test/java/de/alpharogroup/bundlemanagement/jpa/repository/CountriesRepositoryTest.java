package de.alpharogroup.bundlemanagement.jpa.repository;

import de.alpharogroup.bundlemanagement.jpa.entity.BaseNames;
import de.alpharogroup.bundlemanagement.jpa.entity.Countries;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CountriesRepositoryTest extends BaseJpaTest
{

	@Autowired
	private CountriesRepository repository;

	@Test
	public void whenFindByNameThenReturnCountries()
	{
		Countries entity = Countries.builder().name("Greece").iso3166a2name("GR").build();

		entityManager.persist(entity);
		entityManager.flush();

		// when
		Countries distinctByName = repository.findDistinctByName(entity.getName());
		// then
		assertThat(distinctByName.getName()).isEqualTo(entity.getName());
	}

}
