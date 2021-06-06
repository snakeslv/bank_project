package org.example.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.HibernateUtil;
import org.example.System.RegistrationUnit;

import java.io.IOException;

public class RegAccController {
    @FXML
    Label result;
    @FXML
    TextField clientIdField;

    public void initialize(){
        setNumericField(clientIdField);
    }

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
        HibernateUtil.shutdown();
    }

    @FXML
    private void switchToRegUnit() throws IOException {
        App.setRoot("regunit");
    }

    // Метод для ограничения ввода в поле clientIdField(Только числа)
    private void setNumericField(TextField textField){
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @FXML
    private void openAccount() throws IOException {
        try {
            RegistrationUnit.openNewAccount(Integer.parseInt(clientIdField.getText()));
            result.setText("На клиента " + clientIdField.getText() + " открыт новый аккаунт");
        }
        catch (NullPointerException e) { result.setText("Клиента с данным ID не существует!"); }

    }

    @FXML
    private void closeAccount() throws IOException {
        try {
            RegistrationUnit.closeAccount(Integer.parseInt(clientIdField.getText()));
            result.setText("Счет клиента " + clientIdField.getText() + " успешно закрыт");
        } catch (NullPointerException e) {result.setText("Клиента с данным ID не существует либо его аккаунт уже закрыт!");}

    }
}
