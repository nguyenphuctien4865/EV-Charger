package com.evcharger.architecture.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evcharger.architecture.model.ApiResponse;
import com.evcharger.architecture.model.EVChargerDTO;
import com.evcharger.architecture.service.EVChargerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ev-chargers")
public class EVChargerController {
    @Autowired
    private EVChargerService evChargerService;

    // AC1: Create EV Charger
    @PostMapping
    public ResponseEntity<EVChargerDTO> createEVCharger(@RequestBody @Valid EVChargerDTO evChargerDTO) {
        log.info("Received request data: {}", evChargerDTO);
        EVChargerDTO createdCharger = evChargerService.createEVCharger(evChargerDTO);
        return new ResponseEntity<>(createdCharger, HttpStatus.CREATED);
    }

    // AC2: Read EV Charger
    @GetMapping("/{id}")
    public ResponseEntity<EVChargerDTO> getEVCharger(@PathVariable String id) {
        EVChargerDTO evChargerDTO = evChargerService.getEVCharger(id);
        return ResponseEntity.ok(evChargerDTO);
    }

    // AC3: Update EV Charger
    @PutMapping("/{id}")
    public ResponseEntity<EVChargerDTO> updateEVCharger(@PathVariable String id,
            @Validated @RequestBody EVChargerDTO evChargerDTO) {
        EVChargerDTO updatedCharger = evChargerService.updateEVCharger(id, evChargerDTO);
        return ResponseEntity.ok(updatedCharger);
    }

    // AC4: Delete EV Charger
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEVCharger(@PathVariable String id) {
        evChargerService.deleteEVCharger(id);
        return ResponseEntity.noContent().build();
    }

    // AC5: List EV Chargers
    @GetMapping
    public ResponseEntity<ApiResponse<EVChargerDTO>> listEVChargers(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sort", defaultValue = "chargerId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir,
            @RequestParam(required = false) String locationId,
            @RequestParam(required = false) String powerPlugTypeId,
            @RequestParam(required = false) String powerOutputId) {
        ApiResponse<EVChargerDTO> evChargers = evChargerService.listEVChargers(pageNo, pageSize, sortBy, sortDir,
                locationId, powerPlugTypeId, powerOutputId);
        return ResponseEntity.ok(evChargers);
    }
}
