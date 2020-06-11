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

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.alpharogroup.bundlemanagement.configuration.ApplicationConfiguration;
import de.alpharogroup.bundlemanagement.jpa.entity.Languages;
import de.alpharogroup.bundlemanagement.jpa.repository.LanguagesRepository;
import de.alpharogroup.bundlemanagement.mapper.LanguagesMapper;
import de.alpharogroup.bundlemanagement.service.LanguagesService;
import de.alpharogroup.bundlemanagement.viewmodel.Language;
import de.alpharogroup.spring.controller.AbstractRestController;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping(ApplicationConfiguration.REST_VERSION + LanguagesController.REST_PATH)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LanguagesController
	extends
		AbstractRestController<Languages, UUID, LanguagesRepository, Language>
{

	public static final String REST_PATH = "/language";
	public static final String REST_PATH_FIND = "/find";
	public static final String REST_PATH_FIND_BY_CODE = "/find/by/code";

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
	@GetMapping(path = LanguagesController.REST_PATH_FIND, produces = MediaType.APPLICATION_JSON_VALUE)
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
	@GetMapping(path = LanguagesController.REST_PATH_FIND_BY_CODE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find the Language object from the given name")
	public ResponseEntity<Language> findByCode(@RequestParam("code") String code)
	{
		Languages languages = this.service.findByCode(code);
		return ResponseEntity.ok(mapper.toDto(languages));
	}
}
