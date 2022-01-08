package io.github.astrapi69.bundlemanagement.jpa.repository;

import io.github.astrapi69.bundlemanagement.integration.AbstractIntegrationTest;
import io.github.astrapi69.bundlemanagement.jpa.entity.PropertiesKeyParts;
import io.github.astrapi69.resourcebundle.properties.PropertiesKeyExtensions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PropertiesKeyPartsRepositoryTest extends AbstractIntegrationTest
{

	@Autowired
	PropertiesKeyPartsRepository repository;

	@Test
	public void whenFindByValue()
	{
		String value;
		value = "foo";
		PropertiesKeyParts root = PropertiesKeyParts.builder()
			.parent(null)
			.depth(0)
			.node(true)
			.value(value)
			.build();

		value = "bar";
		PropertiesKeyParts foobar = PropertiesKeyParts.builder()
			.parent(root)
			.value(value)
			.node(true)
			.depth(1)
			.build();

		PropertiesKeyParts savedRoot = repository.save(root);
		repository.save(foobar);

		List<PropertiesKeyParts> byValue = repository.findByValue(value);
		assertNotNull(byValue);
		assertEquals(1, byValue.size());

		PropertiesKeyParts propertiesKeyParts = byValue.get(0);
		PropertiesKeyParts parent = propertiesKeyParts.getParent();
		assertEquals(savedRoot, parent);

		assertEquals(savedRoot, parent);

		List<String> ancestry = repository.findAncestry(propertiesKeyParts.getId());

		String concatenate = PropertiesKeyExtensions.concatenate(ancestry);
		assertEquals("foo.bar", concatenate);

		value = "key";
		PropertiesKeyParts foobarkey = PropertiesKeyParts.builder()
			.parent(foobar)
			.value(value)
			.node(true)
			.depth(2)
			.build();


		PropertiesKeyParts keyParts = repository.save(foobarkey);

		ancestry = repository.findAncestry(keyParts.getId());

		concatenate = PropertiesKeyExtensions.concatenate(ancestry);
		assertEquals("foo.bar.key", concatenate);


		List<PropertiesKeyParts> byDepthAndValue = repository.findByDepthAndValue(2, value);
		assertNotNull(byDepthAndValue);
		assertEquals(1, byDepthAndValue.size());

		PropertiesKeyParts propertiesKeyParts1 = byDepthAndValue.get(0);
		assertEquals(keyParts, propertiesKeyParts1);

		List<PropertiesKeyParts> foos = repository.findByDepthAndValueAndParent(1, "bar", savedRoot);
		assertNotNull(foos);
		assertEquals(1, foos.size());

		PropertiesKeyParts propertiesKeyParts2 = foos.get(0);
		assertEquals(propertiesKeyParts, propertiesKeyParts2);

		Optional<PropertiesKeyParts> foo = repository.findRootByValue("foo");
		assertNotNull(foo);
		assertTrue(foo.isPresent());
		assertEquals(savedRoot, foo.get());

	}

}
