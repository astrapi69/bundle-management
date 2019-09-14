package de.alpharogroup.bundlemanagement.mapper;

import org.mapstruct.Mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.BundleNames;
import de.alpharogroup.db.resource.bundles.domain.BundleName;
import de.alpharogroup.mapstruct.mapper.GenericMapper;

@Mapper
public interface BundleNameMapper extends GenericMapper<BundleNames,BundleName>
{
}