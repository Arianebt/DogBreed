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
        stage.setScene(new Scene(view2Root));
        stage.show();
    }
}


//
//    private void searchBreed() {
//        String breedName = breedNameField.getText().trim();
//        if (!breedName.isEmpty()) {
//            try {
//                String apiResponse = apiClient.getBreedInfoByName(breedName);
//                // Parse the JSON response and extract the breed information
//                DogBreed breed = parseApiResponse(apiResponse);
//                if (breed != null) {
//                    // Update the UI components with the retrieved data
//                    breedImageView.setImage(new Image(breed.getImageUrl()));
//                    energyLabel.setText("Energy: " + breed.getEnergy());
//                    protectivenessLabel.setText("Protectiveness: " + breed.getProtectiveness());
//                    trainabilityLabel.setText("Trainability: " + breed.getTrainability());
//                    barkingLabel.setText("Barking: " + breed.getBarking());
//                    maxLifeExpLabel.setText("Max Life Expectancy: " + breed.getMaxLifeExp());
//                    maxWeightLabel.setText("Max Weight: " + breed.getMaxWeight());
//                } else {
//                    // Handle case when the breed is not found or API response is invalid
//                    // Clear labels and show an error message or take appropriate action
//                    breedImageView.setImage(null);
//                    energyLabel.setText("Not found");
//                    protectivenessLabel.setText("Not Found");
//                    trainabilityLabel.setText("Not Found");
//                    barkingLabel.setText("Not Found");
//                    maxLifeExpLabel.setText("Not Found");
//                    maxWeightLabel.setText("Not Found");
//                }
//            } catch (Exception e) {
//                // Handle API connection errors or failed data retrieval
//                e.printStackTrace();
//            }
//        }
//    }
//
//    // Implement the method to parse the JSON API response and create DogBreed object
//    private DogBreed parseApiResponse(String apiResponse) {
//        // You need to parse the JSON response and extract relevant breed information
//        // For example, you can use libraries like Jackson or Gson to do this.
//        // Once you extract the information, create and return a DogBreed object.
//        return null; // Replace this with the actual DogBreed object.
//    }
//}
//

