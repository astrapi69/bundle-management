package de.alpharogroup.bundlemanagement.mapper;

import de.alpharogroup.bundlemanagement.viewmodel.LanguageLocale;
import org.mapstruct.Mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.LanguageLocales;
import de.alpharogroup.mapstruct.mapper.GenericMapper;

@Mapper
public interface LanguageLocaleMapper extends GenericMapper<LanguageLocales, LanguageLocale>
{
}