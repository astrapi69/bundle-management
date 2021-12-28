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
	/** The constant for the action save or update */
	SAVE_OR_UPDATE(ActionRestPath.ACTION_SAVE_OR_UPDATE),
	/** The constant for the action delete */
	DELETE(ActionRestPath.ACTION_DELETE),
	/** The constant for the action find */
	FIND(ActionRestPath.ACTION_FIND),
	/** The constant for the action find all */
	FIND_ALL(ActionRestPath.ACTION_FIND_ALL),
	/** The constant for the action find by */
	FIND_BY(ActionRestPath.ACTION_FIND_BY),
	/** The constant for the action find by name */
	FIND_BY_NAME(ActionRestPath.ACTION_FIND_BY_NAME);

	public static final String ACTION_SAVE = "/save";
	public static final String ACTION_UPDATE = "/update";
	public static final String ACTION_SAVE_OR_UPDATE = ACTION_SAVE + "/or" + ACTION_UPDATE;
	public static final String ACTION_DELETE = "/delete";
	public static final String ACTION_PERSIST = "/persist";
	public static final String ACTION_VALUE = "/value";
	public static final String ACTION_VALUE_WITH_PARAMETERS = ActionRestPath.ACTION_VALUE + "/with/parameters";

	public static final String ACTION_PROPERTIES = "/properties";

	public static final String ACTION_RESOURCE_BUNDLES = "/resourcebundles";
	public static final String ACTION_UPDATE_BUNDLENAME = ACTION_SAVE + "/bundlename";
	public static final String ACTION_FIND = "/find";
	public static final String ACTION_FIND_ALL = ACTION_FIND + "/all";
	public static final String ACTION_FIND_ALL_BUNDLE_NAMES = ACTION_FIND_ALL + "/bundlenames";
	public static final String ACTION_FIND_BY = ACTION_FIND + "/by";
	public static final String ACTION_FIND_BY_NAME = ACTION_FIND_BY + "/name";
	public static final String ACTION_FIND_BY_CODE = ACTION_FIND_BY + "/code";
	public static final String ACTION_FIND_BY_BUNDLE_NAME = ACTION_FIND_BY + "/bundlename";

	String value;
}
