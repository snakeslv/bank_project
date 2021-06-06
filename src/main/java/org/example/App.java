package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.System.Bank;
import org.example.System.RegistrationUnit;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        RegistrationUnit.registerNewUser("Александр", "Иванов");
        RegistrationUnit.registerNewUser("Федор", "Петров");
        RegistrationUnit.openNewAccount(1);
        RegistrationUnit.openNewAccount(2);
        scene = new Scene(loadFXML("menu"), 700, 550);
        stage.setScene(scene);
        stage.setHeight(700);
        stage.setWidth(550);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}