package de.alpharogroup.bundlemanagement.mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.PropertiesKeys;
import de.alpharogroup.db.resource.bundles.domain.PropertiesKey;
import org.mapstruct.Mapper;

@Mapper
public interface PropertiesKeyMapper
{

    PropertiesKey toDto(PropertiesKeys entity);

    PropertiesKeys toEntity(PropertiesKey dto);

}