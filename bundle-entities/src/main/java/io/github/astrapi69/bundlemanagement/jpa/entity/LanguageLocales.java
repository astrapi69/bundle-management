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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import io.github.astrapi69.entity.enums.DatabasePrefix;
import io.github.astrapi69.entity.versionable.VersionableUUIDEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * The entity class {@link LanguageLocales} holds the data for the locale as {@link String} object.
 */
@Entity
@Table(name = LanguageLocales.TABLE_NAME, uniqueConstraints = {
		@UniqueConstraint(name = DatabasePrefix.UNIQUE_CONSTRAINT_PREFIX
			+ LanguageLocales.TABLE_NAME + DatabasePrefix.UNDERSCORE
			+ LanguageLocales.COLUMN_NAME_LOCALE, columnNames = LanguageLocales.COLUMN_NAME_LOCALE) }, indexes = {
					@Index(name = DatabasePrefix.INDEX_PREFIX + LanguageLocales.TABLE_NAME
						+ DatabasePrefix.UNDERSCORE
						+ LanguageLocales.COLUMN_NAME_LOCALE, columnList = LanguageLocales.COLUMN_NAME_LOCALE) })
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LanguageLocales extends VersionableUUIDEntity implements Cloneable
{

	public static final String COLUMN_NAME_LOCALE = "locale";

	/** Serial Version UID */
	private static final long serialVersionUID = 1L;
	public static final String TABLE_NAME = "language_locales";

	/** The locale of this entry. */
	@Column(length = 64)
	String locale;

}