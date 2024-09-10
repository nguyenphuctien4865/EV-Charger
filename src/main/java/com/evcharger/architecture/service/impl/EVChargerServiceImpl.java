package com.evcharger.architecture.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.evcharger.architecture.entity.EVCharger;
import com.evcharger.architecture.entity.Location;
import com.evcharger.architecture.entity.PowerOutput;
import com.evcharger.architecture.entity.PowerPlugType;
import com.evcharger.architecture.exception.common.ResourceNotFoundException;
import com.evcharger.architecture.model.ApiResponse;
import com.evcharger.architecture.model.EVChargerDTO;
import com.evcharger.architecture.repository.EVChargerRepository;
import com.evcharger.architecture.repository.PowerOutputRepository;
import com.evcharger.architecture.repository.PowerPlugTypeRepository;
import com.evcharger.architecture.service.EVChargerService;
import com.evcharger.architecture.specification.EVChargerSpecification;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class EVChargerServiceImpl implements EVChargerService {

    @Autowired
    private EVChargerRepository evChargerRepository;

    @Autowired
    private PowerPlugTypeRepository powerPlugTypeRepository;

    @Autowired
    private PowerOutputRepository powerOutputRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Transactional
    @Override
    public EVChargerDTO createEVCharger(EVChargerDTO evChargerDTO) {
        log.info("Creating EV Charger with availability: {}", evChargerDTO.getAvailability());
        EVCharger evCharger = mapToEntity(evChargerDTO);
        evCharger.setAvailability(evChargerDTO.getAvailability());
        System.out.println("Creating EV Charger with availability:"+ evCharger.getAvailability());
        System.out.println("Creating EV Charger with availability:"+ evCharger.getChargerId());

        return mapToDto(evChargerRepository.save(evCharger));
    }

    @Override
    public EVChargerDTO getEVCharger(String id) {
        // TODO Auto-generated method stub
        EVCharger evCharger = evChargerRepository.findByChargerId(id)
                .orElseThrow(() -> new ResourceNotFoundException("EV Charger", "ChargerId", id));

        return mapToDto(evCharger);
    }

    @Override
    public EVChargerDTO updateEVCharger(String id, EVChargerDTO evChargerDTO) {
        EVCharger evCharger = evChargerRepository.findByChargerId(id)
                .orElseThrow(() -> new ResourceNotFoundException("EV Charger", "Id", id));

        evCharger.setAvailability(evChargerDTO.getAvailability());
        evCharger.setInstallationDate(evChargerDTO.getInstallationDate());
        evCharger.setLastMaintenanceDate(evChargerDTO.getLastMaintenanceDate());
        evCharger.setLocation(modelMapper.map(evChargerDTO.getLocation(), Location.class));
        evCharger.setNumberOfPorts(evChargerDTO.getNumberOfPorts());

        PowerOutput powerOutput = powerOutputRepository.findById(evChargerDTO.getPowerOutput().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Power Output", "Id",
                        evChargerDTO.getPowerOutput().getId()));
        evCharger.setPowerOutput(powerOutput);

        PowerPlugType powerPlugType = powerPlugTypeRepository.findById(evChargerDTO.getPowerPlugType().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Power Plug Type", "Id",
                        evChargerDTO.getPowerPlugType().getId()));
        evCharger.setPowerPlugType(powerPlugType);

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
        return modelMapper.map(evCharger, EVChargerDTO.class);
    }

    private EVCharger mapToEntity(EVChargerDTO evChargerDTO) {
        return modelMapper.map(evChargerDTO, EVCharger.class);
    }
}
