package com.kodilla.task;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Quest {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String status;

    @OneToMany(targetEntity = Person.class)
    private List<Person> persons = new ArrayList<>();

    @OneToMany(targetEntity = SubQuest.class)
    private List<SubQuest> subQuests = new ArrayList<>();

    public Quest(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    public void addSubQuest(SubQuest subQuest) {
        subQuests.add(subQuest);
    }

}
