package com.evcharger.architecture.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evcharger.architecture.entity.EVCharger;
import com.evcharger.architecture.entity.Location;
import com.evcharger.architecture.entity.OperatingHours;
import com.evcharger.architecture.entity.PowerOutput;
import com.evcharger.architecture.entity.PowerPlugType;
import com.evcharger.architecture.entity.documents.EVChargerDocument;
import com.evcharger.architecture.entity.documents.LocationDocument;
import com.evcharger.architecture.entity.documents.OperatingHoursDocument;
import com.evcharger.architecture.entity.documents.PowerOutputDocument;
import com.evcharger.architecture.entity.documents.PowerPlugTypeDocument;
import com.evcharger.architecture.repository.ESEVChargerRepository;
import com.evcharger.architecture.repository.EVChargerRepository;

@Service
public class DataSynchronizationService {

    private static final Logger logger = LoggerFactory.getLogger(DataSynchronizationService.class);

    @Autowired
    private EVChargerRepository evChargerRepository;

    @Autowired
    private ESEVChargerRepository esEVChargerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    private void configureModelMapper() {
        modelMapper.createTypeMap(Location.class, LocationDocument.class)
                .addMappings(mapper -> {
                    mapper.map(Location::getName, LocationDocument::setName);
                    mapper.map(Location::getStreetAddress, LocationDocument::setStreetAddress);
                    mapper.map(Location::getDistrict, LocationDocument::setDistrict);
                    mapper.map(Location::getCityProvince, LocationDocument::setCityProvince);
                    mapper.map(Location::getCountry, LocationDocument::setCountry);
                    mapper.map(Location::getPostalCode, LocationDocument::setPostalCode);
                    mapper.map(Location::getPricing, LocationDocument::setPricing);
                    mapper.map(Location::getPhoneNumber, LocationDocument::setPhoneNumber);
                    mapper.map(Location::getParkingLevel, LocationDocument::setParkingLevel);
                    mapper.using(ctx -> new GeoPoint(
                            ((Location) ctx.getSource()).getLatitude(),
                            ((Location) ctx.getSource()).getLongitude()))
                            .map(src -> src, LocationDocument::setLocation);
                })
                .setPostConverter(context -> {
                    mapOperatingHours(context.getSource(), context.getDestination());
                    return context.getDestination();
                });

        modelMapper.createTypeMap(EVCharger.class, EVChargerDocument.class)
                .addMappings(mapper -> {
                    mapper.map(EVCharger::getChargerId, EVChargerDocument::setChargerId);
                    mapper.map(EVCharger::getNumberOfPorts, EVChargerDocument::setNumberOfPorts);
                    mapper.map(EVCharger::getAvailability, EVChargerDocument::setAvailability);
                    mapper.map(EVCharger::getInstallationDate, EVChargerDocument::setInstallationDate);
                    mapper.map(EVCharger::getLastMaintenanceDate, EVChargerDocument::setLastMaintenanceDate);
                    mapper.map(src -> src.getLocation(), EVChargerDocument::setLocation);
                    mapper.map(src -> src.getPowerOutput(), EVChargerDocument::setPowerOutput);
                    mapper.map(src -> src.getPowerPlugType(), EVChargerDocument::setPowerPlugType);
                });

        modelMapper.createTypeMap(OperatingHours.class, OperatingHoursDocument.class);
        modelMapper.createTypeMap(PowerOutput.class, PowerOutputDocument.class);
        modelMapper.createTypeMap(PowerPlugType.class, PowerPlugTypeDocument.class);
    }

    @Transactional(readOnly = true)
    @Scheduled(fixedRate = 30 * 1000)
    public void synchronizeData() {
        logger.info("Starting data synchronization...");

        try {
            List<EVCharger> evChargers = evChargerRepository.findAll();
            List<EVChargerDocument> esEVChargers = evChargers.stream()
                    .map(this::convertToESEVCharger)
                    .collect(Collectors.toList());

            esEVChargerRepository.saveAll(esEVChargers);
            logger.info("Synchronization completed successfully!");

        } catch (Exception e) {
            logger.error("Error occurred during data synchronization", e);
        }
    }

    private EVChargerDocument convertToESEVCharger(EVCharger evCharger) {
        return modelMapper.map(evCharger, EVChargerDocument.class);

    }

    private void mapOperatingHours(Location source, LocationDocument destination) {
        if (source.getOperatingHours() != null) {
            List<OperatingHoursDocument> operatingHoursDocs = source.getOperatingHours().stream()
                    .map(oh -> modelMapper.map(oh, OperatingHoursDocument.class))
                    .collect(Collectors.toList());
            destination.setOperatingHours(operatingHoursDocs);
        }
    }
}