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

import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.UUID;

import javax.validation.Valid;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.astrapi69.bundlemanagement.enums.ActionRestPath;
import io.github.astrapi69.bundlemanagement.enums.AppRestPath;
import io.github.astrapi69.bundlemanagement.jpa.entity.BundleApplications;
import io.github.astrapi69.bundlemanagement.jpa.entity.BundleNames;
import io.github.astrapi69.bundlemanagement.jpa.entity.Resourcebundles;
import io.github.astrapi69.bundlemanagement.jpa.repository.ResourcebundlesRepository;
import io.github.astrapi69.bundlemanagement.mapper.ResourcebundlesMapper;
import io.github.astrapi69.bundlemanagement.service.ResourcebundlesService;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleName;
import io.github.astrapi69.bundlemanagement.viewmodel.ImprortableBundleName;
import io.github.astrapi69.bundlemanagement.viewmodel.Resourcebundle;
import io.github.astrapi69.collections.list.ListExtensions;
import io.github.astrapi69.resourcebundle.locale.LocaleResolver;
import io.github.astrapi69.resourcebundle.locale.ResourceBundleExtensions;
import io.github.astrapi69.spring.controller.AbstractRestController;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(AppRestPath.REST_VERSION + AppRestPath.REST_RESOURCE_BUNDLES)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ResourcebundlesController
	extends
		AbstractRestController<Resourcebundles, UUID, ResourcebundlesRepository, Resourcebundle>
{

	ResourcebundlesMapper mapper;

	ResourcebundlesService service;

	public ResourcebundlesController(ResourcebundlesMapper mapper, ResourcebundlesService service)
	{
		super(mapper, service);
		this.mapper = mapper;
		this.service = service;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(path = ActionRestPath.ACTION_PERSIST, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Persist the given resourcebundle object")
	@ResponseBody
	public ResponseEntity<Resourcebundle> persist(@Valid @RequestBody Resourcebundle resourcebundle)
	{
		Resourcebundles savedEntity = this.service.saveOrUpdate(resourcebundle);
		return ResponseEntity.ok(mapper.toDto(savedEntity));
	}

	@CrossOrigin(origins = "*")
	@GetMapping(path = ActionRestPath.ACTION_FIND, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find the resourcebundle from the given arguments.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bundleappname", value = "the name of the bundle application", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "basename", value = "the base name", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "locale", value = "the locale", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "key", value = "the key", dataType = "string", paramType = "query") })
	public ResponseEntity<Resourcebundle> find(@RequestParam("bundleappname") String bundleappname,
		@RequestParam("basename") String basename, @RequestParam("locale") String locale,
		@RequestParam("key") String key)
	{
		BundleApplications bundleApplications = this.service.find(bundleappname);
		List<Resourcebundles> resourcebundles = this.service.find(bundleApplications, basename,
			locale, key);
		Resourcebundles first = ListExtensions.getFirst(resourcebundles);
		return ResponseEntity
			.status(first != null ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value())
			.body(mapper.toDto(first));
	}

	@CrossOrigin(origins = "*")
	@GetMapping(path = ActionRestPath.ACTION_RESOURCE_BUNDLES, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find the resourcebundles from the given arguments.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bundleappname", value = "the name of the bundle application", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "basename", value = "the base name", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "locale", value = "the locale", dataType = "string", paramType = "query") })
	ResponseEntity<List<Resourcebundle>> findResourceBundles(
		@RequestParam("bundleappname") String bundleappname,
		@RequestParam("basename") String basename, @RequestParam("locale") String locale)
	{
		BundleApplications bundleApplications = this.service.find(bundleappname);
		final Locale loc = LocaleResolver.resolveLocale(locale);
		List<Resourcebundles> resourcebundles = this.service.findResourceBundles(bundleApplications,
			basename, loc);
		return ResponseEntity.ok(mapper.toDtos(resourcebundles));
	}

	@CrossOrigin(origins = "*")
	@GetMapping(path = ActionRestPath.ACTION_PROPERTIES, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find the properties from the given arguments.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bundleappname", value = "the name of the bundle application", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "basename", value = "the base name", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "locale", value = "the locale", dataType = "string", paramType = "query") })
	public ResponseEntity<Properties> getProperties(
		@RequestParam("bundleappname") String bundleappname,
		@RequestParam("basename") String baseName, @RequestParam("locale") String locale)
	{
		BundleApplications bundleApplications = this.service.find(bundleappname);
		Properties properties = this.service.getProperties(bundleApplications, baseName, locale);
		return ResponseEntity.ok(properties);
	}

	@CrossOrigin(origins = "*")
	@GetMapping(path = ActionRestPath.ACTION_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find the value from the given arguments.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bundleappname", value = "the name of the bundle application", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "basename", value = "the base name", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "locale", value = "the locale", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "key", value = "the key", dataType = "string", paramType = "query") })
	public ResponseEntity<String> getString(@RequestParam("bundleappname") String bundleappname,
		@RequestParam("basename") String basename, @RequestParam("locale") String locale,
		@RequestParam("key") String key)
	{
		BundleApplications bundleApplications = this.service.find(bundleappname);
		Resourcebundles first = this.service.getResourcebundle(bundleApplications, basename,
			LocaleResolver.resolveLocale(locale), key);
		return ResponseEntity
			.status(first != null ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value())
			.body(first.getValue().getName());
	}

	@CrossOrigin(origins = "*")
	@GetMapping(path = ActionRestPath.ACTION_VALUE_WITH_PARAMETERS, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find the value from the given arguments.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bundleappname", value = "the name of the bundle application", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "basename", value = "the base name", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "locale", value = "the locale", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "key", value = "the key", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "params", value = "the parameters for the resourcebundle", dataType = "string", paramType = "query") })
	public ResponseEntity<String> getString(@RequestParam("bundleappname") String bundleappname,
		@RequestParam("basename") String basename, @RequestParam("locale") String locale,
		@RequestParam("key") String key, @RequestParam("params") final Object[] params)
	{
		final BundleApplications owner = this.service.find(bundleappname);
		final Resourcebundles resourcebundles = this.service.getResourcebundle(owner, basename,
			LocaleResolver.resolveLocaleCode(locale), key);
		return ResponseEntity
			.status(
				resourcebundles != null ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value())
			.body(ResourceBundleExtensions.format(resourcebundles.getValue().getName(), params));
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(path = ActionRestPath.ACTION_UPDATE_BUNDLENAME, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "updates the given bundlename")
	public ResponseEntity<BundleName> updateProperties(
		@Valid @RequestBody ImprortableBundleName imprortableBundleName)
	{
		final BundleApplications bundleApplication = this.service
			.find(imprortableBundleName.getBundleappname());
		BundleNames bundleName = this.service.updateProperties(bundleApplication,
			imprortableBundleName.getProperties(), imprortableBundleName.getBaseName(),
			imprortableBundleName.getFilepath(), imprortableBundleName.getLocale(), true);
		return ResponseEntity.ok(mapper.map(bundleName, BundleName.class));
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(path = ActionRestPath.ACTION_SAVE_OR_UPDATE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "save the value from the given arguments.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bundleappname", value = "the name of the bundle application", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "basename", value = "the base name", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "locale", value = "the locale", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "key", value = "the key", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "value", value = "the value of the resourcebundle", dataType = "string", paramType = "query") })
	public ResponseEntity<Resourcebundle> saveOrUpdate(
		@RequestParam("bundleappname") String bundleappname,
		@RequestParam("basename") String basename, @RequestParam("locale") String locale,
		@RequestParam("key") String key, @RequestParam("value") final String value)
	{
		final BundleApplications owner = this.service.find(bundleappname);
		final Resourcebundles resourcebundles = this.service.contains(owner, basename,
			LocaleResolver.resolveLocaleCode(locale), key);
		boolean update = resourcebundles != null;
		Resourcebundles saveOrUpdatedEntry = this.service.saveOrUpdateEntry(owner, basename,
			LocaleResolver.resolveLocaleCode(locale), key, value, update);
		return ResponseEntity
			.status(
				saveOrUpdatedEntry != null ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value())
			.body(mapper.toDto(saveOrUpdatedEntry));
	}

}
