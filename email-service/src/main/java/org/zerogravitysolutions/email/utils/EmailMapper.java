package org.zerogravitysolutions.email.utils;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.zerogravitysolutions.email.EmailDto;
import org.zerogravitysolutions.email.EmailEntity;

@Mapper(componentModel = "spring")
public interface EmailMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapDtoToEntity(EmailDto source, @MappingTarget EmailEntity target);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapEntityToDto(EmailEntity source, @MappingTarget EmailDto target);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EmailDto mapEntityToDto(EmailEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EmailDto mapsEntityToDto(EmailEntity source, @MappingTarget EmailDto target );
}
