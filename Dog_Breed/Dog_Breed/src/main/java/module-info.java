module com.example.dog_breed {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.google.gson;


    opens com.example.dog_breed to javafx.fxml;
    exports com.example.dog_breed;
}