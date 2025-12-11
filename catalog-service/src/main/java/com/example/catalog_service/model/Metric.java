package com.example.catalog_service.model;

import com.example.catalog_service.enums.ComparisonOperator;
import com.example.catalog_service.enums.MetricType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class Metric {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;

    @Column(length = 500)
    private String description;

    // --- Motor de Reglas ---
    @Enumerated(EnumType.STRING)
    private MetricType type;

    private Double threshold;

    @Enumerated(EnumType.STRING)
    private ComparisonOperator operator;

    @ManyToOne
    @JoinColumn(name = "sub_characteristic_id")
    @ToString.Exclude
    private SubCharacteristic subCharacteristic;

}
