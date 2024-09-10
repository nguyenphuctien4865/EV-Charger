package com.evcharger.architecture.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.evcharger.architecture.entity.Location;
import com.evcharger.architecture.exception.common.ResourceNotFoundException;
import com.evcharger.architecture.model.ApiResponse;
import com.evcharger.architecture.model.LocationDTO;
import com.evcharger.architecture.repository.LocationRepository;
import com.evcharger.architecture.service.LocationService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public LocationDTO getLocationById(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location", "Id", id));
        return mapToDto(location);
    }

    @Override
    public LocationDTO updateLocation(Long id, LocationDTO updatedLocation) {
        return mapToDto(locationRepository.findById(id)
                .map(existingLocation -> {
                    existingLocation.setName(updatedLocation.getName());
                    existingLocation.setStreetAddress(updatedLocation.getStreetAddress());
                    existingLocation.setDistrict(updatedLocation.getDistrict());
                    existingLocation.setCityProvince(updatedLocation.getCityProvince());
                    existingLocation.setCountry(updatedLocation.getCountry());
                    existingLocation.setPostalCode(updatedLocation.getPostalCode());
                    existingLocation.setLatitude(updatedLocation.getLatitude());
                    existingLocation.setLongitude(updatedLocation.getLongitude());
                    existingLocation.setOperatingHours(updatedLocation.getOperatingHours());
                    existingLocation.setPricing(updatedLocation.getPricing());
                    existingLocation.setPhoneNumber(updatedLocation.getPhoneNumber());
                    existingLocation.setParkingLevel(updatedLocation.getParkingLevel());
                    return locationRepository.save(existingLocation);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Location", "Id", id)));
    }

    @Override
    public void deleteLocation(Long id) {
        locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location", "Id", id));
        locationRepository.deleteById(id);
    }

    @Override
    public ApiResponse<LocationDTO> listAllLocations(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Location> locationPage = locationRepository.findAll(pageable);

        List<LocationDTO> locationDTOs = locationPage.getContent()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        ApiResponse<LocationDTO> response = new ApiResponse<LocationDTO>();
        response.setContent(locationDTOs);
        response.setPageNumber(pageNo);
        response.setPageSize(pageSize);
        response.setTotalElements(locationPage.getTotalElements());
        response.setTotalPages(pageSize);

        return response;
    }

    @Override
    public LocationDTO createLocation(LocationDTO location) {
        return mapToDto(locationRepository.save(mapToEntity(location)));
    }


    private LocationDTO mapToDto(Location location) {

        LocationDTO locationDTO = modelMapper.map(location, LocationDTO.class);
        return locationDTO;
    }

    private Location mapToEntity(LocationDTO locationDTO) {

        Location location = modelMapper.map(locationDTO, Location.class);
        return location;
    }

 
}
