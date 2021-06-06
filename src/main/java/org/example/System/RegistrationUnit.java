package org.example.System;

import org.example.Repository.AccountRepository;
import org.example.Repository.UserRepository;

import java.util.Random;

public class RegistrationUnit {

    private static int userId = 0;
    private static int accId = 0;

    public static User registerNewUser(String fname, String lname){
        User user = new User(userId++,fname, lname);
        UserRepository.insert(user);
//        Bank.users.put(user.getId(), user);
        return user;
    }

    public static void openNewAccount(int userId){
        BankAccount account = new BankAccount(accId);
        account.open();
        account.setOwner(userId);
        AccountRepository.insert(account);
//        Bank.accounts.put(accId, account);
        User user = UserRepository.get(userId);
        user.setAccount(account.getId());
        UserRepository.update(user);
//        Bank.users.get(userId).setAccount(account.getId());
        accId++;
    }

    public static void closeAccount(int userId){
        User user = UserRepository.get(userId);
        BankAccount account = AccountRepository.get(user.getAccountId());
        account.close();
        AccountRepository.update(account);
        AccountRepository.delete(user.getAccountId());
        user.closeAccount();
        UserRepository.update(user);
//        Bank.users.get(userId).closeAccount();
    }

}
