package de.alpharogroup.bundlemanagement.mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.BundleNames;
import de.alpharogroup.db.resource.bundles.domain.BundleName;
import de.alpharogroup.mapstruct.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface BundleNameMapper extends GenericMapper<BundleNames,BundleName>
{
}