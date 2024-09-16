package com.evcharger.architecture.service.impl;

import java.util.List;
import java.util.Optional;

import com.evcharger.architecture.util.enums.Availability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.evcharger.architecture.entity.documents.EVChargerDocument;
import com.evcharger.architecture.repository.ESEVChargerRepository;
import com.evcharger.architecture.service.ElasticSearchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

    @Autowired
    private ESEVChargerRepository evChargerDocumentRepository;
    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchServiceImpl.class);

    // Save a single EVChargerDocument
    @Override
    public EVChargerDocument save(EVChargerDocument evChargerDocument) {
        return evChargerDocumentRepository.save(evChargerDocument);
    }

    // Save multiple EVChargerDocuments
    @Override
    public List<EVChargerDocument> saveAll(List<EVChargerDocument> evChargerDocuments) {
        return (List<EVChargerDocument>) evChargerDocumentRepository.saveAll(evChargerDocuments);
    }

    // Find by ID
    @Override
    public Optional<EVChargerDocument> findById(String id) {
        return evChargerDocumentRepository.findById(id);
    }

    // Delete by ID
    @Override
    public void deleteById(String id) {
        evChargerDocumentRepository.deleteById(id);
    }

    // Find all with pagination
    @Override
    public Page<EVChargerDocument> findAll(Pageable pageable) {
        return evChargerDocumentRepository.findAll(pageable);
    }

    // Search by availability with pagination
    @Override
    public Page<EVChargerDocument> searchByAvailability(String availability, Pageable pageable) {
        try {
            Availability availabilityEnum = Availability.valueOf(availability.toUpperCase());
            return evChargerDocumentRepository.findByAvailability(availabilityEnum, pageable);
        } catch (IllegalArgumentException e) {
            // Handle invalid availability string
            logger.error("Invalid availability value: " + availability, e);
            return Page.empty(pageable);
        }
    }
}