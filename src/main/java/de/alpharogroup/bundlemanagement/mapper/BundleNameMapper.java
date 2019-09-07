package de.alpharogroup.bundlemanagement.mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.BundleNames;
import de.alpharogroup.db.resource.bundles.domain.BundleName;
import org.mapstruct.Mapper;

@Mapper
public interface BundleNameMapper
{

    BundleName toDto(BundleNames entity);

    BundleNames toEntity(BundleName dto);

}