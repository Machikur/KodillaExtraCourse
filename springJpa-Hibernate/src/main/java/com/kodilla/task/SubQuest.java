package com.kodilla.task;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Data
public class SubQuest {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String status;

    @ManyToOne(targetEntity = Person.class)
    private Person person;

    public SubQuest(String name, String status, Person person) {
        this.name = name;
        this.status = status;
        this.person = person;
    }
}