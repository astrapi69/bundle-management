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

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.github.astrapi69.bundlemanagement.enums.ActionRestPath;
import io.github.astrapi69.bundlemanagement.enums.AppRestPath;
import io.github.astrapi69.bundlemanagement.viewmodel.Language;
import io.github.astrapi69.collections.array.ArrayFactory;
import io.github.astrapi69.spring.web.util.UrlExtensions;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LanguagesControllerTest
{

	@LocalServerPort
	int randomServerPort;
	@Autowired
	private TestRestTemplate restTemplate;

	public String getBaseUrl(int serverPort)
	{
		return UrlExtensions.getBaseUrl("http", "localhost", serverPort,
			AppRestPath.REST_VERSION + AppRestPath.REST_LANGUAGES);
	}

	@Test
	public void testFind()
	{
		String[] requestParams = ArrayFactory.newArray("name");
		String restUrl = UrlExtensions.generateUrl(getBaseUrl(randomServerPort),
			ActionRestPath.ACTION_FIND, requestParams);
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
			ActionRestPath.ACTION_FIND_BY_CODE, requestParams);

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", "el");

		ResponseEntity<Language> entity = this.restTemplate.exchange(restUrl, HttpMethod.GET,
			requestEntity, Language.class, map);
		assertNotNull(entity);
	}

}
