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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import de.alpharogroup.db.entity.name.UniqueNameEntity;
import de.alpharogroup.db.entity.text.UniqueTextEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The entity class {@link Countries} is keeping the information for all countries in the world
 */
@Entity
@Table(name = "countries")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@NoArgsConstructor
public class Countries extends UniqueNameEntity<Integer> implements Cloneable
{

	/** The serial Version UID. */
	private static final long serialVersionUID = 1L;
	/** The iso3166 name with two characters. */
	@Column(name = "iso3166_a2name", length = 2)
	private String iso3166A2name;

	/**
	 * Instantiates a new countries.
	 *
	 * @param name
	 *            the name
	 * @param iso3166a2name
	 *            the iso 3166 a 2 name
	 */
	@Builder
	public Countries(String name, String iso3166a2name)
	{
		super(name);
		iso3166A2name = iso3166a2name;
	}

}