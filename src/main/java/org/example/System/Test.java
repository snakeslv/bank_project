package org.example.System;


import org.example.Repository.AccountRepository;
import org.example.Repository.UserRepository;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

// Класс для тестирования работы некоторых методов
public class Test {

    public static void main(String[] args) throws Exception{
//        SessionFactory sf = buildSessionFactory();
//        Session session = sf.openSession();
//        session.getTransaction().begin();
//        User user1 = RegistrationUnit.registerNewUser("Vasya", "Pupking");
//        session.saveOrUpdate(user1);
//        session.getTransaction().commit();
//        session.close();
//        sf.close();
        User user1 = RegistrationUnit.registerNewUser("Vasya", "Pupking");
        User user2 = RegistrationUnit.registerNewUser("Kolya", "Fedotov");
        RegistrationUnit.openNewAccount(1);
        RegistrationUnit.openNewAccount(2);
        System.out.println(UserRepository.get(1).getAccountId());
        System.out.println(UserRepository.get(2).getAccountId());
        System.out.println(AccountRepository.get(1).getOperationHistory());

//        Iterator<User> iterator = Bank.users.values().iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next().getAccountId());
//        }
    }

    public static SessionFactory buildSessionFactory(){
            try{
                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
                Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
                return metadata.getSessionFactoryBuilder().build();
            } catch (Throwable ex){
                System.err.println("QEQWEWQE" + ex);
                System.err.println("STACKTRACE: + \n\n");
                ex.printStackTrace();
                throw new ExceptionInInitializerError(ex);
            }
        }
}

