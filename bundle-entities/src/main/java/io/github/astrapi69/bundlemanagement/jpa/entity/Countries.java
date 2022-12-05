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
package io.github.astrapi69.bundlemanagement.jpa.entity;

import io.github.astrapi69.data.enumtype.DatabasePrefix;
import io.github.astrapi69.data.nameable.Nameable;
import io.github.astrapi69.entity.nameable.NameUUIDEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * The entity class {@link Countries} is keeping the information for all countries in the world
 */
@Entity
@Table(name = Countries.TABLE_NAME, uniqueConstraints = {
		@UniqueConstraint(name = DatabasePrefix.UNIQUE_CONSTRAINT_PREFIX + Countries.TABLE_NAME
			+ DatabasePrefix.UNDERSCORE
			+ Nameable.COLUMN_NAME_NAME, columnNames = Nameable.COLUMN_NAME_NAME),
		@UniqueConstraint(name = DatabasePrefix.UNIQUE_CONSTRAINT_PREFIX + Countries.TABLE_NAME
			+ DatabasePrefix.UNDERSCORE
			+ Countries.COLUMN_NAME_ISO_3166_A2_NAME, columnNames = Countries.COLUMN_NAME_ISO_3166_A2_NAME) }, indexes = {
					@Index(name = DatabasePrefix.INDEX_PREFIX + Countries.TABLE_NAME
						+ DatabasePrefix.UNDERSCORE
						+ Nameable.COLUMN_NAME_NAME, columnList = Nameable.COLUMN_NAME_NAME),
					@Index(name = DatabasePrefix.INDEX_PREFIX + Countries.TABLE_NAME
						+ DatabasePrefix.UNDERSCORE
						+ Countries.COLUMN_NAME_ISO_3166_A2_NAME, columnList = Countries.COLUMN_NAME_ISO_3166_A2_NAME) })
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class Countries extends NameUUIDEntity implements Cloneable
{

	public static final String COLUMN_NAME_ISO_3166_A2_NAME = "iso3166_a2name";
	public static final String TABLE_NAME = "countries";
	/** The serial Version UID. */
	private static final long serialVersionUID = 1L;
	/** The iso3166 name with two characters. */
	@Column(name = "iso3166_a2name", length = 2)
	String iso3166a2name;

	public Countries(String name)
	{
		super(name);
	}
}
