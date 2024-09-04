package com.evcharger.architecture.repository;

import com.evcharger.architecture.entity.PowerPlugType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PowerPlugTypeRepository extends JpaRepository<PowerPlugType, Long> {
//    Page<PowerPlugType> findAllByPlugType(String plugType, Pageable pageable);
    Page<PowerPlugType> findAllByPlugType(String plugType, Pageable pageable);



}
