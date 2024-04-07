package com.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {
        if (null == sessionFactory) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            return sessionFactory;
        }
        return sessionFactory;
    }
}
