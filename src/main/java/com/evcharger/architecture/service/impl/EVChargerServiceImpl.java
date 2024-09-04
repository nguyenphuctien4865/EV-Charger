package com.evcharger.architecture.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.evcharger.architecture.entity.EVCharger;
import com.evcharger.architecture.entity.PowerOutput;
import com.evcharger.architecture.exception.CustomExceptionHandler;
import com.evcharger.architecture.exception.common.ResourceNotFoundException;
import com.evcharger.architecture.model.ApiResponse;
import com.evcharger.architecture.model.EVChargerDTO;
import com.evcharger.architecture.model.PowerOutputModel;
import com.evcharger.architecture.repository.EVChargerRepository;
import com.evcharger.architecture.repository.PowerOutputRepository;
import com.evcharger.architecture.service.EVChargerService;
import com.evcharger.architecture.specification.EVChargerSpecification;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EVChargerServiceImpl implements EVChargerService {

    @Autowired
    private EVChargerRepository evChargerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EVChargerDTO createEVCharger(EVChargerDTO evChargerDTO) {
        // TODO Auto-generated method stub
        return mapToDto(evChargerRepository.save(mapToEntity(evChargerDTO)));
    }

    @Override
    public EVChargerDTO getEVCharger(String id) {
        // TODO Auto-generated method stub
        EVCharger evCharger = evChargerRepository.findByChargerId(id)
                .orElseThrow(() -> new ResourceNotFoundException("EV Charger", "Id", id));

        return mapToDto(evCharger);
    }

    @Override
    public EVChargerDTO updateEVCharger(String id, EVChargerDTO evChargerDTO) {
        // TODO Auto-generated method stub

        EVCharger evCharger = evChargerRepository.findByChargerId(id)
                .orElseThrow(() -> new ResourceNotFoundException("EV Charger", "Id", id));

        evCharger.setAvailability(evChargerDTO.getAvailability());
        evCharger.setInstallationDate(evChargerDTO.getInstallationDate());
        evCharger.setLastMaintenanceDate(evChargerDTO.getLastMaintenanceDate());
        evCharger.setLocationId(evChargerDTO.getLocationId());
        evCharger.setNumberOfPorts(evChargerDTO.getNumberOfPorts());
        evCharger.setPowerOutputId(evChargerDTO.getPowerOutputId());
        evCharger.setPowerPlugTypeId(evChargerDTO.getPowerPlugTypeId());

        return mapToDto(evChargerRepository.save(evCharger));
    }

    @Override
    public void deleteEVCharger(String id) {
        // TODO Auto-generated method stub
        EVCharger evCharger = evChargerRepository.findByChargerId(id)
                .orElseThrow(() -> new ResourceNotFoundException("EV Charger", "Id", id));

        evChargerRepository.delete(evCharger);
    }

    @Override
    public ApiResponse<EVChargerDTO> listEVChargers(int pageNo, int pageSize, String sortBy, String sortDir,
            String locationId, String powerPlugTypeId, String powerOutputId) {
        // TODO Auto-generated method stub

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Specification<EVCharger> spec = Specification.where(null);

        if (locationId != null && !locationId.isEmpty()) {
            spec = spec.and(EVChargerSpecification.hasLocationId(locationId));
        }
        if (powerPlugTypeId != null && !powerPlugTypeId.isEmpty()) {
            spec = spec.and(EVChargerSpecification.hasPowerPlugTypeId(powerPlugTypeId));
        }
        if (powerOutputId != null && !powerOutputId.isEmpty()) {
            spec = spec.and(EVChargerSpecification.hasPowerOutputId(powerOutputId));
        }
        Page<EVCharger> page = evChargerRepository.findAll(spec, pageable);

        List<EVChargerDTO> powerOutputs = page.getContent()
                .stream().map(this::mapToDto).collect(Collectors.toList());

        ApiResponse<EVChargerDTO> pageResponse = new ApiResponse<>();
        pageResponse.setContent(powerOutputs);
        pageResponse.setPageNumber(page.getNumber());
        pageResponse.setPageSize(page.getSize());
        pageResponse.setTotalElements(page.getTotalElements());
        pageResponse.setTotalPages(page.getTotalPages());
        pageResponse.setLast(page.isLast());
        pageResponse.setFirst(page.isFirst());
        pageResponse.setEmpty(page.isEmpty());

        log.info("Fetched {} power plug types", powerOutputs.size());

        return pageResponse;
    }

    private EVChargerDTO mapToDto(EVCharger evCharger) {

        EVChargerDTO evChargerDTO = modelMapper.map(evCharger, EVChargerDTO.class);
        return evChargerDTO;
    }

    private EVCharger mapToEntity(EVChargerDTO evChargerDTO) {

        EVCharger evCharger = modelMapper.map(evChargerDTO, EVCharger.class);
        return evCharger;
    }
}
