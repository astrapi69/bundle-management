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

import io.github.astrapi69.bundlemanagement.enums.ActionRestPath;
import io.github.astrapi69.bundlemanagement.enums.AppRestPath;
import io.github.astrapi69.bundlemanagement.jpa.entity.LanguageLocales;
import io.github.astrapi69.bundlemanagement.viewmodel.LanguageLocale;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.astrapi69.bundlemanagement.configuration.ApplicationConfiguration;
import io.github.astrapi69.bundlemanagement.jpa.entity.Languages;
import io.github.astrapi69.bundlemanagement.jpa.repository.LanguagesRepository;
import io.github.astrapi69.bundlemanagement.mapper.LanguagesMapper;
import io.github.astrapi69.bundlemanagement.service.LanguagesService;
import io.github.astrapi69.bundlemanagement.viewmodel.Language;
import io.github.astrapi69.spring.controller.AbstractRestController;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping(AppRestPath.REST_VERSION + AppRestPath.REST_LANGUAGES)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LanguagesController
	extends
		AbstractRestController<Languages, UUID, LanguagesRepository, Language>
{

	LanguagesMapper mapper;

	LanguagesService service;

	public LanguagesController(LanguagesMapper mapper, LanguagesService service)
	{
		super(mapper, service);
		this.mapper = mapper;
		this.service = service;
	}

	/**
	 * Call this link <a href="http://localhost:5000/v1/language/find?name=German"></a> and adapt to
	 * your parameters.
	 */
	@CrossOrigin(origins = "*")
	@GetMapping(path = ActionRestPath.ACTION_FIND, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find the Language object from the given name")
	public ResponseEntity<Language> findByName(@RequestParam("name") String name)
	{
		Languages languages = this.service.findByName(name);
		return ResponseEntity.ok(mapper.toDto(languages));
	}

	/**
	 * Call this link <a href="http://localhost:5000/v1/language/find/by/code?code=German"></a> and
	 * adapt to your parameters.
	 */
	@CrossOrigin(origins = "*")
	@GetMapping(path = ActionRestPath.ACTION_FIND_BY_CODE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find the Language object from the given name")
	public ResponseEntity<Language> findByCode(@RequestParam("code") String code)
	{
		Languages languages = this.service.findByCode(code);
		return ResponseEntity.ok(mapper.toDto(languages));
	}

	@CrossOrigin(origins = "*")
	@GetMapping(path = ActionRestPath.ACTION_FIND_ALL, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find all LanguageLocales objects")
	public ResponseEntity<Iterable<Language>> findAllLanguageLocales()
	{
		Iterable<Language> all = super.findAll();
		return ResponseEntity.ok(new HashSet<>((Collection)all));
	}
}
