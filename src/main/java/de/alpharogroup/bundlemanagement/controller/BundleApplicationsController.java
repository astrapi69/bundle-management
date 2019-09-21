package de.alpharogroup.bundlemanagement.controller;

import java.util.List;
import java.util.Set;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.alpharogroup.bundlemanagement.configuration.ApplicationConfiguration;
import de.alpharogroup.bundlemanagement.jpa.entity.BundleApplications;
import de.alpharogroup.bundlemanagement.jpa.entity.BundleNames;
import de.alpharogroup.bundlemanagement.jpa.repository.BundleApplicationsRepository;
import de.alpharogroup.bundlemanagement.mapper.BundleApplicationMapper;
import de.alpharogroup.bundlemanagement.mapper.BundleNameMapper;
import de.alpharogroup.bundlemanagement.service.BundleApplicationsService;
import de.alpharogroup.collections.set.SetExtensions;
import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.db.resource.bundles.domain.BundleName;
import de.alpharogroup.spring.controller.AbstractRestController;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping(ApplicationConfiguration.REST_VERSION + BundleApplicationsController.REST_PATH)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BundleApplicationsController
	extends
		AbstractRestController<BundleApplications, Integer, BundleApplicationsRepository, BundleApplication>
{

	public static final String REST_PATH = "/bundle/applications";

	@Autowired
	BundleApplicationMapper mapper;
	@Autowired
	BundleApplicationsService service;

	public BundleApplicationsController(BundleApplicationMapper mapper,
		BundleApplicationsService service)
	{
		super(mapper, service);
		this.mapper = mapper;
		this.service = service;
	}

	/**
	 * Call this link <a href=
	 * "http://localhost:5000/v1/bundle/applications/find?bundleappname=base-bundle-application"></a>
	 * and adapt to your parameters.
	 */
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/find/all/bundlenames", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find all BundleName objects from the given name of the owner")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bundleappname", value = "the name of the bundle application", dataType = "string", paramType = "query") })
	public ResponseEntity<Set<BundleName>> findAllBundleNames(
		@RequestParam("bundleappname") String bundleappname)
	{
		final BundleApplications bundleApplication = this.service.find(bundleappname);
		Set<BundleNames> bundleNames = this.service.find(bundleApplication);
		BundleNameMapper bundleNameMapper = Mappers.getMapper(BundleNameMapper.class);
		List<BundleName> bundleNameList = bundleNameMapper.toDtos(bundleNames);
		return ResponseEntity.ok(SetExtensions.toSet(bundleNameList));
	}

	/**
	 * Call this link <a href="http://localhost:5000/v1/bundle/applications/find/by/bundlename"></a>
	 * and adapt to your parameters.
	 */
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/find/by/bundlename", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find the BundleApplication object from the given BundleName object")
	public ResponseEntity<BundleApplication> findByBundleName(@RequestBody BundleName bundlename)
	{
		final BundleApplications bundleApplication = this.service
			.find(bundlename.getOwner().getName());
		return ResponseEntity
			.status(
				bundleApplication != null ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value())
			.body(mapper.toDto(bundleApplication));
	}

	/**
	 * Call this link <a href=
	 * "http://localhost:5000/v1/bundle/applications/find?bundleappname=base-bundle-application"></a>
	 * and adapt to your parameters.
	 */
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
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
			.body(mapper.toDto(bundleApplication));
	}
}
