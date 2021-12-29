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

import javax.validation.Valid;

import io.github.astrapi69.bundlemanagement.enums.ActionRestPath;
import io.github.astrapi69.bundlemanagement.enums.AppRestPath;
import io.github.astrapi69.bundlemanagement.service.BundleNamesService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.astrapi69.bundlemanagement.jpa.entity.BundleNames;
import io.github.astrapi69.bundlemanagement.jpa.repository.BundleNamesRepository;
import io.github.astrapi69.bundlemanagement.mapper.BundleNamesMapper;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleName;
import io.github.astrapi69.spring.controller.AbstractRestController;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(AppRestPath.REST_VERSION + AppRestPath.REST_BUNDLE_NAMES)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BundleNamesController
	extends
		AbstractRestController<BundleNames, UUID, BundleNamesRepository, BundleName>
{

	@Autowired
	BundleNamesMapper mapper;
	@Autowired
	BundleNamesService service;

	public BundleNamesController(BundleNamesMapper mapper, BundleNamesService service)
	{
		super(mapper, service);
		this.mapper = mapper;
		this.service = service;
	}

	@RequestMapping(value = ActionRestPath.ACTION_DELETE,
		method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete the given bundle name")
	public void delete(@Valid @RequestBody BundleName bundleName)
	{
		BundleNames bundleNames = this.mapper.toEntity(bundleName);
		this.service.delete(bundleNames);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(path = ActionRestPath.ACTION_SAVE_OR_UPDATE,
		method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "save the value from the given arguments.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "bundleappname", value = "the name of the bundle application", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "basename", value = "the base name", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "locale", value = "the locale", dataType = "string", paramType = "query") })
	public ResponseEntity<BundleName> saveOrUpdate(@RequestParam("bundleappname") String bundleappname,
		@RequestParam("basename") String basename, @RequestParam("locale") String locale)
	{
		BundleNames bundleNames = this.service.getOrCreateNewBundleNames(bundleappname, basename, locale);

		return ResponseEntity
			.status(
				bundleNames != null ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value())
			.body(mapper.toDto(bundleNames));
	}
}
