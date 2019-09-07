package de.alpharogroup.bundlemanagement.mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.BaseNames;
import de.alpharogroup.db.resource.bundles.domain.BaseName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest
{
	@Autowired
	BaseNameMapper baseNameMapper;
	@Autowired
	BundleApplicationMapper bundleApplicationMapper;
	@Autowired
	BundleNameMapper bundleNameMapper;
	@Autowired
	LanguageLocaleMapper languageLocaleMapper;
	@Autowired
	PropertiesKeyMapper propertiesKeyMapper;
	@Autowired
	PropertiesValueMapper propertiesValueMapper;
	@Autowired
	ResourcebundleMapper resourcebundleMapper;

	@Test
	public void testToDto()
	{
		BaseNames baseNames = BaseNames.builder().build();
		BaseName baseName = baseNameMapper.toDto(baseNames);
		assertNotNull(baseName);
		BaseNames baseNameEntity = baseNameMapper.toEntity(baseName);
		assertEquals(baseNames, baseNameEntity);
	}
}