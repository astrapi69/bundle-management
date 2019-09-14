package de.alpharogroup.bundlemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping(ApplicationConfiguration.REST_VERSION + "/resourcebundle")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ResourcebundlesController extends AbstractRestController<Resourcebundles, Integer, ResourcebundlesRepository, Resourcebundle>
{

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
	@ApiOperation(value = "Find the Resourcebundle from the given arguments.")
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

}
