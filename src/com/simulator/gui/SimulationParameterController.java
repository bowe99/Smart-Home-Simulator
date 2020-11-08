package com.simulator.gui;

import com.simulator.model.House;
import com.simulator.model.Profile;
import com.simulator.model.Room;
import com.simulator.model.SimulationParameters;
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
    @FXML private Button cancelBtn;

    private House house;
    private SimulationParameters simulation;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public SimulationParameterController() throws Exception{
        house = House.getInstance();
        simulation = SimulationParameters.getInstance();
    }

    @FXML
    public void initialize(){
        currentUserChoice.setItems(FXCollections.observableList(SimulationParameters.getInstance().getAllUserNames()));
        userLocationChoice.setItems(FXCollections.observableList(House.getInstance().getRoomsNameList()));
        userProfileChoice.setItems(FXCollections.observableList(SimulationParameters.getInstance().getAllUserNames()));
        currentUserChoice.setValue(simulation.getCurrentUser().getName());
        userProfileChoice.setValue(simulation.getCurrentUser().getName());
        userLocationChoice.setValue(simulation.getCurrentUser().getCurrentRoom().getName());
        temperatureValue.setText(String.valueOf(simulation.getTemperature()));
        dateValue.getEditor().setText(dateFormat.format(simulation.getDate()));
        hourValue.setText(String.valueOf(simulation.getTime()/60));
        minuteValue.setText(String.valueOf(simulation.getTime()%60));


        
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
        }
        catch (NumberFormatException e){
            System.out.println("Error parsing temperature");
        }

        try{
            simulation.setDate(dateFormat.parse(dateValue.getEditor().getText()));
        }
        catch (ParseException e){
            System.out.println("Error parsing date");
        }

        Profile user = simulation.getUserByName((String)(currentUserChoice.getValue()));
        if (user != null)
            simulation.setCurrentUser(user);
        else
            System.out.println("entered user does not exist within the simulation");

        user = simulation.getUserByName(userProfileChoice.getValue());
        Room room = house.getRoomByName((String)(userLocationChoice.getValue()));
        if (user != null && room != null)
            simulation.setUserLocation(user.getName(), room);

        try{
            int hour = Integer.parseInt(hourValue.getText());
            int min = Integer.parseInt(minuteValue.getText());
            if (hour >= 0 && hour < 24) {
                if (min >= 0 && min < 60)
                    simulation.setTime(hour * 60 + min);
                else
                    System.out.println("Minute entry does not fall within an acceptable range");
            }
            else
                System.out.println("Hour entry does not fall within an acceptable range");
        }
        catch (Exception e){
            System.out.println("Error parsing time");
        }
        closeWindow(event);
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
