package com.kodilla.task;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@ToString
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubQuest subQuest = (SubQuest) o;
        return id != null &&
                Objects.equals(id, subQuest.id) &&
                Objects.equals(name, subQuest.name) &&
                Objects.equals(status, subQuest.status);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}