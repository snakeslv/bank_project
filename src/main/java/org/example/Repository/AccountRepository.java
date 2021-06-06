package org.example.Repository;

import org.example.HibernateUtil;
import org.example.System.BankAccount;
import org.example.System.User;
import org.hibernate.Session;

import java.util.List;

public class AccountRepository {
    public static void insert(BankAccount acc){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        session.save(acc);
        session.getTransaction().commit();
    }

    public static void update(BankAccount acc){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        session.update(acc);
        session.getTransaction().commit();
    };

    public static void delete(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        BankAccount acc = (BankAccount) session.get(BankAccount.class, id);
        session.delete(acc);
        session.getTransaction().commit();
    };

    public static BankAccount get(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        BankAccount acc = (BankAccount) session.get(BankAccount.class, id);
        session.getTransaction().commit();
        return acc;
    };

    public static List<BankAccount> getList(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        List<BankAccount> accounts = session.createQuery("FROM BankAccount ").list();
        session.getTransaction().commit();
        return accounts;
    };
}
