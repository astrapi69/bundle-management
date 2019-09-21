package de.alpharogroup.bundlemanagement.mapper;

import org.mapstruct.Mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.BundleApplications;
import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.mapstruct.mapper.GenericMapper;

@Mapper
public interface BundleApplicationMapper
	extends
		GenericMapper<BundleApplications, BundleApplication>
{
}