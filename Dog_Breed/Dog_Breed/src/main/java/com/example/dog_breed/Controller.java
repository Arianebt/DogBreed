package com.example.dog_breed;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.image.Image;


public class Controller {

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private Button SearchBreed;

    @FXML
    private TextField TypeBreed;

    @FXML
    private FlowPane ReturnData;

    private APIClient apiClient = new APIClient();

    @FXML
    private void initialize() {
        SearchBreed.setOnAction(this::handleSearchBreed);
    }

    @FXML
    private void handleSearchBreed(ActionEvent event) {
        String breedName = TypeBreed.getText();
        if (!breedName.isEmpty()) {
            try {
                String breedInfo = apiClient.getBreedInfoByName(breedName);
                if (breedInfo != null && !breedInfo.isEmpty()) {
                    Hyperlink breedLink = new Hyperlink(breedInfo);
                    ReturnData.getChildren().clear();
                    ReturnData.getChildren().add(breedLink);

                    // Set action on the breedLink to open ControllerView2
                    breedLink.setOnAction(e -> {
                        try {
                            openView2WithBreedInfo(breedName, breedInfo);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });

                } else {
                    ReturnData.getChildren().clear();
                    ReturnData.getChildren().add(new Label("Sorry, we still do not know this breed! Try a different one"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ReturnData.getChildren().clear();
        }
    }



    private void openView2WithBreedInfo(String breedName, String breedInfo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("View2.fxml"));
        Parent view2Root = loader.load();
        ControllerView2 controllerView2 = loader.getController();
        controllerView2.initData(breedName, breedInfo);

        Stage stage = new Stage();
        stage.setTitle("Breed Information");

        String iconPath = "file:///C:/Users/arian/OneDrive/Área%20de%20Trabalho/Dog_Breed/src/main/java/com/example/dog_breed/img/icon.png";
        Image iconImage = new Image(iconPath);
        stage.getIcons().add(iconImage);

        stage.setScene(new Scene(view2Root));
        stage.show();
    }
}
