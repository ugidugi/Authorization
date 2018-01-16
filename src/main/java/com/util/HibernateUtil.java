package com.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private HibernateUtil(){}

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    private static SessionFactory buildSessionFactory(){
        return new Configuration().configure().buildSessionFactory();
    }
}
