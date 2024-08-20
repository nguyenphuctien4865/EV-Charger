package com.yilmazgokhan.architecture.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.yilmazgokhan.architecture.entity.PowerOutput;
import com.yilmazgokhan.architecture.exception.common.ResourceNotFoundException;
import com.yilmazgokhan.architecture.model.PowerOutputModel;
import com.yilmazgokhan.architecture.repository.PowerOutputRepository;
import com.yilmazgokhan.architecture.service.IPowerOutputService;

public class PowerOutputService implements IPowerOutputService {

    private ModelMapper modelMapper;

    @Autowired
    private PowerOutputRepository powerOutputRepository;

    @Override
    public PowerOutputModel getPowerOutputById(Long id) {
        PowerOutput powerOutput = powerOutputRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found", "Id", id));
        return mapToDto(powerOutput);
    }

    @Override
    public PowerOutputModel updatePowerOutput(Long id, PowerOutputModel powerOutput) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePowerOutput'");
    }

    @Override
    public void deletePowerOutput(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePowerOutput'");
    }

    @Override
    public List<PowerOutputModel> listPowerOutputs() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listPowerOutputs'");
    }

    private PowerOutputModel mapToDto(PowerOutput powerOutput) {

        PowerOutputModel powerOutputModel = modelMapper.map(powerOutput, PowerOutputModel.class);
        return powerOutputModel;
    }

    private PowerOutput mapToEntity(PowerOutputModel powerOutputModel) {

        PowerOutput powerOutput = modelMapper.map(powerOutputModel, PowerOutput.class);
        return powerOutput;
    }

    @Override
    public PowerOutputModel createPowerOutput(PowerOutputModel powerOutput) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createPowerOutput'");
    }

}
