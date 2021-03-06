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
package de.alpharogroup.bundlemanagement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.alpharogroup.bundlemanagement.configuration.ApplicationConfiguration;
import de.alpharogroup.bundlemanagement.viewmodel.BundleName;
import de.alpharogroup.bundlemanagement.viewmodel.ImprortableBundleName;
import de.alpharogroup.bundlemanagement.viewmodel.Resourcebundle;
import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.resourcebundle.locale.LocaleExtensions;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;
import de.alpharogroup.spring.generics.ParameterizedTypeReferenceFactory;
import de.alpharogroup.spring.web.util.UrlExtensions;
import de.alpharogroup.xml.json.ObjectToJsonExtensions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResourcebundlesControllerTest
{

	@Autowired private TestRestTemplate restTemplate;

	@LocalServerPort int randomServerPort;

	public String getBaseUrl(int serverPort)
	{
		return UrlExtensions.getBaseUrl("http", "localhost", serverPort,
			ApplicationConfiguration.REST_VERSION,
			ResourcebundlesController.REST_PATH);
	}

	@Before
	public void prepare()
	{
	}

	@Test
	public void testFind()
	{
		Resourcebundle actual;
		Resourcebundle expected;
		String[] requestParams = {"basename", "bundleappname", "key", "locale"};
		String restUrl = UrlExtensions.generateUrl(getBaseUrl(randomServerPort),
			ResourcebundlesController.REST_PATH_FIND, requestParams);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		Map<String, String> map = new HashMap<String, String>();
		map.put("bundleappname", "test-bundle-application");
		map.put("basename", "test-resource-bundles");
		map.put("locale", "de");
		map.put("key", "resource.bundles.test.label");
		// http://localhost:5000/v1/resourcebundle/find?basename=test-resource-bundles&bundleappname=test-bundle-application&key=resource.bundles.test.label&locale=de
		ResponseEntity<Resourcebundle> entity = this.restTemplate.exchange(restUrl, HttpMethod.GET,
			requestEntity, Resourcebundle.class, map);
		assertNotNull(entity);
		actual = entity.getBody();
		assertEquals(actual.getValue().getName(), "Erstes label");
	}

	@Test
	public void testFindResourceBundles()
	{
		List<Resourcebundle> actual;
		String[] requestParams = {"basename", "bundleappname", "locale"};
		String restUrl = UrlExtensions.generateUrl(getBaseUrl(randomServerPort),
			ResourcebundlesController.REST_PATH_RESOURCES, requestParams);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		Map<String, String> map = new HashMap<String, String>();
		map.put("bundleappname", "test-bundle-application");
		map.put("basename", "test-resource-bundles");
		map.put("locale", "de");
		// http://localhost:5000/v1/resourcebundle/resourcebundles?basename=test-resource-bundles&bundleappname=test-bundle-application&locale=de
		ResponseEntity<List<Resourcebundle>> entity = this.restTemplate.exchange(restUrl,
			HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Resourcebundle>>()
			{
			}, map);
		assertNotNull(entity);
		actual = entity.getBody();
		assertEquals(actual.size(), 1);
		Resourcebundle first = ListExtensions.getFirst(actual);
		assertEquals(first.getValue().getName(), "Erstes label");
	}

	@Test
	public void testGetProperties()
	{
		String[] requestParams = {"basename", "bundleappname", "locale"};
		String restUrl = UrlExtensions.generateUrl(getBaseUrl(randomServerPort),
			ResourcebundlesController.REST_PATH_VALUE, requestParams);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		Map<String, String> map = new HashMap<String, String>();
		map.put("bundleappname", "test-bundle-application");
		map.put("basename", "test-resource-bundles");
		map.put("locale", "de");
		// http://localhost:5000/v1/resourcebundle/properties?basename=test-resource-bundles&bundleappname=test-bundle-application&locale=de
		ResponseEntity<Properties> entity = this.restTemplate.exchange(restUrl, HttpMethod.GET,
			requestEntity, Properties.class, map);
		assertNotNull(entity);
	}

	@Test
	public void testGetString()
	{

		String[] requestParams = {"basename", "bundleappname", "key", "locale"};
		String restUrl = UrlExtensions.generateUrl(getBaseUrl(randomServerPort),
			ResourcebundlesController.REST_PATH_VALUE, requestParams);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		Map<String, String> map = new HashMap<String, String>();
		map.put("bundleappname", "test-bundle-application");
		map.put("basename", "test-resource-bundles");
		map.put("locale", "de");
		map.put("key", "resource.bundles.test.label");
		// http://localhost:5000/v1/resourcebundle/value?basename=test-resource-bundles&bundleappname=test-bundle-application&key=resource.bundles.test.label&locale=de
		ResponseEntity<String> entity = this.restTemplate.exchange(restUrl, HttpMethod.GET,
			requestEntity, String.class, map);
		assertNotNull(entity);
		String entityBody = entity.getBody();
		assertEquals(entityBody, "Erstes label");

	}

	@Test
	public void testGetStringWithParameters()
	{
		final String[] paramsGerman = { "Fritz", "Berlin" };
		String[] requestParams = {"basename", "bundleappname", "key", "locale"};
		String restUrl = UrlExtensions.generateUrl(getBaseUrl(randomServerPort),
			ResourcebundlesController.REST_PATH_VALUE_WITH_PARAMS, requestParams, "params",
			paramsGerman);

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		Map<String, String> map = new HashMap<String, String>();
		map.put("bundleappname", "test-bundle-application");
		map.put("basename", "test");
		map.put("locale", "de_DE");
		map.put("key", "com.example.gui.prop.with.params.label");
		// http://localhost:5000/v1/resourcebundle/value/withparams?basename=test&bundleappname=test-bundle-application&key=com.example.gui.prop.with.params.label&locale=de_DE&params=Fritz&params=Berlin
		ResponseEntity<String> entity = this.restTemplate.exchange(restUrl, HttpMethod.GET,
			requestEntity, String.class, map);
		assertNotNull(entity);
	}


	@Test
	public void testUpdateProperties() throws JsonProcessingException
	{
		String restUrl = UrlExtensions.generateUrl(getBaseUrl(randomServerPort),
			ResourcebundlesController.REST_PATH_UPDATE_BUNDLENAME);

		List<Resourcebundle> actual;
		Properties properties;
		String ownerName;
		String baseName;
		Locale locale;
		String filepath;

		properties = new Properties();
		properties.setProperty("utest.one", "one val");
		properties.setProperty("utest.two", "two val");
		properties.setProperty("utest.three", "three val");
		ownerName = "test-bundle-application";
		baseName = "test";
		filepath = "/home/fooman/dev/barproject/src/main/resources";
		locale = LocaleResolver.resolveLocale("de_DE");
		String localeFilenameSuffix = LocaleExtensions.getLocaleFilenameSuffix(locale);

		ImprortableBundleName imprortableBundleName = ImprortableBundleName.builder()
			.properties(properties).bundleappname(ownerName).baseName(baseName).filepath(filepath)
			.locale(locale).build();

		Map<String, String> map = new HashMap<String, String>();
		map.put("bundleappname", ownerName);
		map.put("basename", baseName);
		map.put("filepath", filepath);
		map.put("locale", localeFilenameSuffix);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String json = ObjectToJsonExtensions.toJson(imprortableBundleName);
		HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);

		ResponseEntity<BundleName> entity = this.restTemplate.postForEntity(restUrl, requestEntity,
			BundleName.class);
		// this.restTemplate
		// .exchange(restUrl, HttpMethod.POST, requestEntity, BundleName.class);
		assertNotNull(entity);

		String[] requestParams = {"basename", "bundleappname", "locale"};
		restUrl = UrlExtensions.generateUrl(getBaseUrl(randomServerPort),
			ResourcebundlesController.REST_PATH_RESOURCES, requestParams);

		headers = new HttpHeaders();
		requestEntity = new HttpEntity<>(headers);
		map = new HashMap<String, String>();
		map.put("bundleappname", ownerName);
		map.put("basename", baseName);
		map.put("locale", localeFilenameSuffix);
		// http://localhost:5000/v1/resourcebundle/resourcebundles?basename=test-resource-bundles&bundleappname=test-bundle-application&locale=de

		ResponseEntity<List<Resourcebundle>> newentity = this.restTemplate.exchange(restUrl,
			HttpMethod.GET, requestEntity, ParameterizedTypeReferenceFactory.newListParameterizedTypeReference(Resourcebundle.class), map);
		assertNotNull(newentity);

		actual = newentity.getBody();
		assertEquals(actual.size(), 7);
		Resourcebundle first = ListExtensions.getFirst(actual);
		assertEquals(first.getValue().getName(), "Speichern");

	}

}
