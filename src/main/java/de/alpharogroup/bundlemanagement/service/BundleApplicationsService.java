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

import de.alpharogroup.bundlemanagement.jpa.entity.BundleApplications;
import de.alpharogroup.bundlemanagement.jpa.entity.BundleNames;
import de.alpharogroup.bundlemanagement.jpa.entity.LanguageLocales;
import de.alpharogroup.bundlemanagement.jpa.repository.BundleApplicationsRepository;
import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.collections.set.SetFactory;
import de.alpharogroup.spring.service.api.GenericService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * The class {@link BundleApplicationsService}
 */
@Transactional
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class BundleApplicationsService
	implements
		GenericService<BundleApplications, UUID, BundleApplicationsRepository>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	BundleNamesService bundleNamesService;

	LanguageLocalesService languageLocalesService;

	BundleApplicationsRepository repository;

	@Override
	public void delete(BundleApplications bundleApplications)
	{
		List<BundleNames> bundleNames = bundleNamesService.find(bundleApplications);
		for (BundleNames bundleName : bundleNames)
		{
			bundleNamesService.delete(bundleName);
		}
		bundleApplications.setDefaultLocale(null);
		bundleApplications.getSupportedLocales().clear();
		BundleApplications merged = repository.save(bundleApplications);
		repository.delete(merged);
	}

	@Override public BundleApplications save(BundleApplications entity)
	{
		if(entity.getDefaultLocale()!=null && entity.getDefaultLocale().getLocale()!=null){
			LanguageLocales languageLocales = languageLocalesService
				.getOrCreateNewLanguageLocales(entity.getDefaultLocale().getLocale());
			entity.setDefaultLocale(languageLocales);
		}
		if(entity.getSupportedLocales() != null && !entity.getSupportedLocales().isEmpty()){
			Set<LanguageLocales> supportedLocales = SetFactory.newHashSet();
			for (LanguageLocales ll : entity.getSupportedLocales()){
				LanguageLocales languageLocales = languageLocalesService
					.getOrCreateNewLanguageLocales(ll.getLocale());
				supportedLocales.add(languageLocales);
			}
			entity.setSupportedLocales(supportedLocales);
		}
		return repository.save(entity);
	}

	public Set<BundleNames> find(final BundleApplications owner)
	{
		return SetFactory.newHashSet(bundleNamesService.find(owner));
	}

	public BundleApplications find(final String name)
	{
		return repository.findDistinctByName(name);
	}

	public BundleApplications get(final BundleNames bundleName)
	{
		return bundleName.getOwner();
	}

	public BundleApplications getOrCreateNewBundleApplications(@NonNull final String name,
		@NonNull final LanguageLocales defaultLocale)
	{
		return getOrCreateNewBundleApplications(name, defaultLocale, null);
	}

	public BundleApplications getOrCreateNewBundleApplications(@NonNull final String name,
		@NonNull final LanguageLocales defaultLocale, Set<LanguageLocales> supportedLocales)
	{
		BundleApplications baseBundleApplication = find(name);
		if (baseBundleApplication == null)
		{
			baseBundleApplication = BundleApplications.builder().name(name)
				.defaultLocale(defaultLocale).supportedLocales(supportedLocales).build();

			baseBundleApplication = repository.save(baseBundleApplication);
		}
		return baseBundleApplication;
	}

	public BundleApplications getOrCreateNewBundleApplications(@NonNull final String name,
		@NonNull final String defaultLocale)
	{
		LanguageLocales languageLocales = languageLocalesService
			.getOrCreateNewLanguageLocales(defaultLocale);
		return getOrCreateNewBundleApplications(name, languageLocales);
	}

}
