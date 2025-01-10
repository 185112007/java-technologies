package com.manning.javapersistence.ch02;

import org.hibernate.SessionFactory;

import jakarta.persistence.EntityManagerFactory;

public class HelloWorldJPAToHibernateTest {

    private static SessionFactory getSessionFactory(EntityManagerFactory emf) {
        return emf.unwrap(SessionFactory.class);
    }


}
