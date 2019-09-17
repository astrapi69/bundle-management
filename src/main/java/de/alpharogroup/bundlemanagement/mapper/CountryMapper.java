package de.alpharogroup.bundlemanagement.mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.Countries;
import de.alpharogroup.db.resource.bundles.domain.Country;
import de.alpharogroup.mapstruct.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CountryMapper extends GenericMapper<Countries, Country>
{
}