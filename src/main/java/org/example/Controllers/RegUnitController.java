package org.example.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.LabeledSkinBase;
import org.example.App;
import org.example.HibernateUtil;
import org.example.System.RegistrationUnit;

import java.io.IOException;

public class RegUnitController {

    @FXML
    Label result;
    @FXML
    TextField nameField;
    @FXML
    TextField surnameField;


    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
        HibernateUtil.shutdown();
    }

    @FXML
    private void switchToRegUnit() throws IOException {
        App.setRoot("regunit");
    }

    @FXML
    private void switchToRegClient() throws IOException {
        App.setRoot("regclient");
    }

    @FXML
    private void switchToAccountOpen() throws IOException {
        App.setRoot("regacc");
    }


    @FXML
    private void regClient() throws IOException {
        RegistrationUnit.registerNewUser(nameField.getText(), surnameField.getText());
        result.setText("Клиент " + nameField.getText() + " " + surnameField.getText() + " успешно зарегистрирован");
    }



}
