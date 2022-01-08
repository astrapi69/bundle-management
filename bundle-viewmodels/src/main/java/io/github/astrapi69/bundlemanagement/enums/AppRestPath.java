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
package io.github.astrapi69.bundlemanagement.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum AppRestPath
{
	VERSION(AppRestPath.REST_VERSION), RESOURCE_BUNDLES(
		AppRestPath.REST_RESOURCE_BUNDLES), COUNTRIES(AppRestPath.REST_COUNTRIES), LANGUAGES(
			AppRestPath.REST_LANGUAGES), LANGUAGE_LOCALES(
				AppRestPath.REST_LANGUAGE_LOCALES), BUNDLE_APPLICATIONS(
					AppRestPath.REST_BUNDLE_APPLICATIONS), BUNDLE_NAMES(
						AppRestPath.REST_BUNDLE_NAMES);

	public static final String SLASH = "/";
	public static final String REST_API_VERSION_1 = "v1";
	public static final String REST_VERSION = SLASH + REST_API_VERSION_1;
	public static final String REST_RESOURCE_BUNDLES = "/resourcebundles";
	public static final String REST_COUNTRIES = "/countries";
	public static final String REST_LANGUAGES = "/languages";
	public static final String REST_LANGUAGE_LOCALES = "/locales";
	public static final String REST_BUNDLE_APPLICATIONS = "/bundle/applications";
	public static final String REST_BUNDLE_NAMES = "/bundle/names";

	String value;

}
