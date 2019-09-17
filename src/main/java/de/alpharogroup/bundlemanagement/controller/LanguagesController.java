package de.alpharogroup.bundlemanagement.controller;

import de.alpharogroup.bundlemanagement.configuration.ApplicationConfiguration;
import de.alpharogroup.bundlemanagement.jpa.entity.Countries;
import de.alpharogroup.bundlemanagement.jpa.entity.Languages;
import de.alpharogroup.bundlemanagement.jpa.repository.CountriesRepository;
import de.alpharogroup.bundlemanagement.jpa.repository.LanguagesRepository;
import de.alpharogroup.bundlemanagement.mapper.CountryMapper;
import de.alpharogroup.bundlemanagement.mapper.LanguageMapper;
import de.alpharogroup.bundlemanagement.service.CountriesService;
import de.alpharogroup.bundlemanagement.service.LanguagesService;
import de.alpharogroup.db.resource.bundles.domain.Country;
import de.alpharogroup.db.resource.bundles.domain.Language;
import de.alpharogroup.spring.controller.AbstractRestController;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApplicationConfiguration.REST_VERSION + LanguagesController.REST_PATH)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LanguagesController extends AbstractRestController<Languages, Integer, LanguagesRepository, Language>
{

	public static final String REST_PATH = "/language";

	LanguageMapper mapper;

	LanguagesService service;

	LanguagesController(LanguageMapper mapper, LanguagesService service){
		super(mapper, service);
		this.mapper = mapper;
		this.service = service;
	}

	/**
	 * Call this link <a href="http://localhost:5000/v1/language/find?name=German"></a>
	 * and adapt to your parameters.
	 */
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find the Language object from the given name")
	public ResponseEntity<Language> findByName(@RequestParam("name") String name)
	{
		Languages languages = this.service.findByName(name);
		return ResponseEntity
			.ok(mapper.toDto(languages));
	}

	/**
	 * Call this link <a href="http://localhost:5000/v1/language/find/by/code?code=German"></a>
	 * and adapt to your parameters.
	 */
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/find/by/code", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find the Language object from the given name")
	public ResponseEntity<Language> findByCode(@RequestParam("code") String code)
	{
		Languages languages = this.service.findByCode(code);
		return ResponseEntity
			.ok(mapper.toDto(languages));
	}
}
