package com.manning.javapersistence.ch02;

import org.hibernate.cfg.Configuration;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class HelloWorldHibernateToJPATest {
    private static EntityManagerFactory createEntityManagerFactory(){
        Configuration configuration = new Configuration();
        configuration.configure().addAnnotatedClass(Message.class);

        Map<String, String> properties = new HashMap<>();
        Enumeration<?> propertyNames = configuration.getProperties().propertyNames();

        while(propertyNames.hasMoreElements()) {
            String element = (String) propertyNames.nextElement();
            properties.put(element, configuration.getProperties().getProperty(element));
        }

        return Persistence.createEntityManagerFactory("ch02", properties);
    }
}
