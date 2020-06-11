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
package de.alpharogroup.bundlemanagement.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.alpharogroup.bundlemanagement.jpa.entity.BaseNames;
import de.alpharogroup.bundlemanagement.jpa.entity.BundleApplications;
import de.alpharogroup.bundlemanagement.jpa.entity.BundleNames;
import de.alpharogroup.bundlemanagement.jpa.entity.Countries;
import de.alpharogroup.bundlemanagement.jpa.entity.LanguageLocales;
import de.alpharogroup.bundlemanagement.jpa.entity.Languages;
import de.alpharogroup.bundlemanagement.jpa.entity.PropertiesKeys;
import de.alpharogroup.bundlemanagement.jpa.entity.PropertiesValues;
import de.alpharogroup.bundlemanagement.jpa.entity.Resourcebundles;
import de.alpharogroup.bundlemanagement.viewmodel.BaseName;
import de.alpharogroup.bundlemanagement.viewmodel.BundleApplication;
import de.alpharogroup.bundlemanagement.viewmodel.BundleName;
import de.alpharogroup.bundlemanagement.viewmodel.Country;
import de.alpharogroup.bundlemanagement.viewmodel.Language;
import de.alpharogroup.bundlemanagement.viewmodel.LanguageLocale;
import de.alpharogroup.bundlemanagement.viewmodel.PropertiesKey;
import de.alpharogroup.bundlemanagement.viewmodel.PropertiesValue;
import de.alpharogroup.bundlemanagement.viewmodel.Resourcebundle;
import de.alpharogroup.collections.set.SetFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest
{
	@Autowired
	BaseNamesMapper baseNamesMapper;
	@Autowired
	BundleApplicationsMapper bundleApplicationsMapper;
	@Autowired
	BundleNamesMapper bundleNamesMapper;
	@Autowired
	CountriesMapper countriesMapper;
	@Autowired
	LanguageLocalesMapper languageLocalesMapper;
	@Autowired
	LanguagesMapper languagesMapper;
	@Autowired
	PropertiesKeysMapper propertiesKeysMapper;
	@Autowired
	PropertiesValuesMapper propertiesValuesMapper;
	@Autowired
	ResourcebundlesMapper resourcebundlesMapper;

	// @Ignore
	@Test
	public void testToDto()
	{
		PropertiesKeys propertiesKeys = PropertiesKeys.builder().id(UUID.randomUUID())
			.name("foo.key").build();
		PropertiesKey propertiesKey = propertiesKeysMapper.toDto(propertiesKeys);
		assertNotNull(propertiesKey);
		PropertiesKeys propertiesKeyEntity = propertiesKeysMapper.toEntity(propertiesKey);
		assertEquals(propertiesKeys, propertiesKeyEntity);

		PropertiesValues propertiesValues = PropertiesValues.builder().name("bar value").build();
		PropertiesValue propertiesValue = propertiesValuesMapper.toDto(propertiesValues);
		assertNotNull(propertiesValue);
		PropertiesValues propertiesValueEntity = propertiesValuesMapper.toEntity(propertiesValue);
		assertEquals(propertiesValues, propertiesValueEntity);

		BaseNames baseNames = BaseNames.builder().name("foo").build();
		BaseName baseName = baseNamesMapper.toDto(baseNames);
		assertNotNull(baseName);
		BaseNames baseNameEntity = baseNamesMapper.toEntity(baseName);
		assertEquals(baseNames, baseNameEntity);

		LanguageLocales languageLocales = LanguageLocales.builder().locale("el").build();
		LanguageLocale languageLocale = languageLocalesMapper.toDto(languageLocales);
		assertNotNull(languageLocale);
		LanguageLocales languageLocaleEntity = languageLocalesMapper.toEntity(languageLocale);
		assertEquals(languageLocales, languageLocaleEntity);

		Countries countries = Countries.builder().name("Greece").iso3166a2name("GR").build();
		Country country = countriesMapper.toDto(countries);
		assertNotNull(country);
		Countries countryEntity = countriesMapper.toEntity(country);
		assertEquals(countries, countryEntity);

		Languages languages = Languages.builder().iso639Dash1("el").build();
		Language language = languagesMapper.toDto(languages);
		assertNotNull(language);
		Languages languageEntity = languagesMapper.toEntity(language);
		assertEquals(languages, languageEntity);

		BundleApplications bundleApplications = BundleApplications.builder().name("test-bundle-app")
			.defaultLocale(languageLocales).supportedLocales(SetFactory.newHashSet(languageLocales))
			.build();

		BundleApplication bundleApplication = bundleApplicationsMapper.toDto(bundleApplications);
		assertNotNull(bundleApplication);
		BundleApplications bundleApplicationEntity = bundleApplicationsMapper
			.toEntity(bundleApplication);
		assertEquals(bundleApplications, bundleApplicationEntity);

		BundleNames bundleNames = BundleNames.builder().baseName(baseNames)
			.filepath("/opt/i18n/foo.yml").owner(bundleApplications).locale(languageLocales)
			.build();
		BundleName bundleName = bundleNamesMapper.toDto(bundleNames);
		assertNotNull(bundleName);
		BundleNames bundleNameEntity = bundleNamesMapper.toEntity(bundleName);
		assertEquals(bundleNames, bundleNameEntity);

		Resourcebundles resourcebundles = Resourcebundles.builder().bundleName(bundleNames)
			.key(propertiesKeys).value(propertiesValues).build();
		Resourcebundle resourcebundle = resourcebundlesMapper.toDto(resourcebundles);
		assertNotNull(resourcebundle);
		Resourcebundles resourcebundleEntity = resourcebundlesMapper.toEntity(resourcebundle);
		assertEquals(resourcebundles, resourcebundleEntity);

	}
}