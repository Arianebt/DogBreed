package com.example.dog_breed;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControllerView2 {

    @FXML
    private ImageView DogImage;

    @FXML
    private Label Breed;

    public void initData(String breedName, String breedInfo) {
        String breedInfoLowerCase = breedInfo.toLowerCase();
        String imageUrl = "https://api-ninjas.com/images/dogs/" + breedInfoLowerCase.replace(' ', '_') + ".jpg";
        Image dogImage = new Image(imageUrl, true);
        DogImage.setImage(dogImage);
        dogImage.errorProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                System.out.println("Failed to load the image: " + imageUrl);
            }
        });


        Breed.setText(breedInfo);


    }
}
