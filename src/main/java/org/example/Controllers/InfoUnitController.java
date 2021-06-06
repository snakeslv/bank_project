package org.example.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import org.example.App;
import org.example.HibernateUtil;
import org.example.Repository.AccountRepository;
import org.example.Repository.UserRepository;
import org.example.System.Bank;
import org.example.System.InfoSaver;
import org.example.System.RegistrationUnit;
import org.example.System.User;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class InfoUnitController {

    @FXML
    Label exportUsExc;
    @FXML
    Label exportAccExc;
    @FXML
    Label importAccExc;
    @FXML
    TextArea infoAll;
    @FXML
    TextField userIdSaver;
    @FXML
    TextField userIdReader;
    @FXML
    TextField accIdSaver;


    private InfoSaver saver = InfoSaver.getInstance();

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
        HibernateUtil.shutdown();
    }

    @FXML
    private void switchToInfoUnit() throws IOException {
        App.setRoot("infounit");
    }

    @FXML
    private void switchToGetBalance() throws IOException {
        App.setRoot("getbalance");

    }

    @FXML
    private void switchToAllInfo() throws IOException {
        App.setRoot("allinfo");
    }

    @FXML
    private void switchToExport() throws IOException {
        App.setRoot("infosaver");
    }

    @FXML
    private void switchToImport() throws IOException {
        App.setRoot("inforeader");
    }

    @FXML
    private void getAllInfo() throws IOException {
        infoAll.setText(Bank.accountsInfo());
        infoAll.setEditable(false);
    }

    @FXML
    private void saveUser() throws IOException {
        try{
            int usId = Integer.parseInt(userIdSaver.getText());
            saver.saveUser(UserRepository.get(usId));
            exportUsExc.setText("Пользователь успешно сохранен в папке Users");
        } catch (NullPointerException e) {exportUsExc.setText("Пользователя с данным ID не существует");}

    }

    @FXML
    private void saveAccount() throws IOException {
        try {
            int accId = Integer.parseInt(accIdSaver.getText());
            saver.saveAccount(AccountRepository.get(accId));
            exportAccExc.setText("Аккаунт успешно сохранен в папке Accounts");
        } catch (NullPointerException e) {exportAccExc.setText("Аккаунта с данным ID не существует или он закрыт");}

    }

    @FXML
    private void readUser() throws IOException, ClassNotFoundException {
        try {
            String filename = userIdReader.getText();
            User user = saver.readUser(filename);
            RegistrationUnit.registerNewUser(user.getFname(), user.getLname());
            importAccExc.setText("Пользователь успешно добавлен в систему!");
        } catch (NoSuchFileException e) {importAccExc.setText("Не найден файл с таким именем");}

    }
}
