package org.example.System;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String fname;
    @Column(nullable = false)
    private String lname;
    @Column
    private int accountId;
    //private BankAccount account;

    public User() { }

    public User(int id, String first, String second) {
        this.id = id;
        this.fname = first;
        this.lname = second;
        this.accountId = -1;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getId() {
        return id;
    }

    public void setAccount(int accId) {
        this.accountId = accId;
    }

    public String getFullname() {
        return fname + " " + lname;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public int getAccountId() {
        return accountId;
    }

    public void closeAccount(){
        this.accountId = -1;
    }

}
