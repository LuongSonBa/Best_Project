package com.example.login.spec;

import org.springframework.data.jpa.domain.Specification;

import com.example.login.entity.Computer;

import jakarta.persistence.criteria.Join;

public class ComputerSpecification {

    public static Specification<Computer> hasName(String name) {
        return (root, query, cb) ->
            cb.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Computer> hasMaxPrice(Double price) {
        return (root, query, cb) ->
            cb.lessThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Computer> hasManufactureName(String manufactureName) {
        return (root, query, cb) -> {
            query.distinct(true);
            Join<Object, Object> manu = root.join("manufacture");
            return cb.like(manu.get("name"), "%" + manufactureName + "%");
        };
    }
}