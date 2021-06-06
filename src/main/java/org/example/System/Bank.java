package org.example.System;

import org.example.Repository.AccountRepository;
import org.example.Repository.UserRepository;

import java.util.*;

public class Bank {

    public static final Map<Integer, User> users = new HashMap<>();
    public static final Map<Integer, BankAccount> accounts = new HashMap<>();

    public static long checkBalanceById(int id){
        return accounts.get(id).getMoney();
    }


    public static String accountsInfo(){
        List<User> users = UserRepository.getList();
        StringBuilder res = new StringBuilder();
        res.append("Bank accounts info:  \n");
        for(User user : users){
            res.append("User id: " + user.getId() + " \n");
            res.append("First name: " + user.getFname()  + " \n");
            res.append("Last name: " + user.getLname()  + " \n");
            if (user.getAccountId() == -1) res.append("This user has no account yet \n\n");
            else {
                BankAccount account = AccountRepository.get(user.getAccountId());
                res.append("Account id: " + account.getId() + " \n");
                res.append("Balance: " + account.getMoney() + " \n\n");
            }

        }
        return res.toString();
    }

}
