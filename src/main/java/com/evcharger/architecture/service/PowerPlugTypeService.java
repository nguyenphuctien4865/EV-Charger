package com.evcharger.architecture.service;

import com.evcharger.architecture.exception.common.InvalidParamException;
import com.evcharger.architecture.model.ApiResponse;
import com.evcharger.architecture.model.powerPlugType.PowerPlugTypeModel;

import java.util.List;

public interface PowerPlugTypeService {
    PowerPlugTypeModel createPowerPlugType(PowerPlugTypeModel plugType) throws InvalidParamException;

    PowerPlugTypeModel getPowerPlugTypeById(Long id) throws InvalidParamException;

    PowerPlugTypeModel updatePowerPlugType(Long id, PowerPlugTypeModel updatedPlugType) throws InvalidParamException;

    void deletePowerPlugType(Long id);

    public ApiResponse<PowerPlugTypeModel> listPowerPlugTypes(int pageNo, int pageSize, String sortBy, String sortDir, String plugType);
}