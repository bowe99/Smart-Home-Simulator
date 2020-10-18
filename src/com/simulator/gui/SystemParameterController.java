package com.simulator.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.simulator.gui.SmartHomeSimulator;

public class SystemParameterController {

    @FXML private TextField temperatureValue;
    @FXML private Button confirmValue;  

    @FXML
    void setTemperature (MouseEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            Parent root = loader.load();
            SmartHomeSimulatorController SHSController = loader.getController();
            SHSController.setTemperature(temperatureValue.getText()+"°C");
            temperatureValue.getScene().setRoot(root);
           }

        catch (Exception e){
        e.printStackTrace();
        }
    }
    
}
