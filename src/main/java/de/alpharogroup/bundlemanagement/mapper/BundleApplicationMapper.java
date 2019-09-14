package de.alpharogroup.bundlemanagement.mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.BundleApplications;
import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.mapstruct.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface BundleApplicationMapper extends GenericMapper<BundleApplications,BundleApplication>
{
}