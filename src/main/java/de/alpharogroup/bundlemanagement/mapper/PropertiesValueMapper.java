package de.alpharogroup.bundlemanagement.mapper;

import de.alpharogroup.bundlemanagement.viewmodel.PropertiesValue;
import org.mapstruct.Mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.PropertiesValues;
import de.alpharogroup.mapstruct.mapper.GenericMapper;

@Mapper
public interface PropertiesValueMapper extends GenericMapper<PropertiesValues, PropertiesValue>
{
}