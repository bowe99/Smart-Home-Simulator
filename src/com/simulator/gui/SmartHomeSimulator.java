package src.com.simulator.gui;

import src.com.simulator.model.House;
import src.com.simulator.model.HouseLayoutParser;
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
        Scene rootScene = new Scene(root);
        primaryStage.setScene(rootScene);
        primaryStage.setTitle("Smart Home Simulator");
        primaryStage.show();

        House house = HouseLayoutParser.loadFile("house_layout_txt.txt");
        System.out.println(house.toString());
    
    }
}
