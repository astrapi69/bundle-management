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
package io.github.astrapi69.bundlemanagement.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import io.github.astrapi69.bundlemanagement.jpa.entity.PropertiesKeyParts;
import io.github.astrapi69.bundlemanagement.jpa.repository.PropertiesKeyPartsRepository;
import io.github.astrapi69.resourcebundle.properties.PropertiesKeyExtensions;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import io.github.astrapi69.bundlemanagement.jpa.entity.PropertiesKeys;
import io.github.astrapi69.bundlemanagement.jpa.repository.PropertiesKeysRepository;
import io.github.astrapi69.spring.service.api.GenericService;
import io.github.astrapi69.spring.service.api.NameEntityService;

/**
 * The class {@link PropertiesKeyPartsService}.
 */
@Transactional
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class PropertiesKeyPartsService
	implements
		GenericService<PropertiesKeyParts, UUID, PropertiesKeyPartsRepository>
{

	PropertiesKeyPartsRepository repository;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public PropertiesKeyParts getOrCreate(String value)
	{
		PropertiesKeyParts parent = null;
		String[] keyParts = PropertiesKeyExtensions.getKeyParts(value);
		for (int i = 0; i < keyParts.length; i++)
		{
			String keyPart = keyParts[i];
			if(i == 0) {
				Optional<PropertiesKeyParts> rootByValue = repository.findRootByValue(keyPart);
				if(rootByValue.isPresent()){
					parent = rootByValue.get();
				} else {
					PropertiesKeyParts root = PropertiesKeyParts.builder()
						.parent(null)
						.depth(i)
						.node(true)
						.value(keyPart)
						.build();
					parent = repository.save(root);
				}
				continue;
			}

			List<PropertiesKeyParts> byDepthAndValueAndParent = repository.findByDepthAndValueAndParent(
				i, keyPart, parent);
			if(!byDepthAndValueAndParent.isEmpty()) {
				parent = byDepthAndValueAndParent.get(0);
			} else {
				PropertiesKeyParts propertiesKeyParts = PropertiesKeyParts.builder()
					.parent(parent)
					.depth(i)
					.node(true)
					.value(keyPart)
					.build();
				parent = repository.save(propertiesKeyParts);
			}
		}
		return parent;
	}

	public PropertiesKeyParts merge(PropertiesKeyParts object)
	{
		return repository.save(object);
	}

}
