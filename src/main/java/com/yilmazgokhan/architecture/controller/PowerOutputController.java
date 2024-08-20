package com.yilmazgokhan.architecture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yilmazgokhan.architecture.model.PowerOutputModel;
import com.yilmazgokhan.architecture.service.IPowerOutputService;
import java.util.List;
@RestController
@RequestMapping("/power-outputs")
public class PowerOutputController {

    @Autowired
    private IPowerOutputService powerOutputService;

    // AC2: Read Power Output
    @GetMapping("/{id}")
    public ResponseEntity<PowerOutputModel> getPowerOutputById(@PathVariable Long id) {
        PowerOutputModel powerOutput = powerOutputService.getPowerOutputById(id);
        if (powerOutput != null) {
            return ResponseEntity.ok(powerOutput);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // AC3: Update Power Output
    @PutMapping("/{id}")
    public ResponseEntity<PowerOutputModel> updatePowerOutput(@PathVariable Long id, @RequestBody PowerOutputModel powerOutput) {
        PowerOutputModel updatedPowerOutput = powerOutputService.updatePowerOutput(id, powerOutput);
        if (updatedPowerOutput != null) {
            return ResponseEntity.ok(updatedPowerOutput);
        } else {
            return ResponseEntity.notFound().build();
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
    public ResponseEntity<List<PowerOutputModel>> listPowerOutputs() {
        List<PowerOutputModel> powerOutputs = powerOutputService.listPowerOutputs();
        return ResponseEntity.ok(powerOutputs);
    }
}