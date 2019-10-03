package de.alpharogroup.bundlemanagement.mapper;

import de.alpharogroup.bundlemanagement.viewmodel.PropertiesKey;
import org.mapstruct.Mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.PropertiesKeys;
import de.alpharogroup.mapstruct.mapper.GenericMapper;

@Mapper
public interface PropertiesKeyMapper extends GenericMapper<PropertiesKeys, PropertiesKey>
{
}