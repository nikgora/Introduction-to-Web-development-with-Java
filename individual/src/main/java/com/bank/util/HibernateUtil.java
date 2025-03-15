package com.bank.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure();
            // Получаем значение из переменной окружения или системного свойства
            String dbUrl = System.getenv("DB_URL");
            if (dbUrl == null) {
                dbUrl = System.getProperty("db_url");
            }
            if (dbUrl != null) {
                configuration.setProperty("hibernate.connection.url", dbUrl);
            }
            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Инициализация SessionFactory не удалась: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
