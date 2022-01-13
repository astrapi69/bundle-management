package io.github.astrapi69.bundlemanagement.service;

import io.github.astrapi69.bundlemanagement.jpa.entity.PropertiesKeyParts;
import io.github.astrapi69.resourcebundle.properties.PropertiesKeyExtensions;

import java.util.ArrayList;
import java.util.List;

public final class PropertiesKeyPartsExtensions
{

	public static List<PropertiesKeyParts> build(String value) {
		PropertiesKeyParts parent = null;
		List<PropertiesKeyParts> propertiesKeyParts = new ArrayList<>();
		String[] keyParts = PropertiesKeyExtensions.getKeyParts(value);
		for (int i = 0; i < keyParts.length; i++)
		{
			String keyPart = keyParts[i];
			if (i == 0)
			{
				PropertiesKeyParts root = PropertiesKeyParts.builder().parent(null).depth(i)
					.node(true).value(keyPart).build();
				propertiesKeyParts.add(root);
				parent = root;
				continue;
			} else {
				PropertiesKeyParts propertiesKeyPart = PropertiesKeyParts.builder().parent(parent)
					.depth(i).node(true).value(keyPart).build();
				propertiesKeyParts.add(propertiesKeyPart);
				parent = propertiesKeyPart;
			}
		}
		return propertiesKeyParts;
	}

	public static PropertiesKeyParts buildPropertiesKeyPartsTree(String value) {
		PropertiesKeyParts parent = null;
		List<PropertiesKeyParts> propertiesKeyParts = new ArrayList<>();
		String[] keyParts = PropertiesKeyExtensions.getKeyParts(value);
		for (int i = 0; i < keyParts.length; i++)
		{
			String keyPart = keyParts[i];
			if (i == 0)
			{
				PropertiesKeyParts root = PropertiesKeyParts.builder().parent(null).depth(i)
					.node(true).value(keyPart).build();
				propertiesKeyParts.add(root);
				parent = root;
				continue;
			} else {
				PropertiesKeyParts propertiesKeyPart = PropertiesKeyParts.builder().parent(parent)
					.depth(i).node(true).value(keyPart).build();
				propertiesKeyParts.add(propertiesKeyPart);
				parent = propertiesKeyPart;
			}
		}
		return parent;
	}
}
