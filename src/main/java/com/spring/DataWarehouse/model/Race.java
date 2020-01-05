package com.spring.DataWarehouse.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public Long id;

    @Transient
    private List<Person> personList;

    @Column(name="name")
    String name;

    @Column(name="type")
    String type;

    @Column(name="link")
    String link;

    @Column(name="average_time")
    String averageTime;

    public Race() {
    }

    public Race(List<Person> personList, String name, String type) {
        this.personList = personList;
        this.name = name;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(String averageTime) {
        this.averageTime = averageTime;
    }
}
