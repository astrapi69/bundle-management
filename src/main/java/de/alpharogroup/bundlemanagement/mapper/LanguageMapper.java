package de.alpharogroup.bundlemanagement.mapper;

import de.alpharogroup.bundlemanagement.viewmodel.Language;
import org.mapstruct.Mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.Languages;
import de.alpharogroup.mapstruct.mapper.GenericMapper;

@Mapper
public interface LanguageMapper extends GenericMapper<Languages, Language>
{
}