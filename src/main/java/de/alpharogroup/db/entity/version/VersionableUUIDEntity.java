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
package de.alpharogroup.db.entity.version;

import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import de.alpharogroup.db.entity.uniqueable.UUIDEntity;
import de.alpharogroup.db.entity.uniqueable.UniqueableEntity;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The abstract class {@link VersionableUUIDEntity} is a concrete class of {@link UniqueableEntity} and holds
 * an {@link UUID} as primary key.
 */
@MappedSuperclass
@Access(AccessType.FIELD)
@NoArgsConstructor
@SuperBuilder
public abstract class VersionableUUIDEntity extends UUIDEntity
{
	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The version property for the optimistic lock value
	 **/
	@Version
	Integer version;
}