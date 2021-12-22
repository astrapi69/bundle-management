/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.bundlemanagement.controller;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.github.astrapi69.bundlemanagement.configuration.ApplicationConfiguration;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleApplication;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleName;
import io.github.astrapi69.collections.array.ArrayFactory;
import io.github.astrapi69.collections.list.ListFactory;
import io.github.astrapi69.spring.generics.ParameterizedTypeReferenceFactory;
import io.github.astrapi69.spring.web.util.UrlExtensions;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BundleApplicationsControllerTest
{

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	int randomServerPort;
	RestTemplate decoratedRestTemplate;

	public String getBaseUrl(int serverPort)
	{
		return UrlExtensions.getBaseUrl("http", "localhost", serverPort,
			ApplicationConfiguration.REST_VERSION, BundleApplicationsController.REST_PATH);
	}

	@BeforeEach
	public void prepare()
	{
		decoratedRestTemplate = this.restTemplate.getRestTemplate();
		List<HttpMessageConverter<?>> converters = decoratedRestTemplate.getMessageConverters();
		for (HttpMessageConverter converter : converters)
		{
			System.out.println(converter.toString());
		}
	}


	@Test
	public void testFindListAll()
	{
		String restUrl;
		restUrl = UrlExtensions.generateUrl(getBaseUrl(randomServerPort), "");
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<Iterable<BundleApplication>> entities = this.restTemplate.exchange(restUrl,
			HttpMethod.GET, requestEntity, ParameterizedTypeReferenceFactory
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
		ResponseEntity<BundleApplication> entity = this.restTemplate.postForEntity(restUrl,
			requestEntity, BundleApplication.class);
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
		entity = this.restTemplate.exchange(restUrl, HttpMethod.GET, requestEntity,
			BundleApplication.class, urlParams);
		assertNotNull(entity);
		// bundle app not exists case...
		requestParams = ArrayFactory.newArray("bundleappname");
		restUrl = UrlExtensions.generateUrl(getBaseUrl(randomServerPort),
			BundleApplicationsController.REST_PATH_FIND, requestParams);
		headers = new HttpHeaders();
		requestEntity = new HttpEntity<>(headers);
		urlParams = new HashMap<String, String>();
		urlParams.put("bundleappname", "not-existent-app");
		entity = this.restTemplate.exchange(restUrl, HttpMethod.GET, requestEntity,
			BundleApplication.class, urlParams);
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
		ResponseEntity<Set<BundleName>> entity = this.restTemplate.exchange(restUrl, HttpMethod.GET,
			requestEntity,
			ParameterizedTypeReferenceFactory.newSetParameterizedTypeReference(BundleName.class),
			map);
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
		ResponseEntity<BundleApplication> entity = this.restTemplate.postForEntity(restUrl,
			requestEntity, BundleApplication.class);
		assertNotNull(entity);
	}

}