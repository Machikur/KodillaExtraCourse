package com.kodilla.task;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@Table(name = "sub_quest")
public class SubQuest {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String status;

    @ManyToMany(targetEntity = Person.class)
    @JoinTable(name = "sub_quests_persons",
            joinColumns = @JoinColumn(name = "sub_quest_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set<Person> persons = new HashSet<>();

    public SubQuest(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public void addPerson(Person person) {
        persons.add(person);
    }


    @Override
    public int hashCode() {
        return Objects.hash( name, status, persons);
    }
}