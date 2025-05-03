package com.tacos.inventory.config;

import org.hibernate.SessionFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class HibernateSessionFactoryProducer {

    private final SessionFactory sessionFactory;

    public HibernateSessionFactoryProducer() {
        this.sessionFactory = new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    @Produces
    public SessionFactory produceSessionFactory() {
        return sessionFactory;
    }
}
