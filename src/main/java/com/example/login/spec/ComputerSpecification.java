package com.example.login.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.example.login.entity.Computer;
import com.example.login.entity.Computer_;
import com.example.login.entity.Manufacture;
import com.example.login.entity.Manufacture_;

import jakarta.persistence.criteria.Join;

public class ComputerSpecification {

    public static Specification<Computer> hasName(String name) {
        return (root, query, cb) ->
            cb.like(root.get(Computer_.name), "%" + name + "%");
    }

    public static Specification<Computer> hasMaxPrice(BigDecimal price) {
        return (root, query, cb) ->
            cb.lessThanOrEqualTo(root.get(Computer_.price), price);
    }

    public static Specification<Computer> hasManufactureName(String manufactureName) {
        return (root, query, cb) -> {
            query.distinct(true);
            Join<Computer, Manufacture> manu = root.join(Computer_.manufacture);
            return cb.like(manu.get(Manufacture_.name), "%" + manufactureName + "%");
        };
    }
}