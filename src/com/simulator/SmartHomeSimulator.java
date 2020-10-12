package com.simulator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class SmartHomeSimulator extends Application{
    public static void main(String[] args) {
       launch(args);
	}
	
	@Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
