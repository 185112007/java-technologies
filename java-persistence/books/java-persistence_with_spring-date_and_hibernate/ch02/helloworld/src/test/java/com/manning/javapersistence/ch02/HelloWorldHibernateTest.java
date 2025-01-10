package com.manning.javapersistence.ch02;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.Test;

import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldHibernateTest {
    private static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();

        configuration.configure().addAnnotatedClass(Message.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Test
    public void storeLoadMessage() {
        try (SessionFactory sessionFactory = createSessionFactory();
             Session session = sessionFactory.openSession()) {

            /* save */
            session.beginTransaction();

            Message message = new Message();
            message.setText("Hello World from Hibernate!");

            session.persist(message);

            session.getTransaction().commit();
            // INSERT INTO MESSAGE (ID, TEXT) VALUES (1, 'Hello World from Hibernate!')

            /* list & update */
            session.beginTransaction();
            CriteriaQuery<Message> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Message.class);
            criteriaQuery.from(Message.class);

            List<Message> messages = session.createQuery(criteriaQuery).getResultList();
            // SELECT * FROM MESSAGE

            messages.get(messages.size() - 1)
                            .setText("Hello World from Hibernate!!!");

            session.getTransaction().commit();
            // UPDATE MESSAGE SET TEXT = 'Hello World from Hibernate!!!' WHERE ID = 1

            assertAll(
                    () -> assertEquals(1, messages.size()),
                    () -> assertEquals("Hello World from Hibernate!!!", messages.get(0).getText())
            );
        }
    }
}
