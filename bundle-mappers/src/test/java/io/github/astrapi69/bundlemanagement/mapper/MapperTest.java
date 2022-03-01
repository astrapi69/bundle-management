/**
 * The MIT License
 * <p>
 * Copyright (C) 2015 Asterios Raptis
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.bundlemanagement.mapper;

import io.github.astrapi69.bundlemanagement.jpa.entity.BaseNames;
import io.github.astrapi69.bundlemanagement.jpa.entity.BundleApplications;
import io.github.astrapi69.bundlemanagement.jpa.entity.BundleNames;
import io.github.astrapi69.bundlemanagement.jpa.entity.Countries;
import io.github.astrapi69.bundlemanagement.jpa.entity.LanguageLocales;
import io.github.astrapi69.bundlemanagement.jpa.entity.Languages;
import io.github.astrapi69.bundlemanagement.jpa.entity.PropertiesKeys;
import io.github.astrapi69.bundlemanagement.jpa.entity.PropertiesValues;
import io.github.astrapi69.bundlemanagement.jpa.entity.Resourcebundles;
import io.github.astrapi69.bundlemanagement.viewmodel.BaseName;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleApplication;
import io.github.astrapi69.bundlemanagement.viewmodel.BundleName;
import io.github.astrapi69.bundlemanagement.viewmodel.Country;
import io.github.astrapi69.bundlemanagement.viewmodel.Language;
import io.github.astrapi69.bundlemanagement.viewmodel.LanguageLocale;
import io.github.astrapi69.bundlemanagement.viewmodel.PropertiesKey;
import io.github.astrapi69.bundlemanagement.viewmodel.PropertiesValue;
import io.github.astrapi69.bundlemanagement.viewmodel.Resourcebundle;
import io.github.astrapi69.collections.set.SetFactory;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MapperTest
{
	BaseNamesMapper baseNamesMapper;
	BundleApplicationsMapper bundleApplicationsMapper;
	BundleNamesMapper bundleNamesMapper;
	CountriesMapper countriesMapper;
	LanguageLocalesMapper languageLocalesMapper;
	LanguagesMapper languagesMapper;
	PropertiesKeysMapper propertiesKeysMapper;
	PropertiesValuesMapper propertiesValuesMapper;
	ResourcebundlesMapper resourcebundlesMapper;


	@Test
	public void testToDto()
	{

		baseNamesMapper = new BaseNamesMapper();
		bundleNamesMapper = new BundleNamesMapper();
		propertiesKeysMapper = new PropertiesKeysMapper();
		propertiesValuesMapper = new PropertiesValuesMapper();
		languageLocalesMapper = new LanguageLocalesMapper();
		countriesMapper = new CountriesMapper();
		bundleApplicationsMapper = new BundleApplicationsMapper();
		resourcebundlesMapper = new ResourcebundlesMapper();
		languagesMapper = new LanguagesMapper();
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

		BundleApplications bundleApplications = new BundleApplications();
		bundleApplications.setName("test-bundle-app");
		bundleApplications.setDefaultLocale(languageLocales);
		bundleApplications.setSupportedLocales(SetFactory.newHashSet(languageLocales));

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
