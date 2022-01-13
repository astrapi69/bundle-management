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

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

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
import io.github.astrapi69.bundlemanagement.jpa.entity.LanguageLocales;
import io.github.astrapi69.bundlemanagement.jpa.repository.LanguageLocalesRepository;
import io.github.astrapi69.bundlemanagement.mapper.LanguageLocalesMapper;
import io.github.astrapi69.bundlemanagement.service.LanguageLocalesService;
import io.github.astrapi69.bundlemanagement.viewmodel.LanguageLocale;
import io.github.astrapi69.spring.controller.AbstractRestController;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(AppRestPath.REST_VERSION + AppRestPath.REST_LANGUAGE_LOCALES)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LanguageLocalesController
	extends
		AbstractRestController<LanguageLocales, UUID, LanguageLocalesRepository, LanguageLocale>
{

	LanguageLocalesMapper mapper;

	LanguageLocalesService service;

	public LanguageLocalesController(LanguageLocalesMapper mapper, LanguageLocalesService service)
	{
		super(mapper, service);
		this.mapper = mapper;
		this.service = service;
	}

	@CrossOrigin(origins = "*")
	@GetMapping(path = ActionRestPath.ACTION_FIND_BY_LOCALE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find the LanguageLocale object from the given locale name")
	public ResponseEntity<LanguageLocale> findByName(@RequestParam("locale") String locale)
	{
		LanguageLocales languageLocales = this.service.find(locale);
		return languageLocales != null
			? ResponseEntity.ok(mapper.toDto(languageLocales))
			: ResponseEntity.ok(null);
	}

	@CrossOrigin(origins = "*")
	@GetMapping(path = ActionRestPath.ACTION_FIND_ALL, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find all LanguageLocales objects")
	public ResponseEntity<Iterable<LanguageLocale>> findAllLanguageLocales()
	{
		return super.findAll();
	}

}
