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
import org.example.System.*;

import java.io.IOException;

public class GetBalanceController {

    @FXML
    Label owner;
    @FXML
    Label balance;
    @FXML
    Label getBalanceException;
    @FXML
    TextArea operationHist;
    @FXML
    TextField accountIdField;

    private InfoSaver saver = InfoSaver.getInstance();

    public void initialize() {
        setNumericField(accountIdField);
        operationHist.setEditable(false);
    }

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
        HibernateUtil.shutdown();
    }

    @FXML
    private void switchToInfoUnit() throws IOException {
        App.setRoot("infounit");
    }

    // Метод для ограничения ввода в поле accountIdField(Только числа)
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
    private void getAccInfo() throws IOException {
        int accid = Integer.parseInt(accountIdField.getText());
        try{
            owner.setText(AccountRepository.get(accid).getOwnerName());
            balance.setText(Long.toString(AccountRepository.get(accid).getMoney()));
            operationHist.setText(AccountRepository.get(accid).printOperationHistory());
        }
        catch (NullPointerException e) {
            getBalanceException.setText("Ошибка! данного аккаунта не существует");
        }

    }
}
