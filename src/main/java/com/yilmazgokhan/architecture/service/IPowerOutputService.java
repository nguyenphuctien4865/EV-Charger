package com.yilmazgokhan.architecture.service;

import java.util.List;

import com.yilmazgokhan.architecture.model.PowerOutputModel;


public interface IPowerOutputService {

    PowerOutputModel createPowerOutput(PowerOutputModel powerOutput);

    PowerOutputModel getPowerOutputById(Long id);

    // AC3: Update Power Output
    PowerOutputModel updatePowerOutput(Long id, PowerOutputModel powerOutput);

    // AC4: Delete Power Output
    void deletePowerOutput(Long id);

    // AC5: List Power Outputs
    List<PowerOutputModel> listPowerOutputs();

}
