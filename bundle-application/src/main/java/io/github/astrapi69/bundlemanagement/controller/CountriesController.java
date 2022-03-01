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
package io.github.astrapi69.bundlemanagement.controller;

import java.util.UUID;

import io.github.astrapi69.spring.rest.BaseActionRestPath;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.astrapi69.bundlemanagement.enums.ActionRestPath;
import io.github.astrapi69.bundlemanagement.enums.AppRestPath;
import io.github.astrapi69.bundlemanagement.jpa.entity.Countries;
import io.github.astrapi69.bundlemanagement.jpa.repository.CountriesRepository;
import io.github.astrapi69.bundlemanagement.mapper.CountriesMapper;
import io.github.astrapi69.bundlemanagement.service.CountriesService;
import io.github.astrapi69.bundlemanagement.viewmodel.Country;
import io.github.astrapi69.spring.controller.AbstractRestController;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(AppRestPath.REST_VERSION + AppRestPath.REST_COUNTRIES)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CountriesController
	extends
		AbstractRestController<Countries, UUID, CountriesRepository, Country>
{

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
	@GetMapping(path = BaseActionRestPath.ACTION_FIND, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find the Country object from the given name")
	public ResponseEntity<Country> findByName(@RequestParam("name") String name)
	{
		Countries countries = this.service.findByName(name);
		Country country = mapper.toDto(countries);
		return ResponseEntity.ok(country);
	}

	@CrossOrigin(origins = "*")
	@GetMapping(path = BaseActionRestPath.ACTION_FIND_ALL, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find all Countries objects")
	public ResponseEntity<Iterable<Country>> findAllCountries()
	{
		return super.findAll();
	}
}
