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
import io.github.astrapi69.entity.versionable.VersionableUUIDEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The entity class {@link Resourcebundles} holds the all data of the values of the resource
 * bundles.
 */
@Entity
@Table(name = Resourcebundles.TABLE_NAME, indexes = { @Index(name = DatabasePrefix.INDEX_PREFIX
	+ Resourcebundles.TABLE_NAME + DatabasePrefix.UNDERSCORE
	+ Resourcebundles.COLUMN_NAME_BUNDLE_NAME, columnList = Resourcebundles.COLUMN_NAME_BUNDLE_NAME),
		@Index(name = DatabasePrefix.INDEX_PREFIX + Resourcebundles.TABLE_NAME
			+ DatabasePrefix.UNDERSCORE
			+ Resourcebundles.COLUMN_NAME_PROPRERTIES_KEY, columnList = Resourcebundles.COLUMN_NAME_PROPRERTIES_KEY),
		@Index(name = DatabasePrefix.INDEX_PREFIX + Resourcebundles.TABLE_NAME
			+ DatabasePrefix.UNDERSCORE
			+ Resourcebundles.COLUMN_NAME_PROPRERTIES_VALUE, columnList = Resourcebundles.COLUMN_NAME_PROPRERTIES_VALUE) })
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class Resourcebundles extends VersionableUUIDEntity implements Cloneable
{

	public static final String COLUMN_NAME_BUNDLE_NAME = "bundlename_id";
	public static final String COLUMN_NAME_PROPRERTIES_KEY = "properties_key_id";
	public static final String COLUMN_NAME_PROPRERTIES_VALUE = "properties_value_id";
	public static final String TABLE_NAME = "resourcebundles";
	/** Serial Version UID */
	private static final long serialVersionUID = 1L;
	/** The bundleName from this {@link Resourcebundles} object. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bundlename_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_resourcebundles_bundlename_id"))
	BundleNames bundleName;

	/** The properties key from this {@link Resourcebundles} object. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "properties_key_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_resourcebundles_properties_key_id"))
	PropertiesKeys key;

	/** The value for the properties key. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "properties_value_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_resourcebundles_properties_value_id"))
	PropertiesValues value;

}
