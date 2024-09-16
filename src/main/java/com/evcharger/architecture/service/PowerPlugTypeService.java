package com.evcharger.architecture.service;

import com.evcharger.architecture.exception.common.InvalidParamException;
import com.evcharger.architecture.model.ApiResponse;
import com.evcharger.architecture.model.PowerPlugTypeDTO;


public interface PowerPlugTypeService {
    PowerPlugTypeDTO createPowerPlugType(PowerPlugTypeDTO plugType) throws InvalidParamException;

    PowerPlugTypeDTO getPowerPlugTypeById(Long id) throws InvalidParamException;

    PowerPlugTypeDTO updatePowerPlugType(Long id, PowerPlugTypeDTO updatedPlugType) throws InvalidParamException;

    void deletePowerPlugType(Long id);

    public ApiResponse<PowerPlugTypeDTO> listPowerPlugTypes(int pageNo, int pageSize, String sortBy, String sortDir, String plugType);
}