package com.spring.DataWarehouse.repository;

import com.spring.DataWarehouse.model.Race;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RaceRepository extends CrudRepository<Race, Long> {
    Optional<Race> findByName(String name);
}
