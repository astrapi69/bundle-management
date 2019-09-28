/**
 * The MIT License
 *
 * Copyright (C) 2007 - 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *  *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *  *
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import de.alpharogroup.db.entity.BaseEntity;
import de.alpharogroup.db.entity.enums.DatabasePrefix;
import de.alpharogroup.db.entity.name.versionable.VersionableNameEntity;
import de.alpharogroup.hibernate.generator.IdentifiableSequenceStyleGenerator;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Entity class for saving in database base names of the resource bundles. The base name if you see
 * it from the properties file view the name of the properties file without the locale suffix.
 */
@Entity
@Table(name = BaseNames.TABLE_NAME, indexes = {
	@Index(name = DatabasePrefix.INDEX_PREFIX + BaseNames.TABLE_NAME
		+ BaseNames.COLUMN_NAME_NAME, columnList = BaseNames.COLUMN_NAME_NAME)
})
@ToString(callSuper = true)
@NoArgsConstructor
@GenericGenerator(
	name = BaseEntity.SEQUENCE_GENERIC_GENERATOR_NAME,
	strategy = IdentifiableSequenceStyleGenerator.STRATEGY_CLASS_NAME,
	parameters = @Parameter(
		name = SequenceStyleGenerator.SEQUENCE_PARAM,
		value = DatabasePrefix.SEQUENCE_GENERATOR_PREFIX + BaseNames.TABLE_NAME
	)
)
public class BaseNames extends VersionableNameEntity<Integer> implements Cloneable
{

	public static final String TABLE_NAME = "basenames";

	public static final String COLUMN_NAME_NAME = "name";

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link BaseNames} entity object.
	 *
	 * @param name
	 *            the name
	 */
	@Builder
	BaseNames(String name)
	{
		super(name);
	}
}