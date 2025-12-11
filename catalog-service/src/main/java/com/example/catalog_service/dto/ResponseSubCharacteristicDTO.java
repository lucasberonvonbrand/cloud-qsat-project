package com.example.catalog_service.dto;

import lombok.Data;
import java.util.List;

@Data
public class ResponseSubCharacteristicDTO {

    private Long id;
    private String name;
    private List<ResponseMetricDTO> metrics; // Usará el DTO de respuesta de Metric
}
