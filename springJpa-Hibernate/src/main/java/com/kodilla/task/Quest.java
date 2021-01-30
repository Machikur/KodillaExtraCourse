package com.kodilla.task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@ToString
@Getter
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
    private Set<Person> persons = new HashSet<>();

    @OneToMany(targetEntity = SubQuest.class)
    @JoinColumn(name = "subQuest_id")
    private Set<SubQuest> subQuests = new HashSet<>();

    public Quest(String name, String status) {
        this.name = name;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quest quest = (Quest) o;
        return id != null &&
                Objects.equals(id, quest.id) &&
                Objects.equals(name, quest.name) &&
                Objects.equals(status, quest.status);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    public void addSubQuest(SubQuest subQuest) {
        subQuests.add(subQuest);
    }

}
