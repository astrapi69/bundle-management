/**
 * The MIT License
 *
 * Copyright (C) 2007 - 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *  *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *  *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.bundlemanagement.service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.bundlemanagement.jpa.entity.BaseNames;
import de.alpharogroup.bundlemanagement.jpa.entity.BundleApplications;
import de.alpharogroup.bundlemanagement.jpa.entity.BundleNames;
import de.alpharogroup.bundlemanagement.jpa.entity.LanguageLocales;
import de.alpharogroup.bundlemanagement.jpa.repository.BundleApplicationsRepository;
import de.alpharogroup.bundlemanagement.jpa.repository.BundleNamesRepository;
import de.alpharogroup.collections.CollectionExtensions;
import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.resourcebundle.locale.LocaleExtensions;
import de.alpharogroup.spring.service.api.GenericService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * The class {@link BundleNamesService}
 */
@Transactional
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class BundleNamesService
	implements
		GenericService<BundleNames, Integer, BundleNamesRepository>
{

	BaseNamesService baseNamesService;

	BundleApplicationsRepository bundleApplicationsRepository;

	LanguageLocalesService languageLocalesService;

	BundleNamesRepository repository;

	public List<BundleNames> find(BaseNames baseName)
	{
		return find(null, baseName != null ? baseName.getName() : null, (String)null);
	}

	public List<BundleNames> find(BundleApplications owner)
	{
		return repository.findByOwner(owner);
	}

	public List<BundleNames> find(final BundleApplications owner, final BaseNames baseName)
	{
		if (baseName != null)
		{
			return find(owner, baseName.getName(), (String)null);
		}
		return null;
	}

	public BundleNames find(final BundleApplications owner, final BaseNames baseName,
		final LanguageLocales languageLocales)
	{
		String bn = null;
		String ll = null;
		if (baseName != null)
		{
			bn = baseName.getName();
		}
		if (languageLocales != null)
		{
			ll = languageLocales.getLocale();
		}
		if (bn != null && ll != null)
		{
			return ListExtensions.getFirst(find(owner, bn, ll));
		}
		return null;
	}

	public List<BundleNames> find(final BundleApplications owner, final String baseName)
	{
		if (baseName != null)
		{
			return find(owner, baseName, (String)null);
		}
		return null;
	}

	public BundleNames find(final BundleApplications owner, final String baseName,
		final Locale locale)
	{
		return ListExtensions
			.getFirst(find(owner, baseName, LocaleExtensions.getLocaleFilenameSuffix(locale)));
	}

	public List<BundleNames> find(final BundleApplications owner, final String baseName,
		final String locale)
	{
		final List<BundleNames> bundleNames = repository.findByOwnerAndBaseNameAndLocale(owner.getName(),
			baseName, locale);
		return bundleNames;
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

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public BundleNames getOrCreateNewBundleNames(BundleApplications owner, final String baseName,
		final Locale locale)
	{
		BundleNames bundleNames = find(owner, baseName, locale);
		if (bundleNames == null)
		{
			final LanguageLocales dbLocale = languageLocalesService
				.getOrCreateNewLanguageLocales(locale);
			final BaseNames bn = baseNamesService.getOrCreateNewNameEntity(baseName);
			bundleNames = BundleNames.builder().owner(owner).baseName(bn).locale(dbLocale).build();

			bundleNames = merge(bundleNames);

			if (!owner.isSupported(dbLocale))
			{
				Optional<BundleApplications> byId = bundleApplicationsRepository
					.findById(owner.getId());
				if (byId.isPresent())
				{
					owner = byId.get();
					owner.addSupportedLanguageLocale(dbLocale);
					bundleApplicationsRepository.save(owner);
				}
			}
		}
		return bundleNames;
	}

	public BundleNames merge(BundleNames object)
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
		return repository.save(object);
	}

}
