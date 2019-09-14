package de.alpharogroup.bundlemanagement.mapper;

import org.mapstruct.Mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.BaseNames;
import de.alpharogroup.db.resource.bundles.domain.BaseName;
import de.alpharogroup.mapstruct.mapper.GenericMapper;

@Mapper
public interface BaseNameMapper extends GenericMapper<BaseNames,BaseName>
{
}