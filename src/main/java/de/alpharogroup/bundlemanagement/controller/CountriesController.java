/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.bundlemanagement.controller;

import de.alpharogroup.bundlemanagement.configuration.ApplicationConfiguration;
import de.alpharogroup.bundlemanagement.jpa.entity.Countries;
import de.alpharogroup.bundlemanagement.jpa.repository.CountriesRepository;
import de.alpharogroup.bundlemanagement.mapper.CountriesMapper;
import de.alpharogroup.bundlemanagement.service.CountriesService;
import de.alpharogroup.bundlemanagement.viewmodel.Country;
import de.alpharogroup.spring.controller.AbstractRestController;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
