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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import de.alpharogroup.db.entity.BaseEntity;
import de.alpharogroup.db.entity.DatabaseAttribute;
import de.alpharogroup.db.entity.name.versionable.VersionableNameEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The entity class {@link PropertiesValues} holds the data only for the properties values. <br>
 * <br>
 */
@Entity
@Table(name = PropertiesValues.TABLE_NAME)
@Getter
@Setter
@ToString
@NoArgsConstructor
@SequenceGenerator(name =
	BaseEntity.SEQUENCE_GENERIC_GENERATOR_NAME, sequenceName =
	DatabaseAttribute.SEQUENCE_PREFIX
		+ PropertiesValues.TABLE_NAME, allocationSize = 1)
public class PropertiesValues extends VersionableNameEntity<Integer> implements Cloneable
{

	public static final String TABLE_NAME = "properties_values";
	/** Serial Version UID */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link PropertiesValues} entity object.
	 *
	 * @param name
	 *            the name
	 */
	@Builder
	PropertiesValues(String name)
	{
		super(name);
	}

}