package de.alpharogroup.bundlemanagement.mapper;

import de.alpharogroup.bundlemanagement.viewmodel.BundleName;
import org.mapstruct.Mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.BundleNames;
import de.alpharogroup.mapstruct.mapper.GenericMapper;

@Mapper
public interface BundleNameMapper extends GenericMapper<BundleNames, BundleName>
{
}