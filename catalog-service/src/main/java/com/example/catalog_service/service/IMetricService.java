package com.example.catalog_service.service;

import com.example.catalog_service.dto.RequestMetricDTO;
import com.example.catalog_service.dto.RequestUpdateMetricDTO;
import com.example.catalog_service.dto.ResponseMetricDTO;
import java.util.List;

public interface IMetricService {
    List<ResponseMetricDTO> getMetricsBySubCharacteristicId(Long subCharacteristicId);
    ResponseMetricDTO getMetricById(Long id);
    ResponseMetricDTO createMetric(Long subCharacteristicId, RequestMetricDTO metricDTO);
    ResponseMetricDTO updateMetric(Long id, RequestUpdateMetricDTO metricDTO);
    void deleteMetric(Long id);
}
