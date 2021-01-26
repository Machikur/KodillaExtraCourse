package com.kodilla.task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class SubQuest {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String status;

    @OneToMany(targetEntity = Person.class)
    private List<Person> persons = new ArrayList<>();

    public SubQuest(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public void addPerson(Person person) {
        persons.add(person);
    }


}