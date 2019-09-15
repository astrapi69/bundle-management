package de.alpharogroup.bundlemanagement.controller;

import de.alpharogroup.bundlemanagement.configuration.ApplicationConfiguration;
import de.alpharogroup.bundlemanagement.jpa.entity.BundleApplications;
import de.alpharogroup.bundlemanagement.jpa.entity.Resourcebundles;
import de.alpharogroup.bundlemanagement.jpa.repository.BundleApplicationsRepository;
import de.alpharogroup.bundlemanagement.jpa.repository.ResourcebundlesRepository;
import de.alpharogroup.bundlemanagement.mapper.BundleApplicationMapper;
import de.alpharogroup.bundlemanagement.mapper.ResourcebundleMapper;
import de.alpharogroup.bundlemanagement.service.BundleApplicationsService;
import de.alpharogroup.bundlemanagement.service.ResourcebundlesService;
import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;
import de.alpharogroup.spring.controller.AbstractRestController;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(ApplicationConfiguration.REST_VERSION + "/bundle/applications")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BundleApplicationsController extends AbstractRestController<BundleApplications, Integer, BundleApplicationsRepository, BundleApplication>
{

	BundleApplicationMapper mapper;

	BundleApplicationsService service;

	BundleApplicationsController(BundleApplicationMapper mapper, BundleApplicationsService service){
		super(mapper, service);
		this.mapper = mapper;
		this.service = service;
	}

	/**
	 * Call this link <a href="http://localhost:5000/v1/bundle/applications/find?bundleappname=base-bundle-application"></a>
	 * and adapt to your parameters.
	 */
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find the Resourcebundle from the given arguments.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "the name of the bundle application", dataType = "string", paramType = "query") })
	public ResponseEntity<BundleApplication> getBundleApp(@RequestParam("bundleappname") String name)
	{
		final BundleApplications bundleApplication = this.service.find(name);
		return ResponseEntity
			.status(bundleApplication != null ? HttpStatus.OK.value(): HttpStatus.BAD_REQUEST.value())
			.body(mapper.toDto(bundleApplication));
	}
}
