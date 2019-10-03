package de.alpharogroup.bundlemanagement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.alpharogroup.bundlemanagement.configuration.ApplicationConfiguration;
import de.alpharogroup.bundlemanagement.extensions.ParameterizedTypeReferenceFactory;
import de.alpharogroup.bundlemanagement.extensions.UrlExtensions;
import de.alpharogroup.bundlemanagement.viewmodel.BundleApplication;
import de.alpharogroup.bundlemanagement.viewmodel.BundleName;
import de.alpharogroup.collections.array.ArrayFactory;
import de.alpharogroup.collections.set.SetExtensions;
import de.alpharogroup.xml.json.ObjectToJsonExtensions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BundleApplicationsControllerTest
{

	@Autowired private TestRestTemplate restTemplate;

	@LocalServerPort int randomServerPort;

	public String getBaseUrl(int serverPort)
	{
		return UrlExtensions.getBaseUrl("http", "localhost", serverPort, ApplicationConfiguration.REST_VERSION, BundleApplicationsController.REST_PATH);
	}

	@Before
	public void prepare()
	{
	}

	@Test
	public void testFind()
	{
		String restUrl;

		String[] requestParams = ArrayFactory.newArray("bundleappname");
		restUrl = UrlExtensions.generateUrl(getBaseUrl(randomServerPort),
			BundleApplicationsController.REST_PATH_FIND, requestParams);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		Map<String, String> map = new HashMap<String, String>();
		map.put("bundleappname", "test-bundle-application");
		ResponseEntity<BundleApplication> entity = this.restTemplate.exchange(restUrl, HttpMethod.GET,
			requestEntity, BundleApplication.class, map);
		assertNotNull(entity);
	}

	@Test
	public void testFindAllBundlenames()
	{
		String restUrl;

		String[] requestParams = ArrayFactory.newArray("bundleappname");
		restUrl = UrlExtensions.generateUrl(getBaseUrl(randomServerPort),
			BundleApplicationsController.REST_PATH_FIND_ALL_BUNDLENAMES, requestParams);

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		Map<String, String> map = new HashMap<String, String>();
		map.put("bundleappname", "test-bundle-application");
		ResponseEntity<Set<BundleName>> entity = this.restTemplate.exchange(restUrl,
			HttpMethod.GET, requestEntity, ParameterizedTypeReferenceFactory.newSetParameterizedTypeReference(BundleName.class), map);
		assertNotNull(entity);
		Set<BundleName> bundleNames = entity.getBody();
	}

	@Test
	public void testFindByBundlename() throws JsonProcessingException
	{
		String restUrl;
		HttpHeaders headers;
		HttpEntity<String> requestEntity;
		Map<String, String> map;

		String json = "{\"id\":4,\"version\":1,\"baseName\":{\"id\":2,\"version\":1,\"name\":\"test\"},\"filepath\":\"/src/test/resources/errors\",\"locale\":{\"id\":48,\"version\":1,\"locale\":\"en_US\"},\"owner\":{\"id\":1,\"version\":1,\"name\":\"test-bundle-application\",\"defaultLocale\":{\"id\":38,\"version\":1,\"locale\":\"en\"},\"supportedLocales\":[{\"id\":29,\"version\":1,\"locale\":\"de\"},{\"id\":38,\"version\":1,\"locale\":\"en\"},{\"id\":41,\"version\":1,\"locale\":\"en_GB\"},{\"id\":32,\"version\":1,\"locale\":\"de_DE\"},{\"id\":48,\"version\":1,\"locale\":\"en_US\"}]}}";
		restUrl = UrlExtensions.generateUrl(getBaseUrl(randomServerPort),
			BundleApplicationsController.REST_PATH_BY_BUNDLENAME);
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		requestEntity = new HttpEntity<>(json, headers);
		ResponseEntity<BundleApplication> entity = this.restTemplate.postForEntity(restUrl, requestEntity,
			BundleApplication.class);
		assertNotNull(entity);
	}

}
