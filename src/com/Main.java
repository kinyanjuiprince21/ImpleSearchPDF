package com;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("search.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("ImpleSearchExcelPDF");
        Image icon = new Image("com/resource/deep-learning.png");
        stage.getIcons().add(icon);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
