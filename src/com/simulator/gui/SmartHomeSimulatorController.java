package com.simulator.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SmartHomeSimulatorController {
    @FXML private ToggleButton simulationToggle;
    @FXML private Button editButton;

    @FXML
    void changeSimulationStatus(MouseEvent event) {
        if(simulationToggle.isSelected()){
            this.simulationToggle.setText("On");
        } else {
            this.simulationToggle.setText("Off");
        }
    }

    @FXML
    void openEditor(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ParameterEditor.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Edit Parameters");
            stage.setScene(new Scene(root1));  
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
