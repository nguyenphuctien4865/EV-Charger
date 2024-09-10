package com.evcharger.architecture.service.impl;

import com.evcharger.architecture.entity.PowerPlugType;
import com.evcharger.architecture.exception.CustomExceptionHandler;
import com.evcharger.architecture.exception.common.InvalidParamException;
import com.evcharger.architecture.exception.common.ResourceNotFoundException;
import com.evcharger.architecture.model.ApiResponse;
import com.evcharger.architecture.model.PowerPlugTypeDTO;
import com.evcharger.architecture.repository.PowerPlugTypeRepository;
import com.evcharger.architecture.service.PowerPlugTypeService;

import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PowerPlugTypeServiceImpl implements PowerPlugTypeService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PowerPlugTypeRepository repository;

    public PowerPlugType createPowerPlugType(PowerPlugType plugType) throws InvalidParamException {
        // Validate input and handle business logic here
        return repository.save(plugType);
    }

    @Override
    @Transactional(readOnly = true)
    public PowerPlugTypeDTO getPowerPlugTypeById(Long id) throws InvalidParamException {
        return mapToDto(
                repository.findById(id).orElseThrow(() -> new InvalidParamException("Power Plug Type not found")));
    }

    public PowerPlugTypeDTO updatePowerPlugType(Long id, PowerPlugTypeDTO updatedPlugType)
            throws InvalidParamException {

        PowerPlugType powerPlugType = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PowerPlugType", "id", id));

        powerPlugType.setAdditionalNotes(updatedPlugType.getAdditionalNotes());
        powerPlugType.setPlugImage(updatedPlugType.getPlugImage());
        powerPlugType.setPlugType(updatedPlugType.getPlugType());
        powerPlugType.setPowerModel(updatedPlugType.getPowerModel());
        powerPlugType.setUsedInRegions(updatedPlugType.getUsedInRegions());

        return mapToDto(repository.save(powerPlugType));
    }

    // @Override
    // public void deletePowerPlugType(Long id) throws InvalidParamException {
    // if (!repository.existsById(id)) {
    // throw new InvalidParamException("Power Plug Type not found");
    // }
    // repository.deleteById(id);
    // }

    @Override
    public void deletePowerPlugType(Long id) {
        PowerPlugType post = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        repository.delete(post);
    }

    public ApiResponse<PowerPlugTypeDTO> listPowerPlugTypes(int pageNo, int pageSize, String sortBy, String sortDir,
            String plugType) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<PowerPlugType> page;
        
        if (plugType != null && !plugType.isEmpty()) {
            page = repository.findAllByPlugType(plugType, pageable);
        } else {
            page = repository.findAll(pageable);
        }

        List<PowerPlugTypeDTO> content = page.getContent()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        ApiResponse<PowerPlugTypeDTO> pageResponse = new ApiResponse<>();
        pageResponse.setContent(content);
        pageResponse.setPageNumber(page.getNumber());
        pageResponse.setPageSize(page.getSize());
        pageResponse.setTotalElements(page.getTotalElements());
        pageResponse.setTotalPages(page.getTotalPages());
        pageResponse.setLast(page.isLast());
        pageResponse.setFirst(page.isFirst());
        pageResponse.setEmpty(page.isEmpty());

        log.info("Fetched {} power plug types", content.size());
        return pageResponse;
    }

    @Override
    public PowerPlugTypeDTO createPowerPlugType(PowerPlugTypeDTO plugType) throws InvalidParamException {
        // TODO Auto-generated method stub
        return mapToDto(repository.save(mapToEntity(plugType)));
    }

    public PowerPlugType mapToEntity(PowerPlugTypeDTO pluginType) {
        return mapper.map(pluginType, PowerPlugType.class);
    }

    public PowerPlugTypeDTO mapToDto(PowerPlugType pluginType) {
        return mapper.map(pluginType, PowerPlugTypeDTO.class);
    }
}
