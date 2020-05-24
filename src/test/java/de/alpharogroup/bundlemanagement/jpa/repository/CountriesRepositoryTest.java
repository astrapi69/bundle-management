package de.alpharogroup.bundlemanagement.jpa.repository;

import de.alpharogroup.bundlemanagement.jpa.entity.Countries;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CountriesRepositoryTest extends BaseJpaTest
{

	@Autowired private CountriesRepository repository;

	@Test public void whenFindByNameThenReturnCountries()
	{
		Countries distinctByName;
		String countryName;
		Countries entity;
		String iso3166a2name;
		countryName = "Alienland";
		iso3166a2name = "XY";

		entity = Countries.builder().name(countryName).iso3166a2name(iso3166a2name).build();

		entityManager.persist(entity);
		entityManager.flush();
		// when
		distinctByName = repository.findDistinctByName(entity.getName());
		// then
		assertThat(distinctByName.getName()).isEqualTo(entity.getName());
		// cleanup
		repository.delete(entity);
		// when
		distinctByName = repository.findDistinctByName(countryName);
		assertThat(distinctByName).isNull();

	}

}
