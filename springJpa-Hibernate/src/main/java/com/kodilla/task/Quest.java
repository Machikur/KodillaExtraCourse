package com.kodilla.task;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
public class Quest {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String status;

    @ManyToOne(targetEntity = Person.class)
    private Person person;

    @OneToMany(targetEntity = SubQuest.class)
    @JoinColumn(name = "subQuest_id")
    private Set<SubQuest> subQuests = new HashSet<>();

    public Quest(String name, String status, Person person) {
        this.name = name;
        this.status = status;
        this.person = person;
    }

    public void addSubQuest(SubQuest subQuest) {
        subQuests.add(subQuest);
    }

}
