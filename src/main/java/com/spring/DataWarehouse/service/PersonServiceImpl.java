package com.spring.DataWarehouse.service;

import com.spring.DataWarehouse.model.Person;
import com.spring.DataWarehouse.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> listAll() {
        List<Person> personList = new ArrayList<>();
        personRepository.findAll().forEach(personList::add);
        return personList;
    }

    @Override
    public Person insertPerson(Person person) {
        personRepository.save(person);
        return person;
    }
    @Override
    public void extractTest(){
        System.out.println("########Extract test########Debugging level 9000########");
    }

    @Override
    public void transformTest() {
        System.out.println("########Transform test########Debugging level 9000########");
    }

    @Override
    public void loadTest() {
        System.out.println("########Load test########Debugging level 9000########");
    }
}
