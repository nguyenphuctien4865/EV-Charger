package com.evcharger.architecture.service;

import com.evcharger.architecture.model.ApiResponse;
import com.evcharger.architecture.model.EVChargerDTO;

public interface EVChargerService {
    EVChargerDTO createEVCharger(EVChargerDTO evChargerDTO);

    EVChargerDTO getEVCharger(String id);

    EVChargerDTO updateEVCharger(String id, EVChargerDTO evChargerDTO);

    void deleteEVCharger(String id);

    ApiResponse<EVChargerDTO> listEVChargers(int pageNo, int pageSize, String sortBy, String sortDir,

            String locationId, String powerPlugTypeId, String powerOutputId);
}
