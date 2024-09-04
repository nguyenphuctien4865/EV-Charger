package com.evcharger.architecture.specification;

import org.springframework.data.jpa.domain.Specification;

import com.evcharger.architecture.entity.EVCharger;

public class EVChargerSpecification {
    public static Specification<EVCharger> hasLocationId(String locationId) {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("locationId")), locationId.toLowerCase());
    }

    public static Specification<EVCharger> hasPowerPlugTypeId(String powerPlugTypeId) {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("powerPlugTypeId")), powerPlugTypeId.toLowerCase());
    }

    public static Specification<EVCharger> hasPowerOutputId(String powerOutputId) {
        return (root, query, cb) -> cb.equal(root.get("powerOutputId"), powerOutputId);
    }
}
