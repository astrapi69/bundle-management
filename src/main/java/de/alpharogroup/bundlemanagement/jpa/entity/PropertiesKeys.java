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
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import de.alpharogroup.db.entity.BaseEntity;
import de.alpharogroup.db.entity.enums.DatabasePrefix;
import de.alpharogroup.db.entity.name.NameEntity;
import de.alpharogroup.db.entity.name.versionable.VersionableNameEntity;
import de.alpharogroup.hibernate.generator.IdentifiableSequenceStyleGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The entity class {@link PropertiesKeys} holds the data only for the properties keys not the
 * values. <br>
 * <br>
 * Note: The values of the properties keys is in the entity class {@link PropertiesValues}.
 */
@Entity
@Table(name = PropertiesKeys.TABLE_NAME, indexes = { @Index(name = DatabasePrefix.INDEX_PREFIX
	+ PropertiesKeys.TABLE_NAME + DatabasePrefix.UNDERSCORE_PREFIX
	+ NameEntity.COLUMN_NAME_NAME, columnList = NameEntity.COLUMN_NAME_NAME) })
@Getter
@Setter
@ToString
@NoArgsConstructor
@GenericGenerator(name = BaseEntity.SEQUENCE_GENERIC_GENERATOR_NAME, strategy = IdentifiableSequenceStyleGenerator.STRATEGY_CLASS_NAME, parameters = @Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = DatabasePrefix.SEQUENCE_GENERATOR_PREFIX
	+ PropertiesKeys.TABLE_NAME))
public class PropertiesKeys extends VersionableNameEntity<Integer> implements Cloneable
{

	/** Serial Version UID */
	private static final long serialVersionUID = 1L;
	public static final String TABLE_NAME = "properties_keys";

	/**
	 * Instantiates a new {@link PropertiesKeys} entity object.
	 *
	 * @param name
	 *            the name
	 */
	@Builder
	PropertiesKeys(String name)
	{
		super(name);
	}

}