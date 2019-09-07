package de.alpharogroup.bundlemanagement.mapper;

public interface GenericMapper<ENTITY, DTO>
{

    DTO toDto(ENTITY entity);

    ENTITY toEntity(DTO dto);

}