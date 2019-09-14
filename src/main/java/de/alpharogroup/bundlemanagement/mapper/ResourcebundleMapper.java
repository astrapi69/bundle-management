package de.alpharogroup.bundlemanagement.mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.Resourcebundles;
import de.alpharogroup.db.resource.bundles.domain.Resourcebundle;
import de.alpharogroup.mapstruct.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface ResourcebundleMapper extends GenericMapper<Resourcebundles,Resourcebundle>
{
}