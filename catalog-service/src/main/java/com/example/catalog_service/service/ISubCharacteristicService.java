package com.example.catalog_service.service;

import com.example.catalog_service.dto.RequestSubCharacteristicDTO;
import com.example.catalog_service.dto.RequestUpdateSubCharacteristicDTO;
import com.example.catalog_service.dto.ResponseSubCharacteristicDTO;
import java.util.List;

public interface ISubCharacteristicService {
    List<ResponseSubCharacteristicDTO> getSubCharacteristicsByCharacteristicId(Long characteristicId);
    ResponseSubCharacteristicDTO getSubCharacteristicById(Long id);
    ResponseSubCharacteristicDTO createSubCharacteristic(Long characteristicId, RequestSubCharacteristicDTO subCharacteristicDTO);
    ResponseSubCharacteristicDTO updateSubCharacteristic(Long id, RequestUpdateSubCharacteristicDTO subCharacteristicDTO);
    void deleteSubCharacteristic(Long id);
}
