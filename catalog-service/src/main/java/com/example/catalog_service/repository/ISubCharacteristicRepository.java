package com.example.catalog_service.repository;

import com.example.catalog_service.model.SubCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISubCharacteristicRepository extends JpaRepository<SubCharacteristic, Long> {
    List<SubCharacteristic> findByCharacteristicId(Long characteristicId);
}
