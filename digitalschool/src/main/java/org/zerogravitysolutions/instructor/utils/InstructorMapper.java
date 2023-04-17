package org.zerogravitysolutions.instructor.utils;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.zerogravitysolutions.instructor.InstructorDto;
import org.zerogravitysolutions.instructor.InstructorEntity;

@Mapper(componentModel = "spring")
public interface InstructorMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapDtoToEntity(InstructorDto source, @MappingTarget InstructorEntity target);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapEntityToDto(InstructorEntity source, @MappingTarget InstructorDto target);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    InstructorDto mapEntityToDto(InstructorEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    InstructorEntity mapDtoToEntityWithEntityReturnType(InstructorDto target, @MappingTarget InstructorEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    InstructorDto mapsEntityToDto(InstructorEntity instructorEntity, @MappingTarget InstructorDto target);
}
