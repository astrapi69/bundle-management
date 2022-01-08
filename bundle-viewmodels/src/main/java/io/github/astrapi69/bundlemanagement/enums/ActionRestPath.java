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
public enum ActionRestPath
{
	/** The constant for the action save */
	SAVE(ActionRestPath.ACTION_SAVE),
	/** The constant for the action persist. */
	PERSIST(ActionRestPath.ACTION_PERSIST),
	/** The constant for the action update. */
	UPDATE(ActionRestPath.ACTION_UPDATE),
	/** The constant for the action save or update */
	SAVE_OR_UPDATE(ActionRestPath.ACTION_SAVE_OR_UPDATE),
	/** The constant for the action delete */
	DELETE(ActionRestPath.ACTION_DELETE),
	/** The constant for the action find */
	FIND(ActionRestPath.ACTION_FIND),
	/** The constant for the action find by */
	FIND_BY(ActionRestPath.ACTION_FIND_BY),
	/** The constant for the action find all */
	FIND_ALL(ActionRestPath.ACTION_FIND_ALL),
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
	/** The constant for the action find by name */
	FIND_BY_NAME(ActionRestPath.ACTION_FIND_BY_NAME),
	/** The constant for the action find by language code */
	FIND_BY_CODE(ActionRestPath.ACTION_FIND_BY_CODE),
	/** The constant for the action find by locale */
	FIND_BY_LOCALE(ActionRestPath.ACTION_FIND_BY_LOCALE),
	/** The constant for the action find by bundle name */
	FIND_BY_BUNDLE_NAME(ActionRestPath.ACTION_FIND_BY_BUNDLE_NAME);

	public static final String ACTION_SAVE = "/save";
	public static final String ACTION_PERSIST = "/persist";
	public static final String ACTION_UPDATE = "/update";
	public static final String ACTION_SAVE_OR_UPDATE = ACTION_SAVE + "/or" + ACTION_UPDATE;
	public static final String ACTION_DELETE = "/delete";
	public static final String ACTION_FIND = "/find";
	public static final String ACTION_FIND_BY = ACTION_FIND + "/by";
	public static final String ACTION_FIND_BY_NAME = ACTION_FIND_BY + "/name";
	public static final String ACTION_FIND_BY_CODE = ACTION_FIND_BY + "/code";
	public static final String ACTION_FIND_BY_LOCALE = ACTION_FIND_BY + "/locale";
	public static final String ACTION_FIND_BY_BUNDLE_NAME = ACTION_FIND_BY + "/bundlename";
	public static final String ACTION_FIND_ALL = ACTION_FIND + "/all";
	public static final String ACTION_FIND_ALL_BUNDLE_NAMES = ACTION_FIND_ALL + "/bundlenames";
	public static final String ACTION_VALUE = "/value";
	public static final String ACTION_VALUE_WITH_PARAMETERS = ActionRestPath.ACTION_VALUE
		+ "/with/parameters";
	public static final String ACTION_PROPERTIES = "/properties";
	public static final String ACTION_RESOURCE_BUNDLES = "/resourcebundles";
	public static final String ACTION_UPDATE_BUNDLENAME = ACTION_UPDATE + "/bundlename";
	String value;
}
