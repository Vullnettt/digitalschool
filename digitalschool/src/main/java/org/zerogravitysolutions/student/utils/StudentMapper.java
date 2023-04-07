package org.zerogravitysolutions.student.utils;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.zerogravitysolutions.student.StudentDto;
import org.zerogravitysolutions.student.StudentEntity;

@Mapper(componentModel = "spring")
public interface StudentMapper {


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapDtoToEntity(StudentDto source, @MappingTarget StudentEntity target);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapEntityToDto(StudentEntity source, @MappingTarget StudentDto target);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    StudentDto mapEntityToDto(StudentEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    StudentDto mapsEntityToDto(StudentEntity source, @MappingTarget StudentDto target );
}
