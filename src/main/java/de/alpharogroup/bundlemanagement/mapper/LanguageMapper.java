package de.alpharogroup.bundlemanagement.mapper;

import de.alpharogroup.bundlemanagement.jpa.entity.Languages;
import de.alpharogroup.db.resource.bundles.domain.Language;
import de.alpharogroup.mapstruct.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface LanguageMapper extends GenericMapper<Languages, Language>
{
}