package de.alpharogroup.bundlemanagement.mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.PropertiesKeys;
import de.alpharogroup.db.resource.bundles.domain.PropertiesKey;
import de.alpharogroup.mapstruct.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface PropertiesKeyMapper extends GenericMapper<PropertiesKeys,PropertiesKey>
{
}