package org.zerogravitysolutions.group.utils;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.zerogravitysolutions.group.GroupDto;
import org.zerogravitysolutions.group.GroupEntity;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapDtoToEntity(GroupDto source, @MappingTarget GroupEntity target);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapEntityToDto(GroupEntity source, @MappingTarget GroupDto target);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    GroupDto mapEntityToDto(GroupEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    GroupEntity mapDtoToEntityWithEntityReturnType(GroupDto source, @MappingTarget GroupEntity target);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    GroupDto mapsEntityToDto(GroupEntity source, @MappingTarget GroupDto target);
}
