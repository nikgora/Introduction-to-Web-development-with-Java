package com.bank.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HibernateUtil {
    private static final Logger logger = Logger.getLogger(HibernateUtil.class.getName());
    private static SessionFactory sessionFactory;

    static {
        try {
            buildSessionFactory();
        } catch (Throwable ex) {
            logger.log(Level.SEVERE, "Initial SessionFactory creation failed", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static void buildSessionFactory() {
        Configuration configuration = new Configuration();

        try {
            configuration.configure(); // Load hibernate.cfg.xml
            logger.info("Hibernate configuration loaded successfully");

            // Database URL handling
            String dbUrl = System.getenv("DB_URL");
            if (dbUrl == null) {
                dbUrl = System.getProperty("db_url");
                logger.info("Using system property db_url: " + (dbUrl != null ? "found" : "not found"));
            } else {
                logger.info("Using environment variable DB_URL");
            }

            if (dbUrl != null) {
                configuration.setProperty("hibernate.connection.url", dbUrl);
            } else {
                logger.warning("No DB_URL environment variable or db_url system property found. Using default from hibernate.cfg.xml");
            }

            sessionFactory = configuration.buildSessionFactory();
            logger.info("SessionFactory created successfully");
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "SessionFactory creation failed", ex);
            throw ex;
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}