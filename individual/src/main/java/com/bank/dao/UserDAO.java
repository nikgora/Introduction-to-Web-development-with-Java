package com.bank.dao;

import com.bank.model.User;
import com.bank.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDAO {

    public static void createUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(user); // persist вместо save
            transaction.commit();
        } catch(Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public static User getUserByUsername(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM User WHERE username = :username";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", username);
            return query.uniqueResult();
        }
    }

    public static void updateUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(user); // merge вместо update
            transaction.commit();
        } catch(Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public static void deleteUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // Если объект не управляется, сначала применяем merge
            User managedUser = session.merge(user);
            session.remove(managedUser); // remove вместо delete
            transaction.commit();
        } catch(Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public static void saveUser(User user) {
        // Если пользователь существует, выполняем merge, иначе persist
        if (getUserByUsername(user.getUsername()) == null) {
            createUser(user);
        } else {
            updateUser(user);
        }
    }
}
