package de.alpharogroup.bundlemanagement.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import de.alpharogroup.bundlemanagement.viewmodel.*;
import de.alpharogroup.collections.set.SetFactory;
import de.alpharogroup.spring.generics.ParameterizedTypeReferenceFactory;
import de.alpharogroup.spring.web.util.UrlExtensions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import de.alpharogroup.bundlemanagement.configuration.ApplicationConfiguration;
import de.alpharogroup.resourcebundle.locale.LocaleExtensions;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;
import de.alpharogroup.xml.json.ObjectToJsonExtensions;

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
		expected = Resourcebundle.builder()
			.id(1)
			.version(1)
			.key(PropertiesKey.builder().id(1).version(1).name("resource.bundles.test.label").build())
			.value(PropertiesValue.builder().id(1).version(1).name("Erstes label").build())
			.bundleName(BundleName.builder()
				.id(1)
				.version(1)
				.filepath("/src/test/resources/messages")
				.baseName(BaseName.builder().id(1).version(1).name("test-resource-bundles").build())
				.locale(LanguageLocale.builder().id(29).version(1).locale("de").build())
				.owner(BundleApplication.builder().id(1).version(1)
					.name("test-bundle-application")
					.defaultLocale(LanguageLocale.builder().id(38).version(1).locale("en").build())
					.supportedLocales(SetFactory.newHashSet(LanguageLocale.builder().id(29).version(1).locale("de").build(),
						LanguageLocale.builder().id(38).version(1).locale("en").build(),
						LanguageLocale.builder().id(41).version(1).locale("en_GB").build(),
						LanguageLocale.builder().id(32).version(1).locale("de_DE").build(),
						LanguageLocale.builder().id(48).version(1).locale("en_US").build()))
					.build())
				.build())
			.build();
		assertEquals(actual, expected);
	}

	@Test
	public void testFindResourceBundles()
	{
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

	}

}
