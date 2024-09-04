package com.evcharger.architecture.controller;

import com.evcharger.architecture.model.ApiResponse;
import com.evcharger.architecture.model.PowerOutputModel;

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

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/power-outputs")
public class PowerOutputController {

    @Autowired
    private PowerOutputService powerOutputService;

    // AC2: Read Power Output
    @GetMapping("/{id}")
    public ResponseEntity<PowerOutputModel> getPowerOutputById(@PathVariable Long id) {
        PowerOutputModel powerOutput = powerOutputService.getPowerOutputById(id);
        if (powerOutput != null) {
            return new ResponseEntity<>(powerOutput, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // AC3: Update Power Output
    @PutMapping("/{id}")
    public ResponseEntity<PowerOutputModel> updatePowerOutput(@PathVariable Long id,
            @RequestBody PowerOutputModel powerOutput) {
        PowerOutputModel updatedPowerOutput = powerOutputService.updatePowerOutput(id, powerOutput);
        if (updatedPowerOutput != null) {
            return new ResponseEntity<>(updatedPowerOutput, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // AC4: Delete Power Output
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePowerOutput(@PathVariable Long id) {
        powerOutputService.deletePowerOutput(id);
        return ResponseEntity.ok().body("{\"message\": \"Power output deleted successfully.\"}");
    }

    // AC5: List Power Outputs
    @GetMapping
    public ResponseEntity<ApiResponse<PowerOutputModel>> listPowerOutputs(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sort", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {

        ApiResponse<PowerOutputModel> powerOutputs = powerOutputService.listPowerOutputs(pageNo, pageSize, sortBy,sortDir);
        return ResponseEntity.ok(powerOutputs);
    }

    @PostMapping
    public ResponseEntity<PowerOutputModel> createPowerOutput(@RequestBody @Valid PowerOutputModel powerOutputModel) {
        // TODO: process POST request
        PowerOutputModel model = powerOutputService.createPowerOutput(powerOutputModel);
        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

}