package com.kodilla.task;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String surName;

    public Person(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }
}
