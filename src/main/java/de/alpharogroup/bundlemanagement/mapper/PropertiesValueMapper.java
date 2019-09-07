package de.alpharogroup.bundlemanagement.mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.PropertiesValues;
import de.alpharogroup.db.resource.bundles.domain.PropertiesValue;
import org.mapstruct.Mapper;

@Mapper
public interface PropertiesValueMapper
{

    PropertiesValue toDto(PropertiesValues entity);

    PropertiesValues toEntity(PropertiesValue dto);

}