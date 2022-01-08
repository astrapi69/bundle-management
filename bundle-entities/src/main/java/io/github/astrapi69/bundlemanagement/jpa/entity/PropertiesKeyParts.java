package io.github.astrapi69.bundlemanagement.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import io.github.astrapi69.entity.treeable.TreeableUUIDEntity;

@Entity
@Table(name = PropertiesKeyParts.TABLE_NAME)
@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder
public class PropertiesKeyParts extends TreeableUUIDEntity<String, PropertiesKeyParts>
{
	/** Serial Version UID */
	private static final long serialVersionUID = 1L;
	public static final String TABLE_NAME = "properties_key_parts";
}
