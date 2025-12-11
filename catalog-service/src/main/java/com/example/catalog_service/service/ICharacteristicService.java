package com.example.catalog_service.service;

import com.example.catalog_service.dto.RequestCharacteristicDTO;
import com.example.catalog_service.dto.RequestUpdateCharacteristicDTO;
import com.example.catalog_service.dto.ResponseCharacteristicDTO;
import java.util.List;

public interface ICharacteristicService {
    List<ResponseCharacteristicDTO> getAllCharacteristics();
    ResponseCharacteristicDTO getCharacteristicById(Long id);
    ResponseCharacteristicDTO createCharacteristic(RequestCharacteristicDTO characteristicDTO);
    ResponseCharacteristicDTO updateCharacteristic(Long id, RequestUpdateCharacteristicDTO characteristicDTO);
    void deleteCharacteristic(Long id);
    List<ResponseCharacteristicDTO> getFullCatalogTree();
}
