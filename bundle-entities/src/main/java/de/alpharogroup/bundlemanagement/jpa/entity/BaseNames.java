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
package de.alpharogroup.bundlemanagement.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import de.alpharogroup.db.entity.enums.DatabasePrefix;
import de.alpharogroup.db.entity.name.NameEntity;
import de.alpharogroup.db.entity.name.versionable.VersionableNameUUIDEntity;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Entity class for saving in database base names of the resource bundles. The base name if you see
 * it from the properties file view the name of the properties file without the locale suffix.
 */
@Entity
@Table(name = BaseNames.TABLE_NAME, indexes = {
		@Index(name = DatabasePrefix.INDEX_PREFIX + BaseNames.TABLE_NAME + DatabasePrefix.UNDERSCORE
			+ NameEntity.COLUMN_NAME_NAME, columnList = NameEntity.COLUMN_NAME_NAME) })
@ToString(callSuper = true)
@NoArgsConstructor
@SuperBuilder
public class BaseNames extends VersionableNameUUIDEntity implements Cloneable
{

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "basenames";
}