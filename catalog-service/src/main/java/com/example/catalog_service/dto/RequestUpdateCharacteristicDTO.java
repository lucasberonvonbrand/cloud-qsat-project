package com.example.catalog_service.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestUpdateCharacteristicDTO {

    private String name;

    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    private String description;
}
