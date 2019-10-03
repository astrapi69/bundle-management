package de.alpharogroup.bundlemanagement.mapper;

import de.alpharogroup.bundlemanagement.viewmodel.BaseName;
import org.mapstruct.Mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.BaseNames;
import de.alpharogroup.mapstruct.mapper.GenericMapper;

@Mapper
public interface BaseNameMapper extends GenericMapper<BaseNames, BaseName>
{
}