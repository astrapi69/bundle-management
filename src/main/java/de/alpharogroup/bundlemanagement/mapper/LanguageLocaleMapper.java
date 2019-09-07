package de.alpharogroup.bundlemanagement.mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.LanguageLocales;
import de.alpharogroup.db.resource.bundles.domain.LanguageLocale;
import org.mapstruct.Mapper;

@Mapper
public interface LanguageLocaleMapper extends GenericMapper<LanguageLocales,LanguageLocale>
{
}