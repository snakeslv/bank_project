package org.example.System;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InfoSaver {

    private String pathUser = "src\\main\\java\\org\\example\\Users";
    private String pathAcc = "src\\main\\java\\org\\example\\Accounts";

    private InfoSaver() {};

    private static InfoSaver instance;

    public static InfoSaver getInstance() {
        if (instance == null) {
            instance = new InfoSaver();
        }
        return instance;
    }

    public String getPathUser() {
        return pathUser;
    }

    public String getPathAcc() { return pathAcc; }

    public void saveUser(User user) throws IOException {
        File userDir = new File(getPathUser());
        userDir.mkdir();
        Path path = Paths.get(userDir + "\\user" + user.getId() + ".txt");
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
            oos.writeObject(user);
        }
    }

    public void saveAccount(BankAccount account) throws IOException {
        File accDir = new File(getPathAcc());
        accDir.mkdir();
        Path path = Paths.get(accDir + "\\account" + account.getId() + ".txt");
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
            oos.writeObject(account);
        }
    }

    public User readUser(String filename) throws IOException, ClassNotFoundException {
        User desUser;
        Path path = Paths.get(getPathUser() + "\\" + filename + ".txt");
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
            desUser = (User) ois.readObject();
        }
        return desUser;
    }
}
