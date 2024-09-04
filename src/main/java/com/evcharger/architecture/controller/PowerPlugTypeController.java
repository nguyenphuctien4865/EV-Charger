package com.evcharger.architecture.controller;

import com.cloudinary.Api;
import com.evcharger.architecture.entity.PowerPlugType;
import com.evcharger.architecture.exception.common.InvalidParamException;
import com.evcharger.architecture.model.ApiResponse;
import com.evcharger.architecture.model.powerPlugType.PowerPlugTypeModel;
import com.evcharger.architecture.service.impl.PowerPlugTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/power-plug-types")
@Validated
public class PowerPlugTypeController {

    @Autowired
    private PowerPlugTypeServiceImpl service;

    @PostMapping
    public ResponseEntity<PowerPlugTypeModel> createPowerPlugType(@RequestBody @Valid PowerPlugTypeModel plugType)
            throws InvalidParamException {
        PowerPlugTypeModel createdPlugType = service.createPowerPlugType(plugType);
        return new ResponseEntity<>(createdPlugType, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PowerPlugTypeModel> getPowerPlugTypeById(@PathVariable Long id) {
        try {
            PowerPlugTypeModel plugType = service.getPowerPlugTypeById(id);
            return new ResponseEntity<>(plugType, HttpStatus.OK);
        } catch (InvalidParamException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PowerPlugTypeModel> updatePowerPlugType(@PathVariable Long id,
            @RequestBody PowerPlugTypeModel plugType) throws InvalidParamException {

        PowerPlugTypeModel updatedPlugType = service.updatePowerPlugType(id, plugType);
        return new ResponseEntity<>(updatedPlugType, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePowerPlugType(@PathVariable Long id) {
        service.deletePowerPlugType(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PowerPlugTypeModel>> listPowerPlugTypes(
            @RequestParam(value = "plugType", defaultValue = "", required = false) String plugType,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sort", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
                
        ApiResponse<PowerPlugTypeModel> plugTypes = service.listPowerPlugTypes(pageNo, pageSize, sortBy, sortDir, plugType);
        return new ResponseEntity<>(plugTypes, HttpStatus.OK);
    }
}
