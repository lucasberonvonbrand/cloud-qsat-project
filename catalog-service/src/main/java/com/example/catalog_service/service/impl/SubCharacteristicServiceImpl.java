package com.example.catalog_service.service.impl;

import com.example.catalog_service.dto.RequestSubCharacteristicDTO;
import com.example.catalog_service.dto.RequestUpdateSubCharacteristicDTO;
import com.example.catalog_service.dto.ResponseSubCharacteristicDTO;
import com.example.catalog_service.exception.ResourceNotFoundException;
import com.example.catalog_service.mapper.CatalogMapper;
import com.example.catalog_service.model.Characteristic;
import com.example.catalog_service.model.SubCharacteristic;
import com.example.catalog_service.repository.ICharacteristicRepository;
import com.example.catalog_service.repository.ISubCharacteristicRepository;
import com.example.catalog_service.service.ISubCharacteristicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubCharacteristicServiceImpl implements ISubCharacteristicService {

    private final ISubCharacteristicRepository subCharacteristicRepository;
    private final ICharacteristicRepository characteristicRepository;
    private final CatalogMapper mapper;

    @Override
    public List<ResponseSubCharacteristicDTO> getSubCharacteristicsByCharacteristicId(Long characteristicId) {
        if (!characteristicRepository.existsById(characteristicId)) {
            throw new ResourceNotFoundException("Characteristic not found with id: " + characteristicId);
        }
        return subCharacteristicRepository.findByCharacteristicId(characteristicId).stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseSubCharacteristicDTO getSubCharacteristicById(Long id) {
        SubCharacteristic subCharacteristic = subCharacteristicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SubCharacteristic not found with id: " + id));
        return mapper.toResponseDTO(subCharacteristic);
    }

    @Override
    public ResponseSubCharacteristicDTO createSubCharacteristic(Long characteristicId, RequestSubCharacteristicDTO subCharacteristicDTO) {
        Characteristic characteristic = characteristicRepository.findById(characteristicId)
                .orElseThrow(() -> new ResourceNotFoundException("Characteristic not found with id: " + characteristicId));
        
        SubCharacteristic subCharacteristic = mapper.toEntity(subCharacteristicDTO);
        subCharacteristic.setCharacteristic(characteristic);
        
        return mapper.toResponseDTO(subCharacteristicRepository.save(subCharacteristic));
    }

    @Override
    public ResponseSubCharacteristicDTO updateSubCharacteristic(Long id, RequestUpdateSubCharacteristicDTO subCharacteristicDTO) {
        SubCharacteristic subCharacteristic = subCharacteristicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SubCharacteristic not found with id: " + id));

        mapper.updateEntity(subCharacteristicDTO, subCharacteristic); // Usamos el método de actualización de MapStruct

        return mapper.toResponseDTO(subCharacteristicRepository.save(subCharacteristic));
    }

    @Override
    public void deleteSubCharacteristic(Long id) {
        if (!subCharacteristicRepository.existsById(id)) {
            throw new ResourceNotFoundException("SubCharacteristic not found with id: " + id);
        }
        subCharacteristicRepository.deleteById(id);
    }
}
