package com.example.catalog_service.dto;

import lombok.Data;
import java.util.List;

@Data
public class ResponseCharacteristicDTO {

    private Long id;
    private String name;
    private String description;
    private List<ResponseSubCharacteristicDTO> subCharacteristics; // Usará el DTO de respuesta de SubCharacteristic
}
