package com.example.catalog_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Characteristic {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(length = 500)
    private String description;

    // Relación: Una Característica tiene muchas SubCaracterísticas
    // FetchType.EAGER carga los hijos automáticamente al consultar el padre
    @OneToMany(mappedBy = "characteristic", fetch = FetchType.EAGER)
    private List<SubCharacteristic> subCharacteristics;

}
