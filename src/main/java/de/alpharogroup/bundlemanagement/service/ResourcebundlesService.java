/**
 * The MIT License
 * <p>
 * Copyright (C) 2007 - 2015 Asterios Raptis
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.bundlemanagement.service;

import de.alpharogroup.bundlemanagement.jpa.entity.*;
import de.alpharogroup.bundlemanagement.jpa.repository.BundleApplicationsRepository;
import de.alpharogroup.bundlemanagement.jpa.repository.ResourcebundlesRepository;
import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.collections.properties.PropertiesExtensions;
import de.alpharogroup.resourcebundle.locale.LocaleExtensions;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;
import de.alpharogroup.spring.service.api.GenericService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;

/**
 * The class {@link ResourcebundlesService}
 */
@Log
@Transactional
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class ResourcebundlesService
	implements
		GenericService<Resourcebundles, UUID, ResourcebundlesRepository>
{

	BaseNamesService baseNamesService;

	BundleApplicationsRepository bundleApplicationsRepository;

	BundleNamesService bundleNamesService;

	LanguageLocalesService languageLocalesService;

	PropertiesKeysService propertiesKeysService;

	PropertiesValuesService propertiesValuesService;

	ResourcebundlesRepository repository;

	public Resourcebundles contains(BundleApplications owner, String baseName, Locale locale,
		String key)
	{
		return getResourcebundle(owner, baseName, locale, key);
	}

	public void delete(BundleNames bundleNames)
	{
		List<Resourcebundles> list = find(bundleNames);
		delete(list);
		BaseNames baseName = bundleNames.getBaseName();
		bundleNames.setBaseName(null);
		bundleNames.setLocale(null);
		bundleNames.setOwner(null);
		final BundleNames merged = bundleNamesService.save(bundleNames);
		bundleNamesService.delete(merged);
		if (0 == bundleNamesService.find(baseName).size())
		{
			baseNamesService.delete(baseName);
		}
	}

	public void delete(final List<Resourcebundles> resourcebundles)
	{
		for (final Resourcebundles resourcebundle : resourcebundles)
		{
			delete(resourcebundle);
		}
	}

	@Override
	public void delete(Resourcebundles resourcebundles)
	{
		PropertiesKeys key = resourcebundles.getKey();
		PropertiesValues value = resourcebundles.getValue();
		resourcebundles.setBundleName(null);
		resourcebundles.setKey(null);
		resourcebundles.setValue(null);
		resourcebundles = repository.save(resourcebundles);
		repository.delete(resourcebundles);
		if (0 == find(key).size())
		{
			propertiesKeysService.delete(key);
		}
		if (0 == find(value).size())
		{
			propertiesValuesService.delete(value);
		}
	}

	public List<Resourcebundles> find(BundleApplications owner, String baseName, String locale,
		String key)
	{
		List<Resourcebundles> resourcebundles;
		if (key != null)
		{
			resourcebundles = repository.findByOwnerAndBaseNameAndLocaleAndKeyAndValue(
				owner.getName(), baseName, locale, key);
		}
		else
		{
			resourcebundles = repository.findByOwnerAndBaseNameAndLocale(owner.getName(), baseName,
				locale);
		}
		return resourcebundles;
	}

	public List<Resourcebundles> find(BundleNames bundleName)
	{
		return find(bundleName.getOwner(), bundleName.getBaseName().getName(),
			bundleName.getLocale().getLocale(), null);
	}

	public List<Resourcebundles> find(PropertiesKeys key)
	{
		return find(null, null, null, key.getName());
	}

	public List<Resourcebundles> find(PropertiesValues value)
	{
		// TODO fixme
		return find(null, null, null, null
		// , value.getName()
		);
	}

	public BundleApplications find(String name)
	{
		return bundleApplicationsRepository.findDistinctByName(name);
	}

	public List<BundleApplications> findAllBundleApplications()
	{
		Iterable<BundleApplications> all = bundleApplicationsRepository.findAll();
		List<BundleApplications> target = ListFactory.newArrayList();
		all.forEach(target::add);
		return target;
	}

	public List<Resourcebundles> findResourceBundles(BundleApplications owner, String baseName,
		Locale locale)
	{
		return find(owner, baseName, LocaleExtensions.getLocaleFilenameSuffix(locale), null);
	}

	public List<Resourcebundles> findResourceBundles(BundleApplications owner, String baseName,
		Locale locale, String key)
	{
		return find(owner, baseName, LocaleExtensions.getLocaleFilenameSuffix(locale), key);
	}

	public List<Resourcebundles> findResourceBundles(final BundleNames bundleName)
	{
		final String baseName = bundleName.getBaseName().getName();
		final Locale locale = LocaleResolver.resolveLocale(bundleName.getLocale().getLocale());
		final BundleApplications owner = bundleName.getOwner();
		return findResourceBundles(owner, baseName, locale);
	}

	public Properties getProperties(BundleApplications owner, String baseName, Locale locale)
	{
		final Properties properties = new Properties();
		final List<Resourcebundles> resourcebundles = findResourceBundles(owner, baseName, locale);
		for (final Resourcebundles resourcebundle : resourcebundles)
		{
			properties.setProperty(resourcebundle.getKey().getName(),
				resourcebundle.getValue().getName());
		}
		return properties;
	}

	public Properties getProperties(BundleApplications owner, String baseName, String localeCode)
	{
		return getProperties(owner, baseName, LocaleResolver.resolveLocale(localeCode));
	}

	public Properties getProperties(final BundleNames bundleName)
	{
		final Properties properties = new Properties();
		final List<Resourcebundles> resourcebundles = findResourceBundles(bundleName);
		for (final Resourcebundles resourcebundle : resourcebundles)
		{
			properties.setProperty(resourcebundle.getKey().getName(),
				resourcebundle.getValue().getName());
		}
		return properties;
	}

	public Resourcebundles getResourcebundle(BundleApplications owner, String baseName,
		Locale locale, String key)
	{
		return repository.findDistinctByOwnerAndBaseNameAndLocaleAndKeyAndValue(owner.getName(),
			baseName, LocaleExtensions.getLocaleFilenameSuffix(locale), key);
	}

	public List<BundleNames> importProperties(BundleApplications bundleApplication,
		List<KeyValuePair<File, Locale>> foundProperties) throws IOException
	{
		final List<BundleNames> list = new ArrayList<>();
		for (final KeyValuePair<File, Locale> entry : foundProperties)
		{
			final File propertiesFile = entry.getKey();
			final Locale locale = entry.getValue();
			final String bundlename = LocaleResolver.resolveBundlename(propertiesFile);
			final Properties properties = PropertiesExtensions.loadProperties(propertiesFile);
			final BundleNames bundleNames = updateProperties(bundleApplication, properties,
				bundlename, locale, false);
			list.add(bundleNames);
			bundleApplication = bundleApplicationsRepository.save(bundleApplication);
		}
		return list;
	}

	private void initialize(final Resourcebundles resourcebundles)
	{
		LanguageLocales languageLocales = languageLocalesService
			.getOrCreateNewLanguageLocales(resourcebundles.getBundleName().getLocale().getLocale());

		BundleNames bundleNames = bundleNamesService.getOrCreateNewBundleNames(
			resourcebundles.getBundleName().getOwner(),
			resourcebundles.getBundleName().getBaseName().getName(),
			languageLocalesService.resolveLocale(languageLocales));

		PropertiesKeys propertiesKeys = propertiesKeysService
			.getOrCreateNewNameEntity(resourcebundles.getKey().getName());

		resourcebundles.setBundleName(bundleNames);
		resourcebundles.setKey(propertiesKeys);
	}

	@Transactional
	public Resourcebundles merge(final Resourcebundles resourcebundles)
	{
		PropertiesKeys key;
		PropertiesValues value;
		if (resourcebundles.getId() != null){
			Optional<Resourcebundles> byId = repository.findById(resourcebundles.getId());
			if (byId.isPresent())
			{
				Resourcebundles dbEntity = byId.get();
				key = dbEntity.getKey();
				value = dbEntity.getValue();
				if (!key.equals(resourcebundles.getKey()) && 1 < find(key).size())
				{
					key = PropertiesKeys.builder().name(resourcebundles.getKey().getName()).build();
					PropertiesKeys merged = propertiesKeysService.merge(key);
					resourcebundles.setKey(merged);
				}
				if (!value.equals(resourcebundles.getValue()) && 1 < find(value).size())
				{
					value = PropertiesValues.builder().name(resourcebundles.getValue().getName())
						.build();
					PropertiesValues merged = propertiesValuesService.merge(value);
					resourcebundles.setValue(merged);
				}
			}
		}
		try
		{
			return repository.save(resourcebundles);
		}
		catch (final Exception e)
		{
			log.log(Level.SEVERE, "merge fail with super.merge(resourcebundles)", e);
			initialize(resourcebundles);
			return repository.save(resourcebundles);
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Resourcebundles saveOrUpdateEntry(final BundleNames bundleName, final String baseName,
		final Locale locale, final String key, final String value, final boolean update)
	{
		Resourcebundles resourcebundle = getResourcebundle(bundleName.getOwner(), baseName, locale,
			key);
		PropertiesValues pvalue = propertiesValuesService.getOrCreateNewNameEntity(value);
		if (resourcebundle != null)
		{
			if (update)
			{
				resourcebundle.setValue(pvalue);
			}
		}
		else
		{
			final PropertiesKeys pkey = propertiesKeysService.getOrCreateNewNameEntity(key);
			resourcebundle = Resourcebundles.builder().bundleName(bundleName).key(pkey)
				.value(pvalue).build();
		}
		resourcebundle = merge(resourcebundle);
		return resourcebundle;
	}

	public BundleNames updateProperties(final @NonNull BundleApplications owner,
		final @NonNull Properties properties, final @NonNull String baseName,
		final @NonNull Locale locale)
	{
		return updateProperties(owner, properties, baseName, locale, true);
	}

	public BundleNames updateProperties(final @NonNull BundleApplications owner,
		final @NonNull Properties properties, final @NonNull String baseName,
		final @NonNull Locale locale, final boolean update)
	{
		return updateProperties(owner, properties, baseName, null, locale, update);
	}

	@Transactional
	public BundleNames updateProperties(final @NonNull BundleApplications owner,
		final @NonNull Properties properties, final @NonNull String baseName, final String filepath,
		final @NonNull Locale locale, final boolean update)
	{
		final BundleNames bundleName = bundleNamesService.getOrCreateNewBundleNames(owner, baseName,
			locale);
		if (filepath != null)
		{
			bundleName.setFilepath(filepath);
		}
		final Properties dbProperties = getProperties(bundleName);

		properties.entrySet().parallelStream().forEach(element -> {
			final String key = element.getKey().toString().trim();
			final String value = element.getValue().toString().trim();
			if (dbProperties.containsKey(key))
			{
				final String dbValue = dbProperties.getProperty(key);
				if (!value.equals(dbValue))
				{
					return;
				}
			}
			saveOrUpdateEntry(bundleName, baseName, locale, key, value, update);
		});
		return bundleName;
	}

	public BundleNames updateProperties(final @NonNull Properties properties,
		final @NonNull String owner, final @NonNull String baseName,
		final @NonNull String localeCode)
	{
		BundleApplications bundleApplications = bundleApplicationsRepository
			.findDistinctByName(owner);
		Locale locale = LocaleResolver.resolveLocale(localeCode, false);
		return updateProperties(bundleApplications, properties, baseName, locale);
	}

}