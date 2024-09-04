package com.evcharger.architecture.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.evcharger.architecture.exception.CustomExceptionHandler;
import com.evcharger.architecture.exception.common.ResourceNotFoundException;
import com.evcharger.architecture.model.ApiResponse;
import com.evcharger.architecture.model.PowerOutputModel;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.evcharger.architecture.entity.PowerOutput;
import com.evcharger.architecture.repository.PowerOutputRepository;
import com.evcharger.architecture.service.PowerOutputService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PowerOutputServiceImpl implements PowerOutputService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PowerOutputRepository powerOutputRepository;


    @Override
    public PowerOutputModel createPowerOutput(PowerOutputModel powerOutput) {
        // TODO Auto-generated method stub
        return mapToDto(powerOutputRepository.save(mapToEntity(powerOutput)));

    }

    @Override
    public PowerOutputModel getPowerOutputById(Long id) {
        PowerOutput powerOutput = powerOutputRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Power Output", "Id", id));

        return mapToDto(powerOutput);
    }

    @Override
    public PowerOutputModel updatePowerOutput(Long id, PowerOutputModel updatePowerOutput) {
        // TODO Auto-generated method stub
        PowerOutput powerOutput = powerOutputRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Power Output", "Id", id));

        powerOutput.setChargingSpeed(updatePowerOutput.getChargingSpeed());
        powerOutput.setDescription(updatePowerOutput.getDescription());
        powerOutput.setOutputValue(updatePowerOutput.getOutputValue());
        powerOutput.setVoltage(updatePowerOutput.getVoltage());

        return mapToDto(powerOutputRepository.save(powerOutput));
    }

    @Override
    public void deletePowerOutput(Long id) {
        // TODO Auto-generated method stub
        PowerOutput powerOutput = powerOutputRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Power Output", "Id", id));

        powerOutputRepository.delete(powerOutput);
    }

    @Override
    public ApiResponse<PowerOutputModel> listPowerOutputs(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        // Implement the logic to fetch and return the list of power outputs
        // Example:
        Page<PowerOutput> page = powerOutputRepository.findAll(pageable);
        List<PowerOutputModel> powerOutputs = page.getContent()
                .stream().map(this::mapToDto).collect(Collectors.toList());

        ApiResponse<PowerOutputModel> pageResponse = new ApiResponse<>();
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

    private PowerOutputModel mapToDto(PowerOutput powerOutput) {

        PowerOutputModel powerOutputModel = modelMapper.map(powerOutput, PowerOutputModel.class);
        return powerOutputModel;
    }

    private PowerOutput mapToEntity(PowerOutputModel powerOutputModel) {

        PowerOutput powerOutput = modelMapper.map(powerOutputModel, PowerOutput.class);
        return powerOutput;
    }

}
