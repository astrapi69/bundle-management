package de.alpharogroup.bundlemanagement.controller;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

import de.alpharogroup.bundlemanagement.jpa.entity.BundleNames;
import de.alpharogroup.bundlemanagement.mapper.BundleNameMapper;
import de.alpharogroup.collections.pairs.Quattro;
import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.db.resource.bundles.domain.BundleName;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;
import de.alpharogroup.resourcebundle.locale.ResourceBundleExtensions;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.alpharogroup.bundlemanagement.configuration.ApplicationConfiguration;
import de.alpharogroup.bundlemanagement.jpa.entity.BundleApplications;
import de.alpharogroup.bundlemanagement.jpa.entity.Resourcebundles;
import de.alpharogroup.bundlemanagement.jpa.repository.ResourcebundlesRepository;
import de.alpharogroup.bundlemanagement.mapper.ResourcebundleMapper;
import de.alpharogroup.bundlemanagement.service.ResourcebundlesService;
import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.spring.controller.AbstractRestController;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping(ApplicationConfiguration.REST_VERSION + ResourcebundlesController.REST_PATH)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ResourcebundlesController extends AbstractRestController<Resourcebundles, Integer, ResourcebundlesRepository, Resourcebundle>
{

	public static final String REST_PATH = "/resourcebundle";

	ResourcebundleMapper mapper;

	ResourcebundlesService service;

	ResourcebundlesController(ResourcebundleMapper mapper, ResourcebundlesService service){
		super(mapper, service);
		this.mapper = mapper;
		this.service = service;
	}

	/**
	 * Call this link <a href="http://localhost:5000/v1/resourcebundle/find?basename=base-resource-bundles&bundleappname=base-bundle-application&key=resource.bundles.test.label&locale=de"></a>
	 * and adapt to your parameters.
	 */
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find the resourcebundle from the given arguments.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "bundleappname", value = "the name of the bundle application", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "basename", value = "the base name", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "locale", value = "the locale", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "key", value = "the key", dataType = "string", paramType = "query") })
	public ResponseEntity<Resourcebundle> find(@RequestParam("bundleappname") String bundleappname,
		@RequestParam("basename") String basename, @RequestParam("locale") String locale,
		@RequestParam("key") String key) {
		BundleApplications bundleApplications = this.service.find(bundleappname);
		List<Resourcebundles> resourcebundles = this.service
			.find(bundleApplications, basename, locale, key);
		Resourcebundles first = ListExtensions.getFirst(resourcebundles);
		return ResponseEntity
			.status(first != null ? HttpStatus.OK.value(): HttpStatus.BAD_REQUEST.value())
			.body(mapper.toDto(first));
	}

	/**
	 * Call this link <a href="http://localhost:5000/v1/resourcebundle/find/resourcebundles?basename=base-resource-bundles&bundleappname=base-bundle-application&locale=de"></a>
	 * and adapt to your parameters.
	 */
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/find/resourcebundles", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find the resourcebundles from the given arguments.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "bundleappname", value = "the name of the bundle application", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "basename", value = "the base name", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "locale", value = "the locale", dataType = "string", paramType = "query") })
	ResponseEntity<List<Resourcebundle>> findResourceBundles(@RequestParam("bundleappname") String bundleappname,
		@RequestParam("basename") String basename, @RequestParam("locale") String locale){
		BundleApplications bundleApplications = this.service.find(bundleappname);
		final Locale loc = LocaleResolver.resolveLocale(locale);
		List<Resourcebundles> resourcebundles = this.service
			.findResourceBundles(bundleApplications, basename, loc);
		return ResponseEntity
			.ok(mapper.toDtos(resourcebundles));
	}

	@CrossOrigin(origins = "*")
	@GetMapping(path = "/find/properties", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find the properties from the given arguments.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "bundleappname", value = "the name of the bundle application", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "basename", value = "the base name", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "locale", value = "the locale", dataType = "string", paramType = "query") })
	ResponseEntity<Properties> getProperties(@RequestParam("bundleappname") String bundleappname,
		@RequestParam("basename") String baseName, @RequestParam("locale") String locale){
		BundleApplications bundleApplications = this.service.find(bundleappname);
		Properties properties = this.service.getProperties(bundleApplications, baseName, locale);
		return ResponseEntity
			.ok(properties);
	}

	/**
	 * Call this link <a href="http://localhost:5000/v1/resourcebundle/find?basename=base-resource-bundles&bundleappname=base-bundle-application&key=resource.bundles.test.label&locale=de"></a>
	 * and adapt to your parameters.
	 */
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/value", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find the value from the given arguments.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "bundleappname", value = "the name of the bundle application", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "basename", value = "the base name", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "locale", value = "the locale", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "key", value = "the key", dataType = "string", paramType = "query") })
	public ResponseEntity<String> getString(@RequestParam("bundleappname") String bundleappname,
		@RequestParam("basename") String basename, @RequestParam("locale") String locale,
		@RequestParam("key") String key) {
		BundleApplications bundleApplications = this.service.find(bundleappname);
		List<Resourcebundles> resourcebundles = this.service
			.find(bundleApplications, basename, locale, key);
		Resourcebundles first = ListExtensions.getFirst(resourcebundles);
		return ResponseEntity
			.status(first != null ? HttpStatus.OK.value(): HttpStatus.BAD_REQUEST.value())
			.body(first.getValue().getName());
	}

	/**
	 * Call this link <a href="http://localhost:5000/v1/resourcebundle/value/withparams?basename=test&bundleappname=base-bundle-application&key=com.example.gui.prop.with.params.label&locale=de_DE&params=Fritz&params=Berlin"></a>
	 * and adapt to your parameters.
	 */
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/value/withparams", produces = MediaType.APPLICATION_JSON_VALUE)
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
		final Resourcebundles resourcebundles = this.service.getResourcebundle(owner,
			basename, LocaleResolver.resolveLocaleCode(locale), key);
		return ResponseEntity
			.status(resourcebundles != null ? HttpStatus.OK.value(): HttpStatus.BAD_REQUEST.value())
			.body(ResourceBundleExtensions.format(resourcebundles.getValue().getName(), params));
	}

	@CrossOrigin(origins = "*")
	@PutMapping(path = "/update/bundlename", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "updates the given bundlename")
	public ResponseEntity<BundleName> updateProperties(@RequestBody
		Quattro<Properties, String, String, Locale> quattro)
	{
		Properties properties = quattro.getTopLeft();
		String bundleappname = quattro.getTopRight();
		String baseName = quattro.getBottomLeft();
		Locale locale = quattro.getBottomRight();
		final BundleApplications bundleApplication = this.service.find(bundleappname);
		BundleNames bundleName = this.service.updateProperties(bundleApplication, properties,
			baseName, locale);
		BundleNameMapper bundleNameMapper = Mappers.getMapper(BundleNameMapper.class);
		return ResponseEntity.ok(bundleNameMapper.toDto(bundleName));
	}
}
