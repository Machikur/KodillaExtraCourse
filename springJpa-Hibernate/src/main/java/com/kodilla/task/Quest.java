package com.kodilla.task;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@Table(name = "quest")
public class Quest {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String status;

    @ManyToMany(targetEntity = Person.class)
    @JoinTable(name = "quests_persons",
            joinColumns = @JoinColumn(name = "quest_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set<Person> persons=new HashSet<>();

    @OneToMany(targetEntity = SubQuest.class)
    @JoinColumn(name = "subQuest_id")
    private Set<SubQuest> subQuests = new HashSet<>();

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
