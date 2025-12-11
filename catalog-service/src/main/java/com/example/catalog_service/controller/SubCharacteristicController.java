package com.example.catalog_service.controller;

import com.example.catalog_service.dto.RequestSubCharacteristicDTO;
import com.example.catalog_service.dto.RequestUpdateSubCharacteristicDTO;
import com.example.catalog_service.dto.ResponseSubCharacteristicDTO;
import com.example.catalog_service.service.ISubCharacteristicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SubCharacteristicController {

    private final ISubCharacteristicService subCharacteristicService;

    @GetMapping("/characteristics/{characteristicId}/subcharacteristics")
    public ResponseEntity<List<ResponseSubCharacteristicDTO>> getSubCharacteristicsByCharacteristicId(@PathVariable Long characteristicId) {
        return ResponseEntity.ok(subCharacteristicService.getSubCharacteristicsByCharacteristicId(characteristicId));
    }

    @PostMapping("/characteristics/{characteristicId}/subcharacteristics")
    public ResponseEntity<ResponseSubCharacteristicDTO> createSubCharacteristic(@PathVariable Long characteristicId, @Valid @RequestBody RequestSubCharacteristicDTO subCharacteristicDTO) {
        return new ResponseEntity<>(subCharacteristicService.createSubCharacteristic(characteristicId, subCharacteristicDTO), HttpStatus.CREATED);
    }

    @GetMapping("/subcharacteristics/{id}")
    public ResponseEntity<ResponseSubCharacteristicDTO> getSubCharacteristicById(@PathVariable Long id) {
        return ResponseEntity.ok(subCharacteristicService.getSubCharacteristicById(id));
    }

    @PutMapping("/subcharacteristics/{id}")
    public ResponseEntity<ResponseSubCharacteristicDTO> updateSubCharacteristic(@PathVariable Long id, @Valid @RequestBody RequestUpdateSubCharacteristicDTO subCharacteristicDTO) {
        return ResponseEntity.ok(subCharacteristicService.updateSubCharacteristic(id, subCharacteristicDTO));
    }

    @DeleteMapping("/subcharacteristics/{id}")
    public ResponseEntity<Void> deleteSubCharacteristic(@PathVariable Long id) {
        subCharacteristicService.deleteSubCharacteristic(id);
        return ResponseEntity.noContent().build();
    }
}
