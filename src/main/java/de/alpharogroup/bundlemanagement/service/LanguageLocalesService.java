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

import de.alpharogroup.bundlemanagement.jpa.entity.LanguageLocales;
import de.alpharogroup.bundlemanagement.jpa.repository.LanguageLocalesRepository;
import de.alpharogroup.resourcebundle.locale.LocaleExtensions;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;
import de.alpharogroup.spring.service.api.GenericService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.UUID;

/**
 * The class {@link LanguageLocalesService}
 */
@Transactional
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class LanguageLocalesService
	implements
		GenericService<LanguageLocales, UUID, LanguageLocalesRepository>
{

	LanguageLocalesRepository repository;

	public LanguageLocales find(Locale locale)
	{
		return find(LocaleExtensions.getLocaleFilenameSuffix(locale));
	}

	public LanguageLocales find(String locale)
	{
		return repository.findDistinctByLocale(locale);
	}

	public LanguageLocales getOrCreateNewLanguageLocales(final Locale locale)
	{
		LanguageLocales expected = find(locale);
		if (expected == null)
		{
			expected = LanguageLocales.builder()
				.locale(LocaleExtensions.getLocaleFilenameSuffix(locale)).build();
			expected = repository.save(expected);
		}
		return expected;
	}

	public LanguageLocales getOrCreateNewLanguageLocales(final String locale)
	{
		LanguageLocales expected = find(locale);
		if (expected == null)
		{
			expected = LanguageLocales.builder().locale(locale).build();
			expected = repository.save(expected);
		}
		return expected;
	}

	public Locale resolveLocale(LanguageLocales languageLocales)
	{
		Locale locale = null;
		if (languageLocales != null)
		{
			locale = LocaleResolver.resolveLocale(languageLocales.getLocale(), false);
		}
		return locale;
	}

}
