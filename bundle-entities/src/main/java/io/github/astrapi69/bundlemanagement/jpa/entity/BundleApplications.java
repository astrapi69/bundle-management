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
package io.github.astrapi69.bundlemanagement.jpa.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import io.github.astrapi69.data.enums.DatabasePrefix;
import io.github.astrapi69.data.nameable.Nameable;
import io.github.astrapi69.entity.nameable.versionable.VersionableNameUUIDEntity;

/**
 * The entity class {@link BundleApplications} is the root of every bundle application. Every entity
 * of type {@link BundleNames} has as reference to a {@link BundleApplications}. Every entity class
 * of {@link BundleApplications} has exactly one default locale that is mandatory. If you see it
 * from the properties file view it is the default properties file is the properties file without
 * the locale suffix. The entity class {@link BundleApplications} acts as the manager of a
 * corresponding real application like a web application or an android-app. If the real application
 * supports only one locale the default locale is enough but if the real application supports more
 * than one locale object then the supported locale objects are kept in the list
 * 'supportedLocales'.<br>
 * Note: The supported locale objects for a real application are mandatory as the default locale.
 */
@Entity
@NamedQueries({
		@NamedQuery(name = BundleApplications.NQ_FIND_ALL_BY_LANGUAGE_LOCALE, query = "select ba from BundleApplications ba where :languageLocale member of ba.supportedLocales"),
		@NamedQuery(name = BundleApplications.NQ_FIND_ALL_BY_LANGUAGE_LOCALE_WITH_INNER_JOIN, query = "select ba from BundleApplications ba inner join ba.supportedLocales sl where sl.id = :languageLocaleId") })

@Table(name = BundleApplications.TABLE_NAME, uniqueConstraints = {
		@UniqueConstraint(name = DatabasePrefix.UNIQUE_CONSTRAINT_PREFIX
			+ BundleApplications.TABLE_NAME + DatabasePrefix.UNDERSCORE
			+ Nameable.COLUMN_NAME_NAME, columnNames = Nameable.COLUMN_NAME_NAME) }, indexes = {
					@Index(name = DatabasePrefix.INDEX_PREFIX + BundleApplications.TABLE_NAME
						+ DatabasePrefix.UNDERSCORE
						+ Nameable.COLUMN_NAME_NAME, columnList = Nameable.COLUMN_NAME_NAME, unique = true) })
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class BundleApplications extends VersionableNameUUIDEntity implements Cloneable
{

	/** The Constant BASE_BUNDLE_APPLICATION is the base name of the initial bundle application. */
	public static final String BASE_BUNDLE_APPLICATION = "base-bundle-application";
	public static final String NQ_FIND_ALL_BY_LANGUAGE_LOCALE_WITH_INNER_JOIN = "BundleApplications."
		+ "findAllByLanguageLocaleWithInnerJoin";

	public static final String NQ_FIND_ALL_BY_LANGUAGE_LOCALE = "BundleApplications."
		+ "findAllByLanguageLocale";
	public static final String TABLE_NAME = "bundle_applications";
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/**
	 * The default locale of this bundle application.
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "default_locale_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_bundle_applications_default_locale_id"))
	LanguageLocales defaultLocale;

	/**
	 * The supported locale objects that are mandatory for this bundle application.
	 */
	@Builder.Default
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinTable(name = "bundle_application_language_locales", joinColumns = {
			@JoinColumn(name = "application_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_bundle_application_id")) }, inverseJoinColumns = {
					@JoinColumn(name = "language_locales_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_bundle_application_language_locales_id")) })
	Set<LanguageLocales> supportedLocales = new HashSet<>();

	public BundleApplications(String name)
	{
		super(name);
	}

	public BundleApplications(String name, Integer version)
	{
		super(name);
		setVersion(version);
	}

	/**
	 * Adds the given {@link LanguageLocales} to the supported language locales.
	 *
	 * @param supportedLocale
	 *            the supported locale to add
	 * @return true, if successful
	 */
	public boolean addSupportedLanguageLocale(LanguageLocales supportedLocale)
	{
		if (supportedLocales == null)
		{
			supportedLocales = new HashSet<>();
		}
		return supportedLocales.add(supportedLocale);
	}

	/**
	 * Checks if the given {@link LanguageLocales} is supported
	 *
	 * @param supportedLocale
	 *            the supported locale
	 * @return true, if the given {@link LanguageLocales} is supported otherwise false
	 */
	public boolean isSupported(LanguageLocales supportedLocale)
	{
		return supportedLocales.contains(supportedLocale);
	}

	/**
	 * Removes the supported language locale.
	 *
	 * @param supportedLocale
	 *            the supported locale
	 * @return true, if successful
	 */
	public boolean removeSupportedLanguageLocale(LanguageLocales supportedLocale)
	{
		if (supportedLocales == null)
		{
			supportedLocales = new HashSet<>();
			return false;
		}
		return supportedLocales.remove(supportedLocale);
	}

}
