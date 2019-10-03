package de.alpharogroup.bundlemanagement.controller;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import de.alpharogroup.bundlemanagement.viewmodel.Country;
import de.alpharogroup.bundlemanagement.viewmodel.LanguageLocale;
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

import de.alpharogroup.bundlemanagement.configuration.ApplicationConfiguration;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LanguageLocalesControllerTest
{

	@Autowired private TestRestTemplate restTemplate;

	@LocalServerPort int randomServerPort;

	public String getBaseUrl(int serverPort)
	{
		return "http://localhost:" + serverPort + ApplicationConfiguration.REST_VERSION
			+ LanguageLocalesController.REST_PATH;
	}

	@Before
	public void prepare()
	{
	}

	@Test
	public void testFind()
	{
		String restUrl = getBaseUrl(randomServerPort) + LanguageLocalesController.REST_PATH_FIND
			+ "?locale={locale}";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		Map<String, String> map = new HashMap<String, String>();
		map.put("locale", "de_DE");
		ResponseEntity<LanguageLocale> entity = this.restTemplate.exchange(restUrl, HttpMethod.GET,
			requestEntity, LanguageLocale.class, map);
		assertNotNull(entity);
	}

}
