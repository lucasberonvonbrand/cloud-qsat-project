package com.example.catalog_service.controller;

import com.example.catalog_service.dto.RequestMetricDTO;
import com.example.catalog_service.dto.RequestUpdateMetricDTO;
import com.example.catalog_service.dto.ResponseMetricDTO;
import com.example.catalog_service.service.IMetricService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MetricController {

    private final IMetricService metricService;

    @GetMapping("/subcharacteristics/{subCharacteristicId}/metrics")
    public ResponseEntity<List<ResponseMetricDTO>> getMetricsBySubCharacteristicId(@PathVariable Long subCharacteristicId) {
        return ResponseEntity.ok(metricService.getMetricsBySubCharacteristicId(subCharacteristicId));
    }

    @PostMapping("/subcharacteristics/{subCharacteristicId}/metrics")
    public ResponseEntity<ResponseMetricDTO> createMetric(@PathVariable Long subCharacteristicId, @Valid @RequestBody RequestMetricDTO metricDTO) {
        return new ResponseEntity<>(metricService.createMetric(subCharacteristicId, metricDTO), HttpStatus.CREATED);
    }

    @GetMapping("/metrics/{id}")
    public ResponseEntity<ResponseMetricDTO> getMetricById(@PathVariable Long id) {
        return ResponseEntity.ok(metricService.getMetricById(id));
    }

    @PutMapping("/metrics/{id}")
    public ResponseEntity<ResponseMetricDTO> updateMetric(@PathVariable Long id, @Valid @RequestBody RequestUpdateMetricDTO metricDTO) {
        return ResponseEntity.ok(metricService.updateMetric(id, metricDTO));
    }

    @DeleteMapping("/metrics/{id}")
    public ResponseEntity<Void> deleteMetric(@PathVariable Long id) {
        metricService.deleteMetric(id);
        return ResponseEntity.noContent().build();
    }
}
