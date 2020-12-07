package com.simulator.controller;

import com.simulator.model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * This is the controller class for the ParametorEditor.fxml file
 */
public class SimulationParameterController {

    @FXML private TextField temperatureValue;
    @FXML private Button confirmValue;
    @FXML private DatePicker dateValue;
    @FXML private ComboBox<String> currentUserChoice = new ComboBox<String>();
    @FXML private ComboBox<String> userProfileChoice = new ComboBox<String>();
    @FXML private ComboBox<String> userLocationChoice = new ComboBox<String>();
    @FXML private TextField hourValue;
    @FXML private TextField minuteValue;
    @FXML private TextField timeSpeed;
    @FXML private Button cancelBtn;

    private House house;
    private SimulationParameters simulation;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Instantiates a new Simulation parameter controller.
     *
     */
    public SimulationParameterController() {
        try{
            house = House.getInstance();
            simulation = SimulationParameters.getInstance();
        }
        catch (HouseLoadException houseLoadException){
            houseLoadException.printStackTrace();
            System.out.println(houseLoadException.getMessage());
        }
    }

    /**
     * Initializes the SimulationsParamterController popout window
     */
    @FXML
    public void initialize(){
        currentUserChoice.setItems(FXCollections.observableList(SimulationParameters.getInstance().getAllUserNames()));
        userLocationChoice.setItems(FXCollections.observableList(house.getRoomsNameList()));
        userProfileChoice.setItems(FXCollections.observableList(SimulationParameters.getInstance().getAllUserNames()));
        currentUserChoice.setValue(simulation.getCurrentUser().getName());
        userProfileChoice.setValue(simulation.getCurrentUser().getName());
        userLocationChoice.setValue(simulation.getCurrentUser().getCurrentRoom().getName());
        temperatureValue.setText(String.valueOf(simulation.getTemperature()));
        dateValue.getEditor().setText(dateFormat.format(simulation.getDate()));
        hourValue.setText(String.valueOf(simulation.getTime()/3600));
        minuteValue.setText(String.valueOf((simulation.getTime()%3600)/60));
        timeSpeed.setText(String.valueOf(1000/simulation.getTimeInterval()));
    }

    /**
     * Populate data of the simulation parameters instance.
     *
     * @param event Referring to a mouse activity by the user Sets the system parameters to the demanded value.
     */
    @FXML
    void returnData (MouseEvent event){
        Logger log = Logger.getInstance();
        try{
            simulation.setTemperature(Double.parseDouble(temperatureValue.getText()));
            log.outputToConsole("Temperature was successfully updated");
        }
        catch (NumberFormatException e){
            log.outputToConsole("Error updating temperature, please try again");
        }

        try{
            simulation.setDate(dateFormat.parse(dateValue.getEditor().getText()));
            log.outputToConsole("Date was successfully updated");
        }
        catch (ParseException e){
            log.outputToConsole("Error updating date, please try again");
        }

        Profile user = simulation.getUserByName((String)(currentUserChoice.getValue()));
        if (user != null){
            simulation.setCurrentUser(user);
            log.outputToConsole("Current user is now: " + user.getName());
        }
        else
            log.outputToConsole("selected user does not exist within the simulation");

        user = simulation.getUserByName(userProfileChoice.getValue());
        Room room = house.getRoomByName((String)(userLocationChoice.getValue()));
        if (user != null && room != null) {
            simulation.setUserLocation(user.getName(), room);
            log.outputToConsole(user.getName() + " has been moved to " + room.getName());
        }
        else if(user == null)
            log.outputToConsole("selected user does not exist within the simulation");
        else if(room == null)
            log.outputToConsole("selected room does not exist within the simulation");

        try{
            int hour = Integer.parseInt(hourValue.getText());
            int min = Integer.parseInt(minuteValue.getText());
            if (hour >= 0 && hour < 24) {
                if (min >= 0 && min < 60) {
                    simulation.setTime(hour * 3600 + min * 60);
                    log.outputToConsole("Time has been updated successfully");
                }
                else{
                   log.outputToConsole("Minute entry does not fall within an acceptable range");
                }
            }
            else{
                log.outputToConsole("Hour entry does not fall within an acceptable range");
            }
        }
        catch (Exception e){
            log.outputToConsole("Error updating time, please try again");
        }
        try{
            int speedFactor = Integer.parseInt(timeSpeed.getText());
            simulation.setTimeInterval(speedFactor);
            log.outputToConsole("Time speed has been updated to run at " + speedFactor + "x real time");
        }
        catch (Exception e){
            log.outputToConsole("Error updating time speed, please try again");
        }
        closeWindow(event);
    }

    /**
     * Closes the parameters edit window pop-up.
     *
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
