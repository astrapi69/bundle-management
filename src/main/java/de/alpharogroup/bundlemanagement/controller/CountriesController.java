package de.alpharogroup.bundlemanagement.controller;

import de.alpharogroup.bundlemanagement.mapper.CountriesMapper;
import de.alpharogroup.bundlemanagement.viewmodel.Country;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.alpharogroup.bundlemanagement.configuration.ApplicationConfiguration;
import de.alpharogroup.bundlemanagement.jpa.entity.Countries;
import de.alpharogroup.bundlemanagement.jpa.repository.CountriesRepository;
import de.alpharogroup.bundlemanagement.service.CountriesService;
import de.alpharogroup.spring.controller.AbstractRestController;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@RestController
@RequestMapping(ApplicationConfiguration.REST_VERSION + CountriesController.REST_PATH)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CountriesController
	extends
		AbstractRestController<Countries, UUID, CountriesRepository, Country>
{

	public static final String REST_PATH = "/country";
	public static final String REST_PATH_FIND = "/find";

	CountriesMapper mapper;

	CountriesService service;

	public CountriesController(CountriesMapper mapper, CountriesService service)
	{
		super(mapper, service);
		this.mapper = mapper;
		this.service = service;
	}

	/**
	 * Call this link <a href="http://localhost:5000/v1/country/find?name=Greece"></a> and adapt to
	 * your parameters.
	 */
	@CrossOrigin(origins = "*")
	@GetMapping(path = CountriesController.REST_PATH_FIND, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find the Country object from the given name")
	public ResponseEntity<Country> findByName(@RequestParam("name") String name)
	{
		Countries countries = this.service.findByName(name);
		Country country = mapper.toDto(countries);
		return ResponseEntity.ok(country);
	}
}
