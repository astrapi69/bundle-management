package de.alpharogroup.bundlemanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import de.alpharogroup.bundlemanagement.jpa.entity.Countries;
import de.alpharogroup.db.resource.bundles.domain.Country;
import de.alpharogroup.mapstruct.mapper.GenericMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CountryMapper extends GenericMapper<Countries, Country>
{
}