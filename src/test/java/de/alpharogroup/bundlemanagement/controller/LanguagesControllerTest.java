package de.alpharogroup.bundlemanagement.controller;

import de.alpharogroup.bundlemanagement.configuration.ApplicationConfiguration;
import de.alpharogroup.bundlemanagement.extensions.UrlExtensions;
import de.alpharogroup.bundlemanagement.viewmodel.Language;
import de.alpharogroup.bundlemanagement.viewmodel.LanguageLocale;
import de.alpharogroup.collections.array.ArrayFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LanguagesControllerTest
{

	@Autowired private TestRestTemplate restTemplate;

	@LocalServerPort int randomServerPort;

	public String getBaseUrl(int serverPort)
	{
		return UrlExtensions.getBaseUrl("http", "localhost", serverPort,
			ApplicationConfiguration.REST_VERSION,
			LanguagesController.REST_PATH);
	}

	@Before
	public void prepare()
	{
	}

	@Test
	public void testFind()
	{
		String[] requestParams = ArrayFactory.newArray("name");
		String restUrl = UrlExtensions.generateUrl(getBaseUrl(randomServerPort),
			LanguagesController.REST_PATH_FIND, requestParams);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "Armenian");

		ResponseEntity<Language> entity = this.restTemplate.exchange(restUrl, HttpMethod.GET,
			requestEntity, Language.class, map);
		assertNotNull(entity);
	}

	@Test
	public void testFindByCode()
	{
		String[] requestParams = ArrayFactory.newArray("code");
		String restUrl = UrlExtensions.generateUrl(getBaseUrl(randomServerPort),
			LanguagesController.REST_PATH_FIND_BY_CODE, requestParams);

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", "el");

		ResponseEntity<Language> entity = this.restTemplate.exchange(restUrl, HttpMethod.GET,
			requestEntity, Language.class, map);
		assertNotNull(entity);
	}

}
