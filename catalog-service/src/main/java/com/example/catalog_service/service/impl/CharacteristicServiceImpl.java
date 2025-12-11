package com.example.catalog_service.service.impl;

import com.example.catalog_service.dto.RequestCharacteristicDTO;
import com.example.catalog_service.dto.RequestUpdateCharacteristicDTO;
import com.example.catalog_service.dto.ResponseCharacteristicDTO;
import com.example.catalog_service.exception.ResourceNotFoundException;
import com.example.catalog_service.mapper.CatalogMapper;
import com.example.catalog_service.model.Characteristic;
import com.example.catalog_service.repository.ICharacteristicRepository;
import com.example.catalog_service.service.ICharacteristicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CharacteristicServiceImpl implements ICharacteristicService {

    private final ICharacteristicRepository characteristicRepository;
    private final CatalogMapper mapper;

    @Override
    public List<ResponseCharacteristicDTO> getAllCharacteristics() {
        return characteristicRepository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseCharacteristicDTO getCharacteristicById(Long id) {
        Characteristic characteristic = characteristicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Characteristic not found with id: " + id));
        return mapper.toResponseDTO(characteristic);
    }

    @Override
    public ResponseCharacteristicDTO createCharacteristic(RequestCharacteristicDTO characteristicDTO) {
        Characteristic characteristic = mapper.toEntity(characteristicDTO);
        return mapper.toResponseDTO(characteristicRepository.save(characteristic));
    }

    @Override
    public ResponseCharacteristicDTO updateCharacteristic(Long id, RequestUpdateCharacteristicDTO characteristicDTO) {
        Characteristic characteristic = characteristicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Characteristic not found with id: " + id));
        
        mapper.updateEntity(characteristicDTO, characteristic); // Usamos el método de actualización de MapStruct

        return mapper.toResponseDTO(characteristicRepository.save(characteristic));
    }

    @Override
    public void deleteCharacteristic(Long id) {
        if (!characteristicRepository.existsById(id)) {
            throw new ResourceNotFoundException("Characteristic not found with id: " + id);
        }
        characteristicRepository.deleteById(id);
    }

    @Override
    public List<ResponseCharacteristicDTO> getFullCatalogTree() {
        return getAllCharacteristics();
    }
}
