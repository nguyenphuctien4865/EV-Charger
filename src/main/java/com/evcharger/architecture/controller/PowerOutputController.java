package com.evcharger.architecture.controller;

import com.evcharger.architecture.model.ApiResponse;
import com.evcharger.architecture.model.PowerOutputDTO;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evcharger.architecture.service.PowerOutputService;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/power-outputs")
public class PowerOutputController {

    @Autowired
    private PowerOutputService powerOutputService;

    // AC2: Read Power Output
    @GetMapping("/{id}")
    public ResponseEntity<PowerOutputDTO> getPowerOutputById(@PathVariable Long id) {
        PowerOutputDTO powerOutput = powerOutputService.getPowerOutputById(id);
        return powerOutput != null ? ResponseEntity.ok(powerOutput) : ResponseEntity.notFound().build();
    }

    // AC3: Update Power Output
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePowerOutput(@PathVariable Long id,
            @RequestBody PowerOutputDTO powerOutput) {
        PowerOutputDTO updatedPowerOutput = powerOutputService.updatePowerOutput(id, powerOutput);
        return updatedPowerOutput != null
                ? ResponseEntity.ok(updatedPowerOutput)
                : ResponseEntity.notFound().build();
    }

    // AC4: Delete Power Output
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePowerOutput(@PathVariable Long id) {
        powerOutputService.deletePowerOutput(id);
        return ResponseEntity.ok().body("{\"message\": \"Power output deleted successfully.\"}");
    }

    // AC5: List Power Outputs
    @GetMapping
    public ResponseEntity<ApiResponse<PowerOutputDTO>> listPowerOutputs(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sort", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {

        ApiResponse<PowerOutputDTO> powerOutputs = powerOutputService.listPowerOutputs(pageNo, pageSize, sortBy,
                sortDir);
        return ResponseEntity.ok(powerOutputs);
    }

    @PostMapping
    public ResponseEntity<PowerOutputDTO> createPowerOutput(@RequestBody @Valid PowerOutputDTO powerOutputModel) {
        // TODO: process POST request
        PowerOutputDTO model = powerOutputService.createPowerOutput(powerOutputModel);
        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

}