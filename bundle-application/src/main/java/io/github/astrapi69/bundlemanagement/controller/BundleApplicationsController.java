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
package io.github.astrapi69.bundlemanagement.controller;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.validation.Valid;

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

import io.github.astrapi69.bundlemanagement.enumtype.ActionRestPath;
import io.github.astrapi69.bundlemanagement.enumtype.AppRestPath;
import io.github.astrapi69.bundlemanagement.jpa.entity.BundleApplications;
import io.github.astrapi69.bundlemanagement.jpa.entity.BundleNames;
import io.github.astrapi69.bundlemanagement.jpa.repository.BundleApplicationsRepository;
import io.github.astrapi69.bundlemanagement.mapper.BundleApplicationsMapper;
import io.github.astrapi69.bundlemanagement.service.BundleApplicationsService;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleApplication;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleName;
import io.github.astrapi69.collection.set.SetExtensions;
import io.github.astrapi69.spring.controller.AbstractRestController;
import io.github.astrapi69.spring.rest.BaseActionRestPath;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping(AppRestPath.REST_VERSION + AppRestPath.REST_BUNDLE_APPLICATIONS)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BundleApplicationsController
	extends
		AbstractRestController<BundleApplications, UUID, BundleApplicationsRepository, BundleApplication>
{

	@Autowired
	BundleApplicationsMapper mapper;
	@Autowired
	BundleApplicationsService service;

	public BundleApplicationsController(BundleApplicationsMapper mapper,
		BundleApplicationsService service)
	{
		super(mapper, service);
		this.mapper = mapper;
		this.service = service;
	}

	@RequestMapping(value = BaseActionRestPath.ACTION_DELETE, method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete the given bundle application")
	public ResponseEntity<BundleApplication> delete(
		@Valid @RequestBody BundleApplication bundleApplication)
	{
		final BundleApplications bundleApplications = this.service
			.find(bundleApplication.getName());
		this.service.delete(bundleApplications);
		return ResponseEntity.ok(mapper.toDto(bundleApplications));
	}

	@RequestMapping(value = BaseActionRestPath.ACTION_UPDATE, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update the given bundle application")
	public ResponseEntity<BundleApplication> update(
		@Valid @RequestBody BundleApplication bundleApplication)
	{
		BundleApplications entity = mapper.toEntity(bundleApplication);
		BundleApplications bundleApplications = this.service.update(entity);
		return ResponseEntity.ok(mapper.toDto(bundleApplications));
	}

	@CrossOrigin(origins = "*")
	@GetMapping(path = BaseActionRestPath.ACTION_FIND_ALL, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find all BundleApplications objects")
	public ResponseEntity<Iterable<BundleApplication>> findAllBundleApplications()
	{
		return super.findAll();
	}

	@CrossOrigin(origins = "*")
	@GetMapping(path = ActionRestPath.ACTION_FIND_ALL_BUNDLE_NAMES, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find all BundleName objects from the given name of the owner")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bundleappname", value = "the name of the bundle application", dataType = "string", paramType = "query") })
	public ResponseEntity<Set<BundleName>> findAllBundleNames(
		@RequestParam("bundleappname") String bundleappname)
	{
		final BundleApplications bundleApplication = this.service.find(bundleappname);
		Set<BundleNames> bundleNames = this.service.find(bundleApplication);
		List<BundleName> bundleNameList = mapper.map(bundleNames, BundleName.class);
		return ResponseEntity.ok(SetExtensions.toSet(bundleNameList));
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(path = ActionRestPath.ACTION_FIND_BY_BUNDLE_NAME, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find the BundleApplication object from the given BundleName object")
	public ResponseEntity<BundleApplication> findByBundleName(
		@Valid @RequestBody BundleName bundlename)
	{
		final BundleApplications bundleApplication = this.service
			.find(bundlename.getOwner().getName());
		return ResponseEntity
			.status(
				bundleApplication != null ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value())
			.body(mapper.toDto(bundleApplication));
	}

	@CrossOrigin(origins = "*")
	@GetMapping(path = BaseActionRestPath.ACTION_FIND, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find the bundle application from the given arguments.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bundleappname", value = "the name of the bundle application", dataType = "string", paramType = "query") })
	public ResponseEntity<BundleApplication> find(
		@RequestParam("bundleappname") String bundleappname)
	{
		final BundleApplications bundleApplication = this.service.find(bundleappname);
		return ResponseEntity
			.status(
				bundleApplication != null ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value())
			.body(bundleApplication != null ? mapper.toDto(bundleApplication) : null);
	}
}
