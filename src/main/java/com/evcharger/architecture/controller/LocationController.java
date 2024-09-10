package com.evcharger.architecture.controller;

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

import com.evcharger.architecture.model.ApiResponse;
import com.evcharger.architecture.model.LocationDTO;
import com.evcharger.architecture.service.LocationService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> getLocationById(@PathVariable Long id) {
        LocationDTO location = locationService.getLocationById(id);
        return ResponseEntity.ok(location);
    }

    @PostMapping
    public ResponseEntity<LocationDTO> postMethodName(@RequestBody @Valid LocationDTO locationDTO) {
        log.info("Received request data: {}", locationDTO);

        LocationDTO createLocationDTO = locationService.createLocation(locationDTO);

        return new ResponseEntity<>(createLocationDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationDTO> updateLocation(@PathVariable Long id,
            @Valid @RequestBody LocationDTO updatedLocation) {
        LocationDTO location = locationService.updateLocation(id, updatedLocation);
        return ResponseEntity.ok(location);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<LocationDTO>> listAllLocations(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sort", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {

        ApiResponse<LocationDTO> locations = locationService.listAllLocations(pageNo, pageSize, sortBy, sortDir);
        return ResponseEntity.ok(locations);
    }
}