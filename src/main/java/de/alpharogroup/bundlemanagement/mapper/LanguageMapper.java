package de.alpharogroup.bundlemanagement.mapper;

import org.mapstruct.Mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.Languages;
import de.alpharogroup.db.resource.bundles.domain.Language;
import de.alpharogroup.mapstruct.mapper.GenericMapper;

@Mapper
public interface LanguageMapper extends GenericMapper<Languages, Language>
{
}