package de.alpharogroup.bundlemanagement.mapper;

import org.mapstruct.Mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.PropertiesValues;
import de.alpharogroup.db.resource.bundles.domain.PropertiesValue;
import de.alpharogroup.mapstruct.mapper.GenericMapper;

@Mapper
public interface PropertiesValueMapper extends GenericMapper<PropertiesValues, PropertiesValue>
{
}