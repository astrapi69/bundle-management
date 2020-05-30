package de.alpharogroup.bundlemanagement.mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.*;
import de.alpharogroup.bundlemanagement.viewmodel.*;
import de.alpharogroup.collections.set.SetFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest
{
	@Autowired BaseNamesMapper baseNamesMapper;
	@Autowired BundleApplicationsMapper bundleApplicationsMapper;
	@Autowired BundleNamesMapper bundleNamesMapper;
	@Autowired CountriesMapper countriesMapper;
	@Autowired LanguageLocalesMapper languageLocalesMapper;
	@Autowired
	LanguagesMapper languagesMapper;
	@Autowired
	PropertiesKeysMapper propertiesKeysMapper;
	@Autowired
	PropertiesValuesMapper propertiesValuesMapper;
	@Autowired
	ResourcebundlesMapper resourcebundlesMapper;

//	@Ignore
	@Test
	public void testToDto()
	{
		PropertiesKeys propertiesKeys = PropertiesKeys.builder()
			.id(UUID.randomUUID())
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