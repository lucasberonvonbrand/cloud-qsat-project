package com.example.catalog_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestSubCharacteristicDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
}
