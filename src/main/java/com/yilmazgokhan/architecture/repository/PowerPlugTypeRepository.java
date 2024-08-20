package com.yilmazgokhan.architecture.repository;

import com.yilmazgokhan.architecture.entity.PowerPlugType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PowerPlugTypeRepository extends JpaRepository<PowerPlugType, Long> {
    List<PowerPlugType> findByPlugTypeContaining(String plugType);


}
