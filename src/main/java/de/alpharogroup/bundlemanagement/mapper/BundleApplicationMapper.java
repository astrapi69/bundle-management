package de.alpharogroup.bundlemanagement.mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.BundleApplications;
import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import org.mapstruct.Mapper;

@Mapper
public interface BundleApplicationMapper
{

    BundleApplication toDto(BundleApplications entity);

    BundleApplications toEntity(BundleApplication dto);

}