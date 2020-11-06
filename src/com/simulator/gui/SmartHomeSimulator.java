package com.simulator.gui;

import com.simulator.model.House;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
  * This is the launch class of the simulator.
  */
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

        House house = House.getInstance();
        //House house2 = HouseLayoutParser.loadFile("house_layout_txt.txt");

        System.out.println(house.toString());

        House house2 = House.getInstance();
        House house3 = House.getInstance();
        House house4 = House.getInstance();
        House house5 = House.getInstance();


        House.getInstance().addRoom("new room");
        House.getInstance().addRoom("new room 2");

        System.out.println(House.getInstance().toString());

    
    }
}
