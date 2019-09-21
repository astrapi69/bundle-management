package de.alpharogroup.bundlemanagement.mapper;

import org.mapstruct.Mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.Resourcebundles;
import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.mapstruct.mapper.GenericMapper;

@Mapper
public interface ResourcebundleMapper extends GenericMapper<Resourcebundles, Resourcebundle>
{
}