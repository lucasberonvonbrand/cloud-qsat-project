package com.example.catalog_service.repository;

import com.example.catalog_service.model.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMetricRepository extends JpaRepository<Metric, Long> {
    Optional<Metric> findByCode(String code);
    List<Metric> findBySubCharacteristicId(Long subCharacteristicId);
}
