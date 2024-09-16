package com.evcharger.architecture.repository;

import com.evcharger.architecture.util.enums.Availability;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.evcharger.architecture.entity.documents.EVChargerDocument;
import org.springframework.stereotype.Repository;

@Repository
public interface ESEVChargerRepository extends ElasticsearchRepository<EVChargerDocument, String> {
        Page<EVChargerDocument> findByAvailability(Availability availability, Pageable pageable);

}
