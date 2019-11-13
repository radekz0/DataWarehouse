package com.spring.DataWarehouse.dao;

import com.spring.DataWarehouse.model.Person;

import java.util.List;
import java.util.UUID;

public interface PersonDao {

    int insertPerson(UUID id, Person person);   //allows us to insert person with a given id

    default int insertPerson(Person person){       //generates id automatically
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    List<Person> selectAllPeople();
}