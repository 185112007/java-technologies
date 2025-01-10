package com.manning.javapersistence.ch02;

import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldJPATest {
    @Test
    public void storeLoadMessage(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch02");

        try {
            EntityManager em = emf.createEntityManager();

            /* save message */
            em.getTransaction().begin();

            Message message = new Message();
            message.setText("Hello World!");

            em.persist(message);

            em.getTransaction().commit();
            // INSERT INTO MESSAGE (ID, TEXT) VALUES (1, 'Hello World!')

            /* select & update message */
            em.getTransaction().begin();

            List<Message> messages = em.createQuery("select m from Message m", Message.class)
                    .getResultList();
            // SELECT * FROM MESSAGE

            messages.get(messages.size() - 1)
                    .setText("Hello World from JPA!");
            em.getTransaction().commit();
            // UPDATE MESSAGE set TEXT = 'Hello World from JPA!' WHERE ID = 1

            assertAll(
                    () -> assertEquals(1, messages.size()),
                    () -> assertEquals("Hello World from JPA!", messages.get(0).getText())
            );

            em.close();
        } finally {
            emf.close();
        }
    }
}
