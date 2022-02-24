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

import lombok.AccessLevel;
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
 * The entity class {@link LanguageLocales} holds the data for the languages.
 */
@Entity
@Table(name = Languages.TABLE_NAME, uniqueConstraints = {
		@UniqueConstraint(name = DatabasePrefix.UNIQUE_CONSTRAINT_PREFIX + Languages.TABLE_NAME
			+ DatabasePrefix.UNDERSCORE
			+ Nameable.COLUMN_NAME_NAME, columnNames = Nameable.COLUMN_NAME_NAME),
		@UniqueConstraint(name = DatabasePrefix.UNIQUE_CONSTRAINT_PREFIX + Languages.TABLE_NAME
			+ DatabasePrefix.UNDERSCORE
			+ Languages.COLUMN_NAME_ISO_639_DASH1, columnNames = Languages.COLUMN_NAME_ISO_639_DASH1) }, indexes = {
					@Index(name = DatabasePrefix.INDEX_PREFIX + Languages.TABLE_NAME
						+ DatabasePrefix.UNDERSCORE
						+ Nameable.COLUMN_NAME_NAME, columnList = Nameable.COLUMN_NAME_NAME),
					@Index(name = DatabasePrefix.INDEX_PREFIX + Languages.TABLE_NAME
						+ DatabasePrefix.UNDERSCORE
						+ Languages.COLUMN_NAME_ISO_639_DASH1, columnList = Languages.COLUMN_NAME_ISO_639_DASH1) })
@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class Languages extends VersionableNameUUIDEntity implements Cloneable
{

	public static final String COLUMN_NAME_ISO_639_DASH1 = "iso639_1";
	public static final String TABLE_NAME = "languages";
	/** Serial Version UID */
	private static final long serialVersionUID = 1L;
	/** The iso639_1 code with two characters. */
	@Column(name = "iso639_1", length = 2)
	String iso639Dash1;

	public Languages(String name)
	{
		super(name);
	}
}
