/**
 * The MIT License
 * <p>
 * Copyright (C) 2015 Asterios Raptis
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.bundlemanagement.controller;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.validation.Valid;

import de.alpharogroup.bundlemanagement.mapper.BundleNamesMapper;
import de.alpharogroup.bundlemanagement.service.BundleNamesService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.alpharogroup.bundlemanagement.configuration.ApplicationConfiguration;
import de.alpharogroup.bundlemanagement.jpa.entity.BundleApplications;
import de.alpharogroup.bundlemanagement.jpa.entity.BundleNames;
import de.alpharogroup.bundlemanagement.jpa.repository.BundleNamesRepository;
import de.alpharogroup.bundlemanagement.mapper.BundleApplicationsMapper;
import de.alpharogroup.bundlemanagement.service.BundleApplicationsService;
import de.alpharogroup.bundlemanagement.viewmodel.BundleApplication;
import de.alpharogroup.bundlemanagement.viewmodel.BundleName;
import de.alpharogroup.collections.set.SetExtensions;
import de.alpharogroup.spring.controller.AbstractRestController;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(ApplicationConfiguration.REST_VERSION + BundleNamesController.REST_PATH)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BundleNamesController
	extends
		AbstractRestController<BundleNames, UUID, BundleNamesRepository, BundleName>
{

	public static final String REST_PATH = "/bundle/names";
	public static final String REST_PATH_FIND = "/find";
	public static final String REST_PATH_FIND_ALL_BUNDLENAMES = "/find/all/bundlenames";
	public static final String REST_PATH_BY_BUNDLENAME = "/find/by/bundlename";
	public static final String REST_PATH_DELETE = "/delete";

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

	@RequestMapping(value = BundleNamesController.REST_PATH_DELETE, method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete the given bundle application")
	public void delete(@Valid @RequestBody BundleName bundleName)
	{
		BundleNames bundleNames = this.mapper.toEntity(bundleName);
		this.service.delete(bundleNames);
	}

}
