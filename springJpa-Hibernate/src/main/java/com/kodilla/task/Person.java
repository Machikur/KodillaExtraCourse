package com.kodilla.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@ToString
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
