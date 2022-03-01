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
package io.github.astrapi69.bundlemanagement.enums;

import io.github.astrapi69.spring.rest.BaseActionRestPath;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ActionRestPath
{
	/** The constant for the action value */
	VALUE(ActionRestPath.ACTION_VALUE),
	/** The constant for the action value with parameters */
	VALUE_WITH_PARAMETERS(ActionRestPath.ACTION_VALUE_WITH_PARAMETERS),
	/** The constant for the action properties */
	PROPERTIES(ActionRestPath.ACTION_PROPERTIES),
	/** The constant for the action resource bundles */
	RESOURCE_BUNDLES(ActionRestPath.ACTION_RESOURCE_BUNDLES),
	/** The constant for the action update bundle name */
	UPDATE_BUNDLENAME(ActionRestPath.ACTION_UPDATE_BUNDLENAME),
	/** The constant for the action find all bundle names */
	FIND_ALL_BUNDLE_NAMES(ActionRestPath.ACTION_FIND_ALL_BUNDLE_NAMES),
	/** The constant for the action find by language code */
	FIND_BY_CODE(ActionRestPath.ACTION_FIND_BY_CODE),
	/** The constant for the action find by locale */
	FIND_BY_LOCALE(ActionRestPath.ACTION_FIND_BY_LOCALE),
	/** The constant for the action find by bundle name */
	FIND_BY_BUNDLE_NAME(ActionRestPath.ACTION_FIND_BY_BUNDLE_NAME);

	public static final String ACTION_FIND_BY_CODE = BaseActionRestPath.ACTION_FIND_BY + "/code";
	public static final String ACTION_FIND_BY_LOCALE = BaseActionRestPath.ACTION_FIND_BY
		+ "/locale";
	public static final String ACTION_FIND_BY_BUNDLE_NAME = BaseActionRestPath.ACTION_FIND_BY
		+ "/bundlename";
	public static final String ACTION_FIND_ALL_BUNDLE_NAMES = BaseActionRestPath.ACTION_FIND_ALL
		+ "/bundlenames";
	public static final String ACTION_VALUE = "/value";
	public static final String ACTION_VALUE_WITH_PARAMETERS = ActionRestPath.ACTION_VALUE
		+ "/with/parameters";
	public static final String ACTION_PROPERTIES = "/properties";
	public static final String ACTION_RESOURCE_BUNDLES = "/resourcebundles";
	public static final String ACTION_UPDATE_BUNDLENAME = BaseActionRestPath.ACTION_UPDATE
		+ "/bundlename";
	String value;
}
