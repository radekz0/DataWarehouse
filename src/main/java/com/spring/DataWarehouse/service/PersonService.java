package com.spring.DataWarehouse.service;

import com.spring.DataWarehouse.model.Person;

import java.util.List;

public interface PersonService {
    List<Person> listAll();
    Person insertPerson(Person person);
    void extractTest();
    void transformTest();
    void loadTest();
}
