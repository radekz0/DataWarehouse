package com.spring.DataWarehouse.dao;

import com.spring.DataWarehouse.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("collectionDao")
public class CollectionDao implements PersonDao{
    private List<Person> personDb = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        personDb.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return personDb;
    }
}