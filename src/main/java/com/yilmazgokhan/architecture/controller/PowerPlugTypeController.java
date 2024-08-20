package com.yilmazgokhan.architecture.controller;

import com.yilmazgokhan.architecture.entity.PowerPlugType;
import com.yilmazgokhan.architecture.exception.common.InvalidParamException;
import com.yilmazgokhan.architecture.service.impl.PowerPlugTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/power-plug-types")
public class PowerPlugTypeController {


    @Autowired
    private PowerPlugTypeService service;

    @PostMapping
    public ResponseEntity<PowerPlugType> createPowerPlugType(@RequestBody PowerPlugType plugType) {
        try {
            PowerPlugType createdPlugType = service.createPowerPlugType(plugType);
            return new ResponseEntity<>(createdPlugType, HttpStatus.CREATED);
        } catch (InvalidParamException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PowerPlugType> getPowerPlugTypeById(@PathVariable Long id) {
        try {
            PowerPlugType plugType = service.getPowerPlugTypeById(id);
            return new ResponseEntity<>(plugType, HttpStatus.OK);
        } catch (InvalidParamException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PowerPlugType> updatePowerPlugType(@PathVariable Long id, @RequestBody PowerPlugType plugType) {
        try {
            PowerPlugType updatedPlugType = service.updatePowerPlugType(id, plugType);
            return new ResponseEntity<>(updatedPlugType, HttpStatus.OK);
        } catch (InvalidParamException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePowerPlugType(@PathVariable Long id) {
        try {
            service.deletePowerPlugType(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InvalidParamException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<PowerPlugType>> listPowerPlugTypes(@RequestParam(required = false) String plugType) {
        List<PowerPlugType> plugTypes = service.listPowerPlugTypes(plugType);
        return new ResponseEntity<>(plugTypes, HttpStatus.OK);
    }
}
