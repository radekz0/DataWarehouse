package com.spring.DataWarehouse.api;

import com.spring.DataWarehouse.model.Person;
import com.spring.DataWarehouse.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity addPerson(@RequestBody Person person){
        return new ResponseEntity<>(personService.insertPerson(person), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAll(){
        return new ResponseEntity<>(personService.listAll(), HttpStatus.OK);
    }
}
