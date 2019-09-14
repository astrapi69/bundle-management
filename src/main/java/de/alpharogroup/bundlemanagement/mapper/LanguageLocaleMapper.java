package de.alpharogroup.bundlemanagement.mapper;

import org.mapstruct.Mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.LanguageLocales;
import de.alpharogroup.db.resource.bundles.domain.LanguageLocale;
import de.alpharogroup.mapstruct.mapper.GenericMapper;

@Mapper
public interface LanguageLocaleMapper extends GenericMapper<LanguageLocales,LanguageLocale>
{
}