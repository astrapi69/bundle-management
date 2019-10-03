package de.alpharogroup.bundlemanagement.mapper;

import de.alpharogroup.bundlemanagement.viewmodel.Resourcebundle;
import org.mapstruct.Mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.Resourcebundles;
import de.alpharogroup.mapstruct.mapper.GenericMapper;

@Mapper
public interface ResourcebundleMapper extends GenericMapper<Resourcebundles, Resourcebundle>
{
}