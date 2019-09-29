package de.alpharogroup.bundlemanagement.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import de.alpharogroup.collections.set.SetFactory;
import de.alpharogroup.db.resource.bundles.domain.BaseName;
import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.db.resource.bundles.domain.BundleName;
import de.alpharogroup.db.resource.bundles.domain.Country;
import de.alpharogroup.db.resource.bundles.domain.Language;
import de.alpharogroup.db.resource.bundles.domain.LanguageLocale;
import de.alpharogroup.db.resource.bundles.domain.PropertiesKey;
import de.alpharogroup.db.resource.bundles.domain.PropertiesValue;
import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest
{
	@Autowired
	BaseNameMapper baseNameMapper;
	@Autowired
	BundleApplicationMapper bundleApplicationMapper;
	@Autowired
	BundleNameMapper bundleNameMapper;
	@Autowired
	CountryMapper countryMapper;
	@Autowired
	LanguageLocaleMapper languageLocaleMapper;
	@Autowired
	LanguageMapper languageMapper;
	@Autowired
	PropertiesKeyMapper propertiesKeyMapper;
	@Autowired
	PropertiesValueMapper propertiesValueMapper;
	@Autowired
	ResourcebundleMapper resourcebundleMapper;

	@Test
	public void testToDto()
	{
		PropertiesKeys propertiesKeys = PropertiesKeys.builder().name("foo.key").build();
		PropertiesKey propertiesKey = propertiesKeyMapper.toDto(propertiesKeys);
		assertNotNull(propertiesKey);
		PropertiesKeys propertiesKeyEntity = propertiesKeyMapper.toEntity(propertiesKey);
		assertEquals(propertiesKeys, propertiesKeyEntity);

		PropertiesValues propertiesValues = PropertiesValues.builder().name("bar value").build();
		PropertiesValue propertiesValue = propertiesValueMapper.toDto(propertiesValues);
		assertNotNull(propertiesValue);
		PropertiesValues propertiesValueEntity = propertiesValueMapper.toEntity(propertiesValue);
		assertEquals(propertiesValues, propertiesValueEntity);

		BaseNames baseNames = BaseNames.builder().name("foo").build();
		BaseName baseName = baseNameMapper.toDto(baseNames);
		assertNotNull(baseName);
		BaseNames baseNameEntity = baseNameMapper.toEntity(baseName);
		assertEquals(baseNames, baseNameEntity);

		LanguageLocales languageLocales = LanguageLocales.builder().locale("el").build();
		LanguageLocale languageLocale = languageLocaleMapper.toDto(languageLocales);
		assertNotNull(languageLocale);
		LanguageLocales languageLocaleEntity = languageLocaleMapper.toEntity(languageLocale);
		assertEquals(languageLocales, languageLocaleEntity);

		Countries countries = Countries.builder().name("Greece").iso3166a2name("GR").build();
		Country country = countryMapper.toDto(countries);
		assertNotNull(country);
		Countries countryEntity = countryMapper.toEntity(country);
		assertEquals(countries, countryEntity);

		Languages languages = Languages.builder().iso639Dash1("el").build();
		Language language = languageMapper.toDto(languages);
		assertNotNull(language);
		Languages languageEntity = languageMapper.toEntity(language);
		assertEquals(languages, languageEntity);

		BundleApplications bundleApplications = BundleApplications.builder().name("test-bundle-app")
			.defaultLocale(languageLocales).supportedLocales(SetFactory.newHashSet(languageLocales))
			.build();

		BundleApplication bundleApplication = bundleApplicationMapper.toDto(bundleApplications);
		assertNotNull(bundleApplication);
		BundleApplications bundleApplicationEntity = bundleApplicationMapper
			.toEntity(bundleApplication);
		assertEquals(bundleApplications, bundleApplicationEntity);

		BundleNames bundleNames = BundleNames.builder().baseName(baseNames)
			.filepath("/opt/i18n/foo.yml").owner(bundleApplications).locale(languageLocales)
			.build();
		BundleName bundleName = bundleNameMapper.toDto(bundleNames);
		assertNotNull(bundleName);
		BundleNames bundleNameEntity = bundleNameMapper.toEntity(bundleName);
		assertEquals(bundleNames, bundleNameEntity);

		Resourcebundles resourcebundles = Resourcebundles.builder().bundleName(bundleNames)
			.key(propertiesKeys).value(propertiesValues).build();
		Resourcebundle resourcebundle = resourcebundleMapper.toDto(resourcebundles);
		assertNotNull(resourcebundle);
		Resourcebundles resourcebundleEntity = resourcebundleMapper.toEntity(resourcebundle);
		assertEquals(resourcebundles, resourcebundleEntity);

	}
}