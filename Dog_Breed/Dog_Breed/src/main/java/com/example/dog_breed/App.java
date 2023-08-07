package com.example.dog_breed;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        primaryStage.setTitle("Dog Breed Search App");

        String iconPath = "file:///C:/Users/arian/OneDrive/Área%20de%20Trabalho/Dog_Breed/src/main/java/com/example/dog_breed/img/icon.png";
        Image iconImage = new Image(iconPath);
        primaryStage.getIcons().add(iconImage);

        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}