package org.zerogravitysolutions.training.utils;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.zerogravitysolutions.training.TrainingDto;
import org.zerogravitysolutions.training.TrainingDtoPageable;
import org.zerogravitysolutions.training.TrainingEntity;

@Mapper(componentModel = "spring")
public interface TrainingMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapDtoToEntity(TrainingDto source, @MappingTarget TrainingEntity target);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapEntityToDto(TrainingEntity source, @MappingTarget TrainingDto target);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TrainingDto mapEntityToDto(TrainingEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TrainingEntity mapDtoToEntityWithEntityReturnType(TrainingDto source, @MappingTarget TrainingEntity target);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TrainingDto mapsEntityToDto(TrainingEntity source, @MappingTarget TrainingDto target);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapEntityToDtoPageAble(TrainingEntity source, @MappingTarget TrainingDtoPageable target);
}
