package de.alpharogroup.bundlemanagement.controller;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import de.alpharogroup.bundlemanagement.configuration.ApplicationConfiguration;
import de.alpharogroup.bundlemanagement.viewmodel.ImprortableBundleName;
import de.alpharogroup.db.resource.bundles.domain.BundleName;
import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.resourcebundle.locale.LocaleExtensions;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;
import de.alpharogroup.xml.json.ObjectToJsonExtensions;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ResourcebundlesControllerTest
{

	RestTemplate restTemplate = new RestTemplate();

	int serverPort;

	public String getBaseUrl(int serverPort)
	{
		return "http://localhost:" + serverPort + ApplicationConfiguration.REST_VERSION
			+ ResourcebundlesController.REST_PATH;
	}

	@Before
	public void prepare()
	{
		serverPort = 5000;
		restTemplate = new RestTemplate();
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(
			Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
		restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
	}

	@Test
	public void testFind()
	{
		String restUrl = getBaseUrl(serverPort) + ResourcebundlesController.REST_PATH_FIND
			+ "?basename={basename}&bundleappname={bundleappname}&key={key}&locale={locale}";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		Map<String, String> map = new HashMap<String, String>();
		map.put("bundleappname", "base-bundle-application");
		map.put("basename", "base-resource-bundles");
		map.put("locale", "de");
		map.put("key", "resource.bundles.test.label");
		// http://localhost:5000/v1/resourcebundle/find?basename=base-resource-bundles&bundleappname=base-bundle-application&key=resource.bundles.test.label&locale=de
		ResponseEntity<Resourcebundle> entity = this.restTemplate.exchange(restUrl, HttpMethod.GET,
			requestEntity, Resourcebundle.class, map);
		assertNotNull(entity);
	}

	@Test
	public void testFindResourceBundles()
	{
		String restUrl = getBaseUrl(serverPort) + ResourcebundlesController.REST_PATH_RESOURCES
			+ "?basename={basename}&bundleappname={bundleappname}&locale={locale}";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		Map<String, String> map = new HashMap<String, String>();
		map.put("bundleappname", "base-bundle-application");
		map.put("basename", "base-resource-bundles");
		map.put("locale", "de");
		// http://localhost:5000/v1/resourcebundle/resourcebundles?basename=base-resource-bundles&bundleappname=base-bundle-application&locale=de
		ResponseEntity<List<Resourcebundle>> entity = this.restTemplate.exchange(restUrl,
			HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Resourcebundle>>()
			{
			}, map);
		assertNotNull(entity);
	}

	@Test
	public void testGetProperties()
	{
		String restUrl = getBaseUrl(serverPort) + ResourcebundlesController.REST_PATH_VALUE
			+ "?basename={basename}&bundleappname={bundleappname}&locale={locale}";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		Map<String, String> map = new HashMap<String, String>();
		map.put("bundleappname", "base-bundle-application");
		map.put("basename", "base-resource-bundles");
		map.put("locale", "de");
		// http://localhost:5000/v1/resourcebundle/properties?basename=base-resource-bundles&bundleappname=base-bundle-application&locale=de
		ResponseEntity<Properties> entity = this.restTemplate.exchange(restUrl, HttpMethod.GET,
			requestEntity, Properties.class, map);
		assertNotNull(entity);
	}

	@Test
	public void testGetString()
	{
		String restUrl = getBaseUrl(serverPort) + ResourcebundlesController.REST_PATH_VALUE
			+ "?basename={basename}&bundleappname={bundleappname}&key={key}&locale={locale}";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		Map<String, String> map = new HashMap<String, String>();
		map.put("bundleappname", "base-bundle-application");
		map.put("basename", "base-resource-bundles");
		map.put("locale", "de");
		map.put("key", "resource.bundles.test.label");
		// http://localhost:5000/v1/resourcebundle/value?basename=base-resource-bundles&bundleappname=base-bundle-application&key=resource.bundles.test.label&locale=de
		ResponseEntity<String> entity = this.restTemplate.exchange(restUrl, HttpMethod.GET,
			requestEntity, String.class, map);
		assertNotNull(entity);
	}

	@Test
	public void testGetStringWithParameters()
	{
		final String[] paramsGerman = { "Fritz", "Berlin" };
		StringBuilder sb = new StringBuilder();
		sb.append(getBaseUrl(serverPort))
			.append(ResourcebundlesController.REST_PATH_VALUE_WITH_PARAMS)
			.append("?basename={basename}&bundleappname={bundleappname}&key={key}&locale={locale}");
		for (String param : paramsGerman)
		{
			sb.append("&params=").append(param);
		}
		String restUrl = sb.toString();

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		Map<String, String> map = new HashMap<String, String>();
		map.put("bundleappname", "base-bundle-application");
		map.put("basename", "test");
		map.put("locale", "de_DE");
		map.put("key", "com.example.gui.prop.with.params.label");
		// http://localhost:5000/v1/resourcebundle/value/withparams?basename=test&bundleappname=base-bundle-application&key=com.example.gui.prop.with.params.label&locale=de_DE&params=Fritz&params=Berlin
		ResponseEntity<String> entity = this.restTemplate.exchange(restUrl, HttpMethod.GET,
			requestEntity, String.class, map);
		assertNotNull(entity);
	}


	@Test
	public void testUpdateProperties() throws JsonProcessingException
	{
		StringBuilder sb = new StringBuilder();
		sb.append(getBaseUrl(serverPort))
			.append(ResourcebundlesController.REST_PATH_UPDATE_BUNDLENAME)
		// .append(
		// "?basename={basename}&bundleappname={bundleappname}&filepath={filepath}&locale={locale}")
		;

		String restUrl = sb.toString();

		Properties properties;
		String ownerName;
		String baseName;
		Locale locale;
		String filepath;

		properties = new Properties();
		properties.setProperty("utest.one", "one val");
		properties.setProperty("utest.two", "two val");
		properties.setProperty("utest.three", "three val");
		ownerName = "base-bundle-application";
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

		restUrl = getBaseUrl(serverPort) + ResourcebundlesController.REST_PATH_RESOURCES
			+ "?basename={basename}&bundleappname={bundleappname}&locale={locale}";
		headers = new HttpHeaders();
		requestEntity = new HttpEntity<>(headers);
		map = new HashMap<String, String>();
		map.put("bundleappname", ownerName);
		map.put("basename", baseName);
		map.put("locale", localeFilenameSuffix);
		// http://localhost:5000/v1/resourcebundle/resourcebundles?basename=base-resource-bundles&bundleappname=base-bundle-application&locale=de

		ResponseEntity<List<Resourcebundle>> newentity = this.restTemplate.exchange(restUrl,
			HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Resourcebundle>>()
			{
			}, map);
		assertNotNull(newentity);

	}

}
