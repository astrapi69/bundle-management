package de.alpharogroup.bundlemanagement.controller;

import de.alpharogroup.bundlemanagement.configuration.ApplicationConfiguration;
import de.alpharogroup.bundlemanagement.jpa.entity.LanguageLocales;
import de.alpharogroup.bundlemanagement.jpa.repository.LanguageLocalesRepository;
import de.alpharogroup.bundlemanagement.mapper.LanguageLocalesMapper;
import de.alpharogroup.bundlemanagement.service.LanguageLocalesService;
import de.alpharogroup.bundlemanagement.viewmodel.LanguageLocale;
import de.alpharogroup.spring.controller.AbstractRestController;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(ApplicationConfiguration.REST_VERSION + LanguageLocalesController.REST_PATH)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LanguageLocalesController
	extends
		AbstractRestController<LanguageLocales, UUID, LanguageLocalesRepository, LanguageLocale>
{

	public static final String REST_PATH = "/locale";
	public static final String REST_PATH_FIND = "/find";

	LanguageLocalesMapper mapper;

	LanguageLocalesService service;

	public LanguageLocalesController(LanguageLocalesMapper mapper, LanguageLocalesService service)
	{
		super(mapper, service);
		this.mapper = mapper;
		this.service = service;
	}

	/**
	 * Call this link <a href="http://localhost:5000/v1/locale/find?locale=de_DE"></a> and adapt to
	 * your parameters.
	 */
	@CrossOrigin(origins = "*")
	@GetMapping(path = LanguageLocalesController.REST_PATH_FIND, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find the LanguageLocale object from the given locale name")
	public ResponseEntity<LanguageLocale> findByName(@RequestParam("locale") String locale)
	{
		LanguageLocales languageLocales = this.service.find(locale);
		return ResponseEntity.ok(mapper.toDto(languageLocales));
	}

}
