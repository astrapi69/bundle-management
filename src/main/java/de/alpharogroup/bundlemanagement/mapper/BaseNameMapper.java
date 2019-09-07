package de.alpharogroup.bundlemanagement.mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.BaseNames;
import de.alpharogroup.db.resource.bundles.domain.BaseName;
import org.mapstruct.Mapper;

@Mapper
public interface BaseNameMapper extends GenericMapper<BaseNames,BaseName>
{
}