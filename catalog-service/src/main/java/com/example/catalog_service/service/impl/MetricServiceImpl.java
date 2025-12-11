package com.example.catalog_service.service.impl;

import com.example.catalog_service.dto.RequestMetricDTO;
import com.example.catalog_service.dto.RequestUpdateMetricDTO;
import com.example.catalog_service.dto.ResponseMetricDTO;
import com.example.catalog_service.enums.ComparisonOperator;
import com.example.catalog_service.enums.MetricType;
import com.example.catalog_service.exception.BusinessRuleException;
import com.example.catalog_service.exception.ResourceNotFoundException;
import com.example.catalog_service.mapper.CatalogMapper;
import com.example.catalog_service.model.Metric;
import com.example.catalog_service.model.SubCharacteristic;
import com.example.catalog_service.repository.IMetricRepository;
import com.example.catalog_service.repository.ISubCharacteristicRepository;
import com.example.catalog_service.service.IMetricService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MetricServiceImpl implements IMetricService {

    private final IMetricRepository metricRepository;
    private final ISubCharacteristicRepository subCharacteristicRepository;
    private final CatalogMapper mapper;

    @Override
    public List<ResponseMetricDTO> getMetricsBySubCharacteristicId(Long subCharacteristicId) {
        if (!subCharacteristicRepository.existsById(subCharacteristicId)) {
            throw new ResourceNotFoundException("SubCharacteristic not found with id: " + subCharacteristicId);
        }
        return metricRepository.findBySubCharacteristicId(subCharacteristicId).stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseMetricDTO getMetricById(Long id) {
        Metric metric = metricRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Metric not found with id: " + id));
        return mapper.toResponseDTO(metric);
    }

    @Override
    public ResponseMetricDTO createMetric(Long subCharacteristicId, RequestMetricDTO metricDTO) {
        SubCharacteristic subCharacteristic = subCharacteristicRepository.findById(subCharacteristicId)
                .orElseThrow(() -> new ResourceNotFoundException("SubCharacteristic not found with id: " + subCharacteristicId));

        Metric metric = mapper.toEntity(metricDTO);
        metric.setSubCharacteristic(subCharacteristic);
        
        try {
            metric.setOperator(ComparisonOperator.valueOf(metricDTO.getOperator().toUpperCase()));
            metric.setType(MetricType.valueOf(metricDTO.getType().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new BusinessRuleException("Invalid value for operator or type: " + e.getMessage());
        }

        return mapper.toResponseDTO(metricRepository.save(metric));
    }

    @Override
    public ResponseMetricDTO updateMetric(Long id, RequestUpdateMetricDTO metricDTO) {
        Metric metric = metricRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Metric not found with id: " + id));

        mapper.updateEntity(metricDTO, metric); // Usamos el método de actualización de MapStruct
        
        try {
            if (metricDTO.getOperator() != null) metric.setOperator(ComparisonOperator.valueOf(metricDTO.getOperator().toUpperCase()));
            if (metricDTO.getType() != null) metric.setType(MetricType.valueOf(metricDTO.getType().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new BusinessRuleException("Invalid value for operator or type: " + e.getMessage());
        }

        return mapper.toResponseDTO(metricRepository.save(metric));
    }

    @Override
    public void deleteMetric(Long id) {
        if (!metricRepository.existsById(id)) {
            throw new ResourceNotFoundException("Metric not found with id: " + id);
        }
        metricRepository.deleteById(id);
    }
}
