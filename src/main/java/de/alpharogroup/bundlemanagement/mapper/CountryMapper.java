package de.alpharogroup.bundlemanagement.mapper;

import de.alpharogroup.bundlemanagement.viewmodel.Country;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import de.alpharogroup.bundlemanagement.jpa.entity.Countries;
import de.alpharogroup.mapstruct.mapper.GenericMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CountryMapper extends GenericMapper<Countries, Country>
{
}