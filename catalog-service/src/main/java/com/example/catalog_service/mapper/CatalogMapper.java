package com.example.catalog_service.mapper;

import com.example.catalog_service.dto.*;
import com.example.catalog_service.model.Characteristic;
import com.example.catalog_service.model.Metric;
import com.example.catalog_service.model.SubCharacteristic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CatalogMapper {

    // --- Characteristic Mappers ---
    ResponseCharacteristicDTO toResponseDTO(Characteristic entity);
    Characteristic toEntity(RequestCharacteristicDTO dto);
    void updateEntity(RequestUpdateCharacteristicDTO dto, @MappingTarget Characteristic entity);

    // --- SubCharacteristic Mappers ---
    ResponseSubCharacteristicDTO toResponseDTO(SubCharacteristic entity);
    SubCharacteristic toEntity(RequestSubCharacteristicDTO dto);
    void updateEntity(RequestUpdateSubCharacteristicDTO dto, @MappingTarget SubCharacteristic entity);

    // --- Metric Mappers ---
    @Mapping(target = "operator", ignore = true) // Ignoramos los enums, se manejan en el servicio
    @Mapping(target = "type", ignore = true)
    Metric toEntity(RequestMetricDTO dto);

    @Mapping(target = "operator", expression = "java(entity.getOperator() != null ? entity.getOperator().name() : null)")
    @Mapping(target = "type", expression = "java(entity.getType() != null ? entity.getType().name() : null)")
    ResponseMetricDTO toResponseDTO(Metric entity);

    @Mapping(target = "operator", ignore = true)
    @Mapping(target = "type", ignore = true)
    void updateEntity(RequestUpdateMetricDTO dto, @MappingTarget Metric entity);
}
