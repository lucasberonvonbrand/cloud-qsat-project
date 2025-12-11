package com.example.catalog_service.controller;

import com.example.catalog_service.dto.RequestCharacteristicDTO;
import com.example.catalog_service.dto.RequestUpdateCharacteristicDTO;
import com.example.catalog_service.dto.ResponseCharacteristicDTO;
import com.example.catalog_service.service.ICharacteristicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characteristics")
@RequiredArgsConstructor
public class CharacteristicController {

    private final ICharacteristicService characteristicService;

    @GetMapping("/tree")
    public ResponseEntity<List<ResponseCharacteristicDTO>> getFullCatalogTree() {
        return ResponseEntity.ok(characteristicService.getFullCatalogTree());
    }

    @GetMapping
    public ResponseEntity<List<ResponseCharacteristicDTO>> getAllCharacteristics() {
        return ResponseEntity.ok(characteristicService.getAllCharacteristics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCharacteristicDTO> getCharacteristicById(@PathVariable Long id) {
        return ResponseEntity.ok(characteristicService.getCharacteristicById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseCharacteristicDTO> createCharacteristic(@Valid @RequestBody RequestCharacteristicDTO characteristicDTO) {
        return new ResponseEntity<>(characteristicService.createCharacteristic(characteristicDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCharacteristicDTO> updateCharacteristic(@PathVariable Long id, @Valid @RequestBody RequestUpdateCharacteristicDTO characteristicDTO) {
        return ResponseEntity.ok(characteristicService.updateCharacteristic(id, characteristicDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacteristic(@PathVariable Long id) {
        characteristicService.deleteCharacteristic(id);
        return ResponseEntity.noContent().build();
    }
}
