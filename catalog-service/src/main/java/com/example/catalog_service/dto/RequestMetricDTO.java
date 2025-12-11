package com.example.catalog_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestMetricDTO {

    @NotBlank(message = "El código no puede estar vacío")
    private String code;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    private String description;

    @NotNull(message = "El umbral (threshold) no puede ser nulo")
    private Double threshold;

    @NotBlank(message = "El operador no puede estar vacío")
    private String operator;

    @NotBlank(message = "El tipo no puede estar vacío")
    private String type;
}
