package org.zerogravitysolutions.subject.utils;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.zerogravitysolutions.subject.SubjectDto;
import org.zerogravitysolutions.subject.SubjectEntity;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapDtoToEntity(SubjectDto source, @MappingTarget SubjectEntity target);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapEntityToDto(SubjectEntity source, @MappingTarget SubjectDto target);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SubjectDto mapEntityToDto(SubjectEntity source);
}
