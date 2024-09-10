package com.evcharger.architecture.service.impl;

import com.evcharger.architecture.exception.common.ResourceNotFoundException;
import com.evcharger.architecture.model.ApiResponse;
import com.evcharger.architecture.model.PowerOutputDTO;
import com.evcharger.architecture.entity.PowerOutput;
import com.evcharger.architecture.repository.PowerOutputRepository;
import com.evcharger.architecture.service.PowerOutputService;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PowerOutputServiceImpl implements PowerOutputService {

    private final ModelMapper modelMapper;
    private final PowerOutputRepository powerOutputRepository;

    @Override
    public PowerOutputDTO createPowerOutput(PowerOutputDTO powerOutput) {
        return mapToDto(powerOutputRepository.save(mapToEntity(powerOutput)));
    }

    @Override
    public PowerOutputDTO getPowerOutputById(Long id) {
        PowerOutput powerOutput = powerOutputRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Power Output", "Id", id));
        return mapToDto(powerOutput);
    }

    @Override
    public PowerOutputDTO updatePowerOutput(Long id, PowerOutputDTO updatePowerOutput) {
        PowerOutput powerOutput = powerOutputRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Power Output", "Id", id));

        powerOutput.setChargingSpeed(updatePowerOutput.getChargingSpeed().toString());
        powerOutput.setDescription(updatePowerOutput.getDescription());
        powerOutput.setOutputValue(updatePowerOutput.getOutputValue());
        powerOutput.setVoltage(updatePowerOutput.getVoltage());

        return mapToDto(powerOutputRepository.save(powerOutput));
    }

    @Override
    public void deletePowerOutput(Long id) {
        PowerOutput powerOutput = powerOutputRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Power Output", "Id", id));
        powerOutputRepository.delete(powerOutput);
    }

    @Override
    public ApiResponse<PowerOutputDTO> listPowerOutputs(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<PowerOutput> page = powerOutputRepository.findAll(pageable);
        List<PowerOutputDTO> powerOutputs = page.getContent()
                .stream().map(this::mapToDto).collect(Collectors.toList());

        ApiResponse<PowerOutputDTO> pageResponse = new ApiResponse<>();
        pageResponse.setContent(powerOutputs);
        pageResponse.setPageNumber(page.getNumber());
        pageResponse.setPageSize(page.getSize());
        pageResponse.setTotalElements(page.getTotalElements());
        pageResponse.setTotalPages(page.getTotalPages());
        pageResponse.setLast(page.isLast());
        pageResponse.setFirst(page.isFirst());
        pageResponse.setEmpty(page.isEmpty());

        log.info("Fetched {} power outputs", powerOutputs.size());

        return pageResponse;
    }

    private PowerOutputDTO mapToDto(PowerOutput powerOutput) {
        return modelMapper.map(powerOutput, PowerOutputDTO.class);
    }

    private PowerOutput mapToEntity(PowerOutputDTO powerOutputModel) {
        return modelMapper.map(powerOutputModel, PowerOutput.class);
    }
}
