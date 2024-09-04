package com.evcharger.architecture.service;

import java.util.List;

import com.evcharger.architecture.model.ApiResponse;
import com.evcharger.architecture.model.PowerOutputModel;

public interface PowerOutputService {

    PowerOutputModel createPowerOutput(PowerOutputModel powerOutput);

    PowerOutputModel getPowerOutputById(Long id);

    // AC3: Update Power Output
    PowerOutputModel updatePowerOutput(Long id, PowerOutputModel updatePowerOutput);

    // AC4: Delete Power Output
    void deletePowerOutput(Long id);

    // AC5: List Power Outputs
    ApiResponse<PowerOutputModel> listPowerOutputs(int pageNo, int pageSize, String sortBy, String sortDir);

}
