package org.example.Repository;

import org.example.HibernateUtil;
import org.example.System.User;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

public class UserRepository {

    public static void insert(User user){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        session.save(user);
        session.getTransaction().commit();
    }

    public static void update(User user){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        session.update(user);
        session.getTransaction().commit();
    };

    public static void delete(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
    };

    public static User get(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        User user = (User) session.get(User.class, id);
        session.getTransaction().commit();
        return user;
    };

    public static List<User> getList(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        List<User> users = session.createQuery("FROM User").list();
        session.getTransaction().commit();
        return users;
    };

}
