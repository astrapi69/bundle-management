package de.alpharogroup.bundlemanagement.mapper;

import de.alpharogroup.bundlemanagement.viewmodel.BundleApplication;
import org.mapstruct.Mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.BundleApplications;
import de.alpharogroup.mapstruct.mapper.GenericMapper;

@Mapper
public interface BundleApplicationMapper
	extends
		GenericMapper<BundleApplications, BundleApplication>
{
}