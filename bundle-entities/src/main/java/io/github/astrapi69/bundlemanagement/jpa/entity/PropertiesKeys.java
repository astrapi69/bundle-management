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

import io.github.astrapi69.data.enums.DatabasePrefix;
import io.github.astrapi69.data.nameable.Nameable;
import io.github.astrapi69.entity.nameable.versionable.VersionableNameUUIDEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * The entity class {@link PropertiesKeys} holds the data only for the properties keys not the
 * values. <br>
 * <br>
 * Note: The values of the properties keys is in the entity class {@link PropertiesValues}.
 */
@Entity
@Table(name = PropertiesKeys.TABLE_NAME, indexes = { @Index(name = DatabasePrefix.INDEX_PREFIX
	+ PropertiesKeys.TABLE_NAME + DatabasePrefix.UNDERSCORE
	+ Nameable.COLUMN_NAME_NAME, columnList = Nameable.COLUMN_NAME_NAME) })
@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder
public class PropertiesKeys extends VersionableNameUUIDEntity implements Cloneable
{

	public static final String TABLE_NAME = "properties_keys";
	/** Serial Version UID */
	private static final long serialVersionUID = 1L;

}
