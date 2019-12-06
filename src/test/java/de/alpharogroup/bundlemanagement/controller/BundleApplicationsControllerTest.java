package de.alpharogroup.bundlemanagement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.alpharogroup.bundlemanagement.configuration.ApplicationConfiguration;
import de.alpharogroup.bundlemanagement.viewmodel.BundleApplication;
import de.alpharogroup.bundlemanagement.viewmodel.BundleName;
import de.alpharogroup.collections.array.ArrayFactory;
import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.spring.generics.ParameterizedTypeReferenceFactory;
import de.alpharogroup.spring.web.util.UrlExtensions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BundleApplicationsControllerTest
{

	@Autowired private TestRestTemplate restTemplate;

	@LocalServerPort int randomServerPort;
	RestTemplate decoratedRestTemplate;

	public String getBaseUrl(int serverPort)
	{
		return UrlExtensions.getBaseUrl("http", "localhost", serverPort,
			ApplicationConfiguration.REST_VERSION, BundleApplicationsController.REST_PATH);
	}

	@Before
	public void prepare()
	{
		decoratedRestTemplate = this.restTemplate.getRestTemplate();
		List<HttpMessageConverter<?>> converters =
			decoratedRestTemplate.getMessageConverters();
		for (HttpMessageConverter converter : converters) {
			System.out.println(converter.toString());
		}
	}


	@Test
	public void testFindListAll(){
		String restUrl;
		restUrl = UrlExtensions.generateUrl(getBaseUrl(randomServerPort),
			"");
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<Iterable<BundleApplication>> entities = this.restTemplate.exchange(restUrl, HttpMethod.GET,
			requestEntity, ParameterizedTypeReferenceFactory
				.newIterableParameterizedTypeReference(BundleApplication.class));
		assertNotNull(entities);
	}

	@Test
	public void testPersist()
	{
		String restUrl;
		HttpHeaders headers;
		HttpEntity<String> requestEntity;

		restUrl = UrlExtensions.generateUrl(getBaseUrl(randomServerPort), "");
		List<MediaType> acceptableMediaTypes = ListFactory.newArrayList();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		String json = "{\"id\":null,\"version\":null,\"name\":\"test-foo-del-app\",\"defaultLocale\":{\"id\":\"4bc772e6-e7b8-43af-89e3-99a66962bfca\",\"version\":1,\"locale\":\"el\"},\"supportedLocales\":[{\"id\":\"4bc772e6-e7b8-43af-89e3-99a66962bfca\",\"version\":1,\"locale\":\"el\"}]}";
		headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);
		headers.setContentType(MediaType.APPLICATION_JSON);
		requestEntity = new HttpEntity<>(json, headers);
		ResponseEntity<BundleApplication> entity = this.restTemplate.postForEntity(restUrl, requestEntity,
			BundleApplication.class);
		assertNotNull(entity);
		BundleApplication bundleApplication = entity.getBody();
		assertNotNull(bundleApplication);
	}

	@Test
	public void testFind()
	{
		String restUrl;
		String[] requestParams;
		HttpHeaders headers;
		HttpEntity<String> requestEntity;
		Map<String, String> urlParams;
		ResponseEntity<BundleApplication> entity;

		requestParams = ArrayFactory.newArray("bundleappname");
		restUrl = UrlExtensions.generateUrl(getBaseUrl(randomServerPort),
			BundleApplicationsController.REST_PATH_FIND, requestParams);
		headers = new HttpHeaders();
		requestEntity = new HttpEntity<>(headers);
		urlParams = new HashMap<String, String>();
		urlParams.put("bundleappname", "test-bundle-application");
		entity = this.restTemplate.exchange(restUrl, HttpMethod.GET,
			requestEntity, BundleApplication.class, urlParams);
		assertNotNull(entity);
		// bundle app not exists case...
		requestParams = ArrayFactory.newArray("bundleappname");
		restUrl = UrlExtensions.generateUrl(getBaseUrl(randomServerPort),
			BundleApplicationsController.REST_PATH_FIND, requestParams);
		headers = new HttpHeaders();
		requestEntity = new HttpEntity<>(headers);
		urlParams = new HashMap<String, String>();
		urlParams.put("bundleappname", "not-existent-app");
		entity = this.restTemplate.exchange(restUrl, HttpMethod.GET,
			requestEntity, BundleApplication.class, urlParams);
		assertNotNull(entity);
		assertNull(entity.getBody());
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

		String json = "{\"id\":\"8024acb3-362b-4cbd-a3b9-1cd4cfd88974\",\"version\":1,\"baseName\":{\"id\":\"711af8a1-466a-4393-9b4a-e83bff605330\",\"version\":1,\"name\":\"test\"},\"filepath\":\"/src/test/resources/errors\",\"locale\":{\"id\":\"286dbfda-9035-4eda-a6b7-76b0dbe7697b\",\"version\":1,\"locale\":\"en_US\"},\"owner\":{\"id\":\"0084d910-d153-4bd4-86bf-f5e5a8492c7e\",\"version\":1,\"name\":\"test-bundle-application\",\"defaultLocale\":{\"id\":\"f910d316-add9-463f-8bc7-e4281c5c44f1\",\"version\":1,\"locale\":\"en\"},\"supportedLocales\":[{\"id\":\"6afa59eb-1c2b-4767-9ce8-166b405369a4\",\"version\":1,\"locale\":\"de\"},{\"id\":\"f910d316-add9-463f-8bc7-e4281c5c44f1\",\"version\":1,\"locale\":\"en\"},{\"id\":\"c513ed08-e65f-4352-b789-a8bbc5d2f1db\",\"version\":1,\"locale\":\"en_GB\"},{\"id\":\"0f178aab-52f9-4459-83a9-c3078ca9d4d9\",\"version\":1,\"locale\":\"de_DE\"},{\"id\":\"286dbfda-9035-4eda-a6b7-76b0dbe7697b\",\"version\":1,\"locale\":\"en_US\"}]}}";
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
