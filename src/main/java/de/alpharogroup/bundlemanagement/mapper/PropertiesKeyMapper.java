package de.alpharogroup.bundlemanagement.mapper;

import org.mapstruct.Mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.PropertiesKeys;
import de.alpharogroup.db.resource.bundles.domain.PropertiesKey;
import de.alpharogroup.mapstruct.mapper.GenericMapper;

@Mapper
public interface PropertiesKeyMapper extends GenericMapper<PropertiesKeys, PropertiesKey>
{
}