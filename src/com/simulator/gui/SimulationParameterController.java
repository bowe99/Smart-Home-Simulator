package com.simulator.gui;
 /**
  * This is the controller class for the ParametorEditor.fxml file
  */
import com.simulator.model.House;
import com.simulator.model.SimulationParameters;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;

public class SimulationParameterController {

    @FXML private TextField temperatureValue;
    @FXML private Button confirmValue;
    @FXML private DatePicker dateValue;
    @FXML private ChoiceBox userProfileChoice;
    @FXML private ChoiceBox userLocationChoice;
    @FXML private TextField theTime;
    @FXML private Button cancelBtn;

    private House house;
    private SimulationParameters simulation;

    public SimulationParameterController() throws Exception{
        house = House.getInstance();
        simulation = SimulationParameters.getInstance();
    }
    /**
     * Populate data of the simulation parameters instance.
     * @param event Referring to a mouse activity by the user
     * Sets the system parameters to the demanded value.
     */
    @FXML
    void returnData (MouseEvent event){
        try{
            simulation.setTemperature(Integer.parseInt(temperatureValue.getText()));
            SimpleDateFormat initialFormat = new SimpleDateFormat("yyyy-MM-dd");
            simulation.setDate(initialFormat.parse(dateValue.getEditor().getText()));
            simulation.setCurrentUser(simulation.getUserByName((String)(userProfileChoice.getValue())));
            simulation.setCurrentUserLocation(house.getRoomByName((String)(userLocationChoice.getValue())));
            simulation.setTime(Integer.parseInt((String)(theTime.getText())));
            closeWindow(event);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
        /**
     * Closes the parameters edit window pop-up.
     * @param event Referring to a mouse activity by the user
     */
    @FXML
    void closeWindow (MouseEvent event){
        try{
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
           }
        catch (Exception e){
        e.printStackTrace();
        }
    }
}
