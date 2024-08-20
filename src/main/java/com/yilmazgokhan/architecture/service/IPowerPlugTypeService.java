package com.yilmazgokhan.architecture.service;

import com.yilmazgokhan.architecture.entity.PowerPlugType;
import com.yilmazgokhan.architecture.exception.common.InvalidParamException;
import com.yilmazgokhan.architecture.model.powerPlugType.PowerPlugTypeModel;

import java.util.List;

public interface IPowerPlugTypeService {
    PowerPlugType createPowerPlugType(PowerPlugTypeModel plugType) throws InvalidParamException;
    PowerPlugType getPowerPlugTypeById(Long id) throws InvalidParamException;
    PowerPlugType updatePowerPlugType(Long id, PowerPlugTypeModel updatedPlugType) throws InvalidParamException;
    void deletePowerPlugType(Long id) throws InvalidParamException;
    List<PowerPlugType> listPowerPlugTypes(String plugType);
}