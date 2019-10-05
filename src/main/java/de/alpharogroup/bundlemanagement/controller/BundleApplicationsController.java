package de.alpharogroup.bundlemanagement.controller;

import java.util.List;
import java.util.Set;

import de.alpharogroup.bundlemanagement.viewmodel.BundleApplication;
import de.alpharogroup.bundlemanagement.viewmodel.BundleName;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.alpharogroup.bundlemanagement.configuration.ApplicationConfiguration;
import de.alpharogroup.bundlemanagement.jpa.entity.BundleApplications;
import de.alpharogroup.bundlemanagement.jpa.entity.BundleNames;
import de.alpharogroup.bundlemanagement.jpa.repository.BundleApplicationsRepository;
import de.alpharogroup.bundlemanagement.mapper.BundleApplicationMapper;
import de.alpharogroup.bundlemanagement.mapper.BundleNameMapper;
import de.alpharogroup.bundlemanagement.service.BundleApplicationsService;
import de.alpharogroup.collections.set.SetExtensions;
import de.alpharogroup.spring.controller.AbstractRestController;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.validation.Valid;

@RestController
@RequestMapping(ApplicationConfiguration.REST_VERSION + BundleApplicationsController.REST_PATH)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BundleApplicationsController
	extends
		AbstractRestController<BundleApplications, Integer, BundleApplicationsRepository, BundleApplication>
{

	public static final String REST_PATH = "/bundle/applications";
	public static final String REST_PATH_FIND = "/find";
	public static final String REST_PATH_FIND_ALL_BUNDLENAMES = "/find/all/bundlenames";
	public static final String REST_PATH_BY_BUNDLENAME = "/find/by/bundlename";
	public static final String REST_PATH_PERSIST = "/persist";

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
	@CrossOrigin(origins = "*")
	@RequestMapping(path = BundleApplicationsController.REST_PATH_PERSIST, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Persist the given BundleApplication object")
	public ResponseEntity<BundleApplication> persist(@Valid @RequestBody BundleApplication bundleApplication){
		BundleApplications bundleApplications = mapper.toEntity(bundleApplication);
		BundleApplications savedEntity = this.service.save(bundleApplications);
		ResponseEntity<BundleApplication> saved = super.save(bundleApplication);
		return ResponseEntity.ok(mapper.toDto(savedEntity));
	}

	/**
	 * Call this link <a href=
	 * "http://localhost:5000/v1/bundle/applications/find/all/bundlenames?bundleappname=test-bundle-application"></a>
	 * and adapt to your parameters.
	 */
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
		BundleNameMapper bundleNameMapper = Mappers.getMapper(BundleNameMapper.class);
		List<BundleName> bundleNameList = bundleNameMapper.toDtos(bundleNames);
		return ResponseEntity.ok(SetExtensions.toSet(bundleNameList));
	}

	/**
	 * Call this link <a href="http://localhost:5000/v1/bundle/applications/find/by/bundlename"></a>
	 * and adapt to your parameters.
	 */
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

	/**
	 * Call this link <a href=
	 * "http://localhost:5000/v1/bundle/applications/find?bundleappname=test-bundle-application"></a>
	 * and adapt to your parameters.
	 */
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
			.body(mapper.toDto(bundleApplication));
	}
}
