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
package io.github.astrapi69.bundlemanagement.service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import io.github.astrapi69.bundlemanagement.jpa.entity.Resourcebundles;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import io.github.astrapi69.bundlemanagement.jpa.entity.BaseNames;
import io.github.astrapi69.bundlemanagement.jpa.entity.BundleApplications;
import io.github.astrapi69.bundlemanagement.jpa.entity.BundleNames;
import io.github.astrapi69.bundlemanagement.jpa.entity.LanguageLocales;
import io.github.astrapi69.bundlemanagement.jpa.repository.BundleApplicationsRepository;
import io.github.astrapi69.bundlemanagement.jpa.repository.BundleNamesRepository;
import io.github.astrapi69.collections.CollectionExtensions;
import io.github.astrapi69.collections.list.ListExtensions;
import io.github.astrapi69.resourcebundle.locale.LocaleExtensions;
import io.github.astrapi69.spring.service.api.GenericService;

/**
 * The class {@link BundleNamesService}
 */
@Transactional
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class BundleNamesService implements GenericService<BundleNames, UUID, BundleNamesRepository>
{

	BaseNamesService baseNamesService;

	BundleApplicationsRepository bundleApplicationsRepository;

	LanguageLocalesService languageLocalesService;

	BundleNamesRepository repository;

	ResourcebundlesService resourcebundlesService;

	public List<BundleNames> find(final @NonNull BaseNames baseName)
	{
		return repository.findByBaseName(baseName.getName());
	}

	public List<BundleNames> find(final @NonNull BundleApplications owner)
	{
		return repository.findByOwner(owner);
	}

	public List<BundleNames> find(final @NonNull BundleApplications owner,
		final @NonNull BaseNames baseName)
	{
		return repository.findByOwnerAndBaseName(owner.getName(), baseName.getName());
	}

	public BundleNames find(final @NonNull BundleApplications owner,
		final @NonNull BaseNames baseName, final @NonNull LanguageLocales languageLocales)
	{
		return repository.findDistinctByOwnerAndBaseNameAndLocale(owner.getName(),
			baseName.getName(), languageLocales.getLocale());
	}

	public List<BundleNames> find(final @NonNull BundleApplications owner,
		final @NonNull String baseName)
	{
		return repository.findByOwnerAndBaseName(owner.getName(), baseName);
	}

	public BundleNames find(final BundleApplications owner, final String baseName,
		final Locale locale)
	{
		return repository.findDistinctByOwnerAndBaseNameAndLocale(owner.getName(), baseName,
			LocaleExtensions.getLocaleFilenameSuffix(locale));
	}

	public BundleNames find(final BundleApplications owner, final String baseName,
		final String locale)
	{
		return repository.findDistinctByOwnerAndBaseNameAndLocale(owner.getName(), baseName,
			locale);
	}

	public BundleNames find(final String ownerName, final String baseName, final String locale)
	{
		return repository.findDistinctByOwnerAndBaseNameAndLocale(ownerName, baseName, locale);
	}

	public LanguageLocales getDefaultLocale(final BundleApplications owner, final String baseName)
	{
		final List<BundleNames> list = find(owner, baseName);
		if (CollectionExtensions.isNotEmpty(list))
		{
			return getDefaultLocale(ListExtensions.getFirst(list));
		}
		return null;
	}

	public LanguageLocales getDefaultLocale(final BundleNames bundleNames)
	{
		Optional<BundleApplications> byId = bundleApplicationsRepository
			.findById(bundleNames.getId());
		if (byId.isPresent())
		{
			return byId.get().getDefaultLocale();
		}
		return null;
	}

	public BundleNames getOrCreateNewBundleNames(BundleApplications owner, final String baseName,
		final Locale locale)
	{
		return getOrCreateNewBundleNames(owner.getName(), baseName,
			LocaleExtensions.getLocaleFilenameSuffix(locale));
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public BundleNames getOrCreateNewBundleNames(String ownerName, final String baseName,
		final String locale)
	{
		BundleNames bundleNames = find(ownerName, baseName, locale);
		if (bundleNames == null)
		{
			BundleApplications ownerByName = bundleApplicationsRepository
				.findDistinctByName(ownerName);
			final LanguageLocales dbLocale = languageLocalesService
				.getOrCreateNewLanguageLocales(locale);
			final BaseNames bn = baseNamesService.getOrCreateNewNameEntity(baseName);
			bundleNames = BundleNames.builder().owner(ownerByName).baseName(bn).locale(dbLocale)
				.build();

			bundleNames = merge(bundleNames);

			if (!ownerByName.isSupported(dbLocale))
			{
				Optional<BundleApplications> byId = bundleApplicationsRepository
					.findById(ownerByName.getId());
				if (byId.isPresent())
				{
					ownerByName = byId.get();
					ownerByName.addSupportedLanguageLocale(dbLocale);
					bundleApplicationsRepository.save(ownerByName);
				}
			}
		}
		return bundleNames;
	}

	public BundleNames merge(@NonNull BundleNames object)
	{
		if (object.getId() != null)
		{
			Optional<BundleNames> byId = repository.findById(object.getId());
			if (!byId.isPresent())
			{
				BundleNames dbBundleNames = byId.get();
				BaseNames dbBaseName;
				dbBaseName = dbBundleNames.getBaseName();
				// check if basename have changed
				if (!dbBaseName.equals(object.getBaseName()))
				{
					// find all bundlenames with the same basename
					List<BundleNames> applicationBundleNames = find(object.getOwner(), dbBaseName);
					// get or create new name entity
					BaseNames newBaseName = baseNamesService
						.getOrCreateNewNameEntity(object.getBaseName().getName());
					// update this bundlenames object with the new basename
					object.setBaseName(newBaseName);
					// update all other bundlenames object with the same basename
					for (BundleNames bn : applicationBundleNames)
					{
						bn.setBaseName(newBaseName);
						if (!bn.equals(object))
						{
							repository.save(bn);
						}
					}
				}
			}
		}
		return repository.save(object);
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(BundleNames bundleNames)
	{
		List<Resourcebundles> list = resourcebundlesService.find(bundleNames);
		resourcebundlesService.delete(list);
		BaseNames baseName = bundleNames.getBaseName();
		bundleNames.setBaseName(null);
		bundleNames.setLocale(null);
		bundleNames.setOwner(null);
		final BundleNames merged = merge(bundleNames);
		repository.delete(merged);
		if (0 == find(baseName).size())
		{
			baseNamesService.delete(baseName);
		}
	}

}
