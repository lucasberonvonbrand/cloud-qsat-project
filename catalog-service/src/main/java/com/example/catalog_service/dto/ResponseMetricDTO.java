package com.example.catalog_service.dto;

import lombok.Data;

@Data
public class ResponseMetricDTO {

    private Long id;
    private String code;
    private String name;
    private String description;
    private Double threshold;
    private String operator;
    private String type;
}
