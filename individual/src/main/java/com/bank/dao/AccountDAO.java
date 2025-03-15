package com.bank.dao;

import com.bank.model.Account;
import com.bank.model.User;
import com.bank.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AccountDAO {

    public static List<Account> getAccountsByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Account WHERE user = :user";
            Query<Account> query = session.createQuery(hql, Account.class);
            query.setParameter("user", user);
            return query.list();
        }
    }

    public static void createAccount(Account account, User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            account.setUser(user);
            session.persist(account); // persist вместо save
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public static void deleteAccount(Account account) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // Чтобы удалить сущность, сначала нужно её получить, если она не управляется
            Account managedAccount = session.merge(account);
            session.remove(managedAccount); // remove вместо delete
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public static void updateAccount(Account account) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // merge возвращает управляемую сущность, которая заменяет старую
            session.merge(account); // merge вместо update
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public static boolean isExist(User user, String type) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Account WHERE user = :user AND type = :type";
            Query<Account> query = session.createQuery(hql, Account.class);
            query.setParameter("user", user);
            query.setParameter("type", type);
            return !query.list().isEmpty();
        }
    }

    public static boolean transferMoney(String fromType, String toType, User user, double amount) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "FROM Account WHERE user = :user AND type = :type";
            Account fromAccount = session.createQuery(hql, Account.class)
                    .setParameter("user", user)
                    .setParameter("type", fromType)
                    .uniqueResult();
            Account toAccount = session.createQuery(hql, Account.class)
                    .setParameter("user", user)
                    .setParameter("type", toType)
                    .uniqueResult();

            if (fromAccount == null || toAccount == null || fromAccount.getBalance() < amount) {
                transaction.rollback();
                return false;
            }

            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);
            // Применяем изменения через merge
            session.merge(fromAccount);
            session.merge(toAccount);

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
}
