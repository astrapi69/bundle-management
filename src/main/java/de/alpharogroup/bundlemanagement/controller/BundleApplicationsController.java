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
import de.alpharogroup.bundlemanagement.jpa.entity.BundleApplications;
import de.alpharogroup.bundlemanagement.jpa.entity.BundleNames;
import de.alpharogroup.bundlemanagement.jpa.repository.BundleApplicationsRepository;
import de.alpharogroup.bundlemanagement.mapper.BundleApplicationsMapper;
import de.alpharogroup.bundlemanagement.service.BundleApplicationsService;
import de.alpharogroup.bundlemanagement.viewmodel.BundleApplication;
import de.alpharogroup.bundlemanagement.viewmodel.BundleName;
import de.alpharogroup.collections.set.SetExtensions;
import de.alpharogroup.spring.controller.AbstractRestController;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(ApplicationConfiguration.REST_VERSION + BundleApplicationsController.REST_PATH)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BundleApplicationsController
	extends AbstractRestController<BundleApplications, UUID, BundleApplicationsRepository, BundleApplication>
{

	public static final String REST_PATH = "/bundle/applications";
	public static final String REST_PATH_FIND = "/find";
	public static final String REST_PATH_FIND_ALL_BUNDLENAMES = "/find/all/bundlenames";
	public static final String REST_PATH_BY_BUNDLENAME = "/find/by/bundlename";
	public static final String REST_PATH_DELETE = "/delete";

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

	@RequestMapping(value = BundleApplicationsController.REST_PATH_DELETE, method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete the given bundle application")
	public void delete(@Valid @RequestBody BundleApplication bundleApplication)
	{
		final BundleApplications bundleApplications = this.service
			.find(bundleApplication.getName());
		this.service.delete(bundleApplications);
	}

	@CrossOrigin(origins = "*")
	@GetMapping(path = BundleApplicationsController.REST_PATH_FIND_ALL_BUNDLENAMES, produces = MediaType.APPLICATION_JSON_VALUE)
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
	@RequestMapping(path = BundleApplicationsController.REST_PATH_BY_BUNDLENAME, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find the BundleApplication object from the given BundleName object")
	public ResponseEntity<BundleApplication> findByBundleName(@Valid @RequestBody BundleName bundlename)
	{
		final BundleApplications bundleApplication = this.service
			.find(bundlename.getOwner().getName());
		return ResponseEntity
			.status(
				bundleApplication != null ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value())
			.body(mapper.toDto(bundleApplication));
	}

	@CrossOrigin(origins = "*")
	@GetMapping(path = BundleApplicationsController.REST_PATH_FIND, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find the Resourcebundle from the given arguments.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bundleappname", value = "the name of the bundle application", dataType = "string", paramType = "query") })
	public ResponseEntity<BundleApplication> getBundleApp(
		@RequestParam("bundleappname") String bundleappname)
	{
		final BundleApplications bundleApplication = this.service.find(bundleappname);
		return ResponseEntity
			.status(
				bundleApplication != null ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value())
			.body(bundleApplication != null ?mapper.toDto(bundleApplication): null);
	}
}
