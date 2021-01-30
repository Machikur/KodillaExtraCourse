package com.kodilla;

import com.kodilla.task.Person;
import com.kodilla.task.Quest;
import com.kodilla.task.SubQuest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.*;
import javax.persistence.metamodel.Attribute;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@SpringBootTest
public class TaskTestSuite {

    private final Logger logger = Logger.getLogger(this.getClass().getName());
    @PersistenceUnit
    private EntityManagerFactory factory;

    @Test
    public void test() {
        //given
        EntityManager manager = factory.createEntityManager();

        List<Long> questsList = addEntities();

        //when
        System.out.println("\n");
        logger.info("Rozpoczynam odczytywanie obiektów\n");

        EntityGraph<Quest> eg = manager.createEntityGraph(Quest.class);
        eg.addSubgraph("subQuests").addSubgraph("persons");
        eg.addSubgraph("persons");

        TypedQuery<Quest> quest = manager.createQuery("FROM Quest WHERE id IN(" + joinIds(questsList) + ") " +
                        "",
                Quest.class);

        quest.setHint("javax.persistence.fetchgraph", eg);

        quest.getResultList().forEach(System.out::println);

        //then should be less hibernates actions then usually

        System.out.println("\n");
        logger.info("Wczytywanie zakończone");
    }

    private String joinIds(List<Long> list) {
        return list.stream()
                .map(id -> "" + id)
                .collect(Collectors.joining(","));
    }

    private List<Long> addEntities() {

        Person person1 = new Person("Janusz", "Nosacz");
        Person person2 = new Person("Janusz", "Kosacz");
        Person person3 = new Person("Janusz", "Nochal");
        Person person4 = new Person("Janusz", "Nochalos");
        Quest quest1 = new Quest("wynieść smieci", "do zrobienia");
        Quest quest2 = new Quest("Umyj okna", "do zrobienia");
        SubQuest subQuest = new SubQuest("Posprzątaj", "do zrobienia");
        SubQuest subQuest2 = new SubQuest("Zamieć", "do zrobienia");

        quest1.addPerson(person1);
        quest2.addPerson(person2);
        subQuest.addPerson(person3);
        subQuest2.addPerson(person4);
        quest2.addSubQuest(subQuest);
        quest1.addSubQuest(subQuest2);

        EntityManager manager = factory.createEntityManager();

        manager.getTransaction().begin();
        manager.persist(person1);
        manager.persist(person2);
        manager.persist(person3);
        manager.persist(person4);
        manager.persist(subQuest);
        manager.persist(subQuest2);
        manager.persist(quest1);
        manager.persist(quest2);
        manager.flush();
        manager.getTransaction().commit();
        manager.close();

        return Arrays.asList(quest1.getId(), quest2.getId());
    }

}
