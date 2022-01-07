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
	VERSION(AppRestPath.REST_VERSION),
	RESOURCE_BUNDLES(AppRestPath.REST_RESOURCE_BUNDLES),
	COUNTRIES(AppRestPath.REST_COUNTRIES),
	LANGUAGES(AppRestPath.REST_LANGUAGES),
	LANGUAGE_LOCALES(AppRestPath.REST_LANGUAGE_LOCALES),
	BUNDLE_APPLICATIONS(AppRestPath.REST_BUNDLE_APPLICATIONS),
	BUNDLE_NAMES(AppRestPath.REST_BUNDLE_NAMES);

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
