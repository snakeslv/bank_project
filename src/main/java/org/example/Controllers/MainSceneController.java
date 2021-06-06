package org.example.Controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import org.example.App;
import org.example.HibernateUtil;

public class MainSceneController {

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
        HibernateUtil.shutdown();
    }

    @FXML
    private void switchToAccountOperations() throws IOException {
        App.setRoot("accops");
    }

    @FXML
    private void switchToRegistrationUnit() throws IOException {
        App.setRoot("regunit");
    }

    @FXML
    private void switchToInformationUnit() throws IOException {
        App.setRoot("infounit");
    }

    @FXML
    private void switchToHelp() throws IOException {
        App.setRoot("help");
    }
}
