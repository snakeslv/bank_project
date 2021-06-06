package org.example.System;

import org.example.Repository.AccountRepository;
import org.example.Repository.UserRepository;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "accounts")
public class BankAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private long money = 0;
    //private User owner;
    @Column
    private int ownerId;
    @Column
    private StringBuilder operationHistory = new StringBuilder();

    public BankAccount() {}

    public BankAccount(int i){
        this.id = i;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public StringBuilder getOperationHistory() {
        return operationHistory;
    }

    public void setOperationHistory(StringBuilder operationHistory) {
        this.operationHistory = operationHistory;
    }

    public void open(){
        operationHistory.append("Account opened! " + new Date().toString() + " \n");
    }

    public void close(){
        operationHistory.append("Account closed! " + new Date().toString() + " \n");
    }

    public int getId() {
        return id;
    }

    public long getMoney() {
        return money;
    }

    public int getOwnerId(){
        return ownerId;
    }

    public String getOwnerName(){
        return UserRepository.get(ownerId).getFullname();
    }

    public void setOwner(int id){
        this.ownerId = id;
    }

    public void deposit(long amount){
        money += amount;
        logDeposit(amount);
    }

    public void deposit(long amount, boolean transfer){
        money += amount;
    }

    public void withdraw(long amount) throws InsuffisientBalanceException {

        if(checkIfEnough(amount)) {
            money -= amount;
            logWithdraw(amount);
        }
        else throw new InsuffisientBalanceException();
    }

    public void transfer(int accId, long amount) throws InsuffisientBalanceException{
        if (checkIfEnough(amount)) {
            BankAccount acc = AccountRepository.get(accId);
            acc.deposit(amount, true);
//            Bank.accounts.get(accId).deposit(amount, true);
            money -= amount;
            logTransferTo(amount, acc.getOwnerName());
            acc.logTransferFrom(amount, getOwnerName());
            AccountRepository.update(acc);
        }
        else throw new InsuffisientBalanceException();
    }

    public boolean checkIfEnough(long amount){
        return amount <= money;
    }


    public void logDeposit(long amount){
        operationHistory.append("Deposit: " + amount + " " + new Date().toString() + " \n");
        operationHistory.append("Balance: " + getMoney() + " \n\n");
    }

    public void logTransferTo(long amount, String name){
        operationHistory.append("Transfer: " + amount + " to " + name + " " +
                                                                new Date().toString() + " \n");
        operationHistory.append("Balance: " + getMoney() + " \n\n");
    }

    public void logTransferFrom(long amount, String name){
        operationHistory.append("Transfer: " + amount + " from " + name + " " +
                new Date().toString() + " \n");
        operationHistory.append("Balance: " + getMoney() + " \n\n");
    }

    public void logWithdraw(long amount){
        operationHistory.append("Withdraw: " + amount + " " + new Date().toString() + " \n");
        operationHistory.append("Balance: " + getMoney() + " \n\n");
    }

    public String printOperationHistory(){
        return operationHistory.toString();
    }
}
