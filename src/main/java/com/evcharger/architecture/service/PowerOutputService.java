package com.evcharger.architecture.service;

import com.evcharger.architecture.model.ApiResponse;
import com.evcharger.architecture.model.PowerOutputDTO;

public interface PowerOutputService {

    PowerOutputDTO createPowerOutput(PowerOutputDTO powerOutput);

    PowerOutputDTO getPowerOutputById(Long id);

    // AC3: Update Power Output
    PowerOutputDTO updatePowerOutput(Long id, PowerOutputDTO updatePowerOutput);

    // AC4: Delete Power Output
    void deletePowerOutput(Long id);

    // AC5: List Power Outputs
    ApiResponse<PowerOutputDTO> listPowerOutputs(int pageNo, int pageSize, String sortBy, String sortDir);

}
