package com.example.catalog_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
public class SubCharacteristic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "characteristic_id")
    @ToString.Exclude // Evita bucles infinitos en los logs
    private Characteristic characteristic;

    // Relación: Una SubCaracterística tiene muchas Métricas
    @OneToMany(mappedBy = "subCharacteristic", fetch = FetchType.EAGER)
    private List<Metric> metrics;

}
