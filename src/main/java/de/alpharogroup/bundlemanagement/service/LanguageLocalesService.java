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

import de.alpharogroup.bundlemanagement.jpa.entity.LanguageLocales;
import de.alpharogroup.bundlemanagement.jpa.repository.LanguageLocalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.resourcebundle.locale.LocaleExtensions;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;

/**
 * The class {@link LanguageLocalesService}
 */
@Transactional
@Service("languageLocalesService")
public class LanguageLocalesService
{
	@Autowired
	LanguageLocalesRepository languageLocalesRepository;

	public LanguageLocales find(Locale locale)
	{
		return find(LocaleExtensions.getLocaleFilenameSuffix(locale));
	}

	public LanguageLocales find(String locale)
	{
		final List<LanguageLocales> languageLocales = languageLocalesRepository.findByLocale(locale);
		return ListExtensions.getFirst(languageLocales);
	}

	public LanguageLocales getOrCreateNewLanguageLocales(final Locale locale)
	{
		LanguageLocales expected = find(locale);
		if (expected == null)
		{
			expected = LanguageLocales.builder()
				.locale(LocaleExtensions.getLocaleFilenameSuffix(locale)).build();
			expected = languageLocalesRepository.save(expected);
		}
		return expected;
	}

	public LanguageLocales getOrCreateNewLanguageLocales(final String locale)
	{
		LanguageLocales expected = find(locale);
		if (expected == null)
		{
			expected = LanguageLocales.builder()
				.locale(locale).build();
			expected = languageLocalesRepository.save(expected);
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
