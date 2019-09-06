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

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.alpharogroup.db.entity.version.VersionableBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The entity class {@link Resourcebundles} holds the all data of the values of the resource
 * bundles.
 */
@Entity
@Table(name = "resourcebundles")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Resourcebundles extends VersionableBaseEntity<Integer> implements Cloneable
{
	/** Serial Version UID */
	private static final long serialVersionUID = 1L;

	/** The bundleName from this {@link BundleNames} object. */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bundlename_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_resourcebundles_bundlename_id"))
	private BundleNames bundleName;

	/** The properties key from this {@link BundleNames} object. */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "properties_key_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_resourcebundles_properties_key_id"))
	private PropertiesKeys key;

	/** The value for the properties key. */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "properties_value_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_resourcebundles_properties_value_id"))
	private PropertiesValues value;

}