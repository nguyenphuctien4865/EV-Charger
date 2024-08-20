package com.yilmazgokhan.architecture.service.impl;


import com.yilmazgokhan.architecture.entity.PowerPlugType;
import com.yilmazgokhan.architecture.exception.common.InvalidParamException;
import com.yilmazgokhan.architecture.model.powerPlugType.PowerPlugTypeModel;
import com.yilmazgokhan.architecture.repository.PowerPlugTypeRepository;
import com.yilmazgokhan.architecture.service.IPowerPlugTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PowerPlugTypeService implements IPowerPlugTypeService {
    @Autowired
    private PowerPlugTypeRepository repository;

    public PowerPlugType createPowerPlugType(PowerPlugType plugType) throws InvalidParamException {
        // Validate input and handle business logic here
        return repository.save(plugType);
    }

    public PowerPlugType getPowerPlugTypeById(Long id) throws InvalidParamException {
        return repository.findById(id).orElseThrow(() -> new InvalidParamException("Power Plug Type not found"));
    }

    public PowerPlugType updatePowerPlugType(Long id, PowerPlugType updatedPlugType) throws InvalidParamException {
        if (!repository.existsById(id)) {
            throw new InvalidParamException("Power Plug Type not found");
        }
        updatedPlugType.setId(id);
        return repository.save(updatedPlugType);
    }

    public void deletePowerPlugType(Long id) throws InvalidParamException {
        if (!repository.existsById(id)) {
            throw new InvalidParamException("Power Plug Type not found");
        }
        repository.deleteById(id);
    }

    public List<PowerPlugType> listPowerPlugTypes(String plugType) {
        if (plugType != null && !plugType.isEmpty()) {
            return repository.findByPlugTypeContaining(plugType);
        }
        return repository.findAll();
    }

    @Override
    public PowerPlugType createPowerPlugType(PowerPlugTypeModel plugType) throws InvalidParamException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createPowerPlugType'");
    }

    @Override
    public PowerPlugType updatePowerPlugType(Long id, PowerPlugTypeModel updatedPlugType) throws InvalidParamException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePowerPlugType'");
    }
}
