package com.evcharger.architecture.service;


import com.evcharger.architecture.model.ApiResponse;
import com.evcharger.architecture.model.LocationDTO;

public interface LocationService {
    LocationDTO getLocationById(Long id);

    LocationDTO createLocation (LocationDTO location);

    LocationDTO updateLocation(Long id, LocationDTO updatedLocation);

    void deleteLocation(Long id);

    ApiResponse<LocationDTO> listAllLocations(int pageNo, int pageSize, String sortBy, String sortDir);
}
