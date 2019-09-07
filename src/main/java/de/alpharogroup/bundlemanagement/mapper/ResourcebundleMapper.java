package de.alpharogroup.bundlemanagement.mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.Resourcebundles;
import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import org.mapstruct.Mapper;

@Mapper
public interface ResourcebundleMapper
{

    Resourcebundle toDto(Resourcebundles entity);

    Resourcebundles toEntity(Resourcebundle dto);

}