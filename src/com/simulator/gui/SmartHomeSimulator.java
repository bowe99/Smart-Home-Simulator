package com.simulator.gui;
/**
  * This is the launch class of the simulator.
  */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class SmartHomeSimulator extends Application{
    public static void main(String[] args) {
       launch(args);
	}
	/**
     * Launch the dashboard of the simulator.
     * @param primaryStage Referring to a Stage, the top level container of a JavaFX object.
     * it contains the dashboard of the simulator, which is then used to open everything else.
     */
	@Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Scene rootScene = new Scene(root);
        primaryStage.setScene(rootScene);
        primaryStage.setTitle("Smart Home Simulator");
        primaryStage.show();
    }
}
