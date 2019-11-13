package com.spring.DataWarehouse.repository;

import com.spring.DataWarehouse.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

}
