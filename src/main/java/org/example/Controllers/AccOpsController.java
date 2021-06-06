package org.example.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.HibernateUtil;
import org.example.Repository.AccountRepository;
import org.example.System.Bank;
import org.example.System.BankAccount;
import org.example.System.InsuffisientBalanceException;

import java.io.IOException;

public class AccOpsController {

    @FXML
    Label owner;
    @FXML
    Label balance;
    @FXML
    Label enterEx;
    @FXML
    Label withEx;
    @FXML
    Label transferEx;
    @FXML
    TextField accIdField;
    @FXML
    TextField depositField;
    @FXML
    TextField withdrawField;
    @FXML
    TextField recIdField;
    @FXML
    TextField recAmountField;
    @FXML
    TextArea opsHistory;

    private int accId;
    private int userId;
    private BankAccount account;

    public void initialize(){
        setNumericField(accIdField);
        setNumericField(depositField);
        setNumericField(withdrawField);
        setNumericField(recIdField);
        setNumericField(recAmountField);
    }

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
        HibernateUtil.shutdown();
    }

    // Метод для ограничения ввода в поля (Только числа)
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
    private void updBalance() throws IOException {
        balance.setText(Long.toString(AccountRepository.get(accId).getMoney()));
    }

    @FXML
    private void enterAcc() throws IOException {
        try{
            accId = Integer.parseInt(accIdField.getText());
            account = AccountRepository.get(accId);
            userId = account.getOwnerId();
            owner.setText(account.getOwnerName());
            balance.setText(Long.toString(account.getMoney()));
            opsHistory.setEditable(false);
            printHistory();
            enterEx.setText("Вход выполнен успешно");
        } catch (NullPointerException e) {enterEx.setText("Ошибка! Не существует аккаунта с данным ID, либо он закрыт");}

    }

    @FXML
    private void deposit() throws IOException {
        long sum = Long.parseLong(depositField.getText());
        BankAccount acc = AccountRepository.get(accId);
        acc.deposit(sum);
        AccountRepository.update(acc);
        updBalance();
        printHistory();
        depositField.clear();
    }

    @FXML
    private void withdraw() throws IOException{
        try {
            long sum = Long.parseLong(withdrawField.getText());
            BankAccount acc = AccountRepository.get(accId);
            acc.withdraw(sum);
            AccountRepository.update(acc);
            updBalance();
            printHistory();
            withdrawField.clear();
            withEx.setText("");
        } catch (InsuffisientBalanceException e) {withEx.setText("Недостаточно средств на счете!");}

    }

    @FXML
    private void transfer() throws IOException {
        try {
            int recId = Integer.parseInt(recIdField.getText());
            long sum = Long.parseLong(recAmountField.getText());
            BankAccount acc = AccountRepository.get(accId);
            acc.transfer(recId, sum);
            AccountRepository.update(acc);
            transferEx.setText("");
        } catch (InsuffisientBalanceException e) {transferEx.setText("Недостаточно средств на счете!");}
        catch (NullPointerException e) {transferEx.setText("Пользователя с данным ID не существует");}
        updBalance();
        printHistory();
        recIdField.clear();
        recAmountField.clear();
    }

    @FXML
    private void printHistory() throws IOException {
        opsHistory.setText(AccountRepository.get(accId).printOperationHistory());
    }

}
