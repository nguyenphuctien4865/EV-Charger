package com.evcharger.architecture.service;

import com.evcharger.architecture.entity.documents.EVChargerDocument;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ElasticSearchService {
    // Save a single EVChargerDocument
    EVChargerDocument save(EVChargerDocument evChargerDocument);

    // Save multiple EVChargerDocuments
    List<EVChargerDocument> saveAll(List<EVChargerDocument> evChargerDocuments);

    // Find by ID
    Optional<EVChargerDocument> findById(String id);

    // Delete by ID
    void deleteById(String id);

    // Search all EVChargerDocuments with pagination
    Page<EVChargerDocument> findAll(Pageable pageable);

    // Custom search (e.g., by availability or location) - Add more methods as needed
    Page<EVChargerDocument> searchByAvailability(String availability, Pageable pageable);
}