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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import io.github.astrapi69.bundlemanagement.enums.ActionRestPath;
import io.github.astrapi69.bundlemanagement.enums.AppRestPath;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleName;
import io.github.astrapi69.bundlemanagement.viewmodel.Resourcebundle;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.github.astrapi69.bundlemanagement.configuration.ApplicationConfiguration;
import io.github.astrapi69.bundlemanagement.viewmodel.Country;
import io.github.astrapi69.collections.array.ArrayFactory;
import io.github.astrapi69.spring.web.util.UrlExtensions;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BundleNamesControllerTest
{

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	int randomServerPort;

	public String getBaseUrl(int serverPort)
	{
		return UrlExtensions.getBaseUrl("http", "localhost", serverPort,
			AppRestPath.REST_VERSION + AppRestPath.REST_BUNDLE_NAMES);
	}

	@Test
	public void testSaveOrUpdate()
	{
		String[] requestParams = { "bundleappname", "basename", "locale" };
		String restUrl = UrlExtensions.generateUrl(getBaseUrl(randomServerPort),
			ActionRestPath.ACTION_SAVE_OR_UPDATE, requestParams);

		String newValue = RandomStringUtils.randomAlphabetic(10);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		Map<String, String> map = new HashMap<>();
		map.put("bundleappname", "test-bundle-application");
		map.put("basename", newValue);
		map.put("locale", "de_DE");
		// http://localhost:5000/v1/bundle/names/save/or/update?bundleappname=test-bundle-application&basename=testtest&locale=de_DE

		ResponseEntity<BundleName> entity = this.restTemplate.postForEntity(restUrl, requestEntity,
			BundleName.class, map);
		assertNotNull(entity);
		BundleName bundleName = entity.getBody();
		assertEquals(bundleName.getBaseName().getName(), newValue);
	}

}
