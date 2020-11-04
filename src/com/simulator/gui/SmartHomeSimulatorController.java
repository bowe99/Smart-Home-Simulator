package com.simulator.gui;
/**
  * This is the controller class for the Dashboard.fxml file
  */
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


public class SmartHomeSimulatorController {

    @FXML private ToggleButton simulationToggle;
    @FXML private Button editButton;
    @FXML private Button roomsControlPanelButton;
    @FXML private Label displayTemp;
    @FXML private Label displayDate;
    @FXML private Label UserProfile;
    @FXML private Label UserLocation;
    @FXML private Label displayTime;

    /**
     * Changes the simulation status to on or off
     * @param event Referring to a mouse activity by the user
     */
    @FXML
    void changeSimulationStatus(MouseEvent event) {
        if(simulationToggle.isSelected()){
            this.simulationToggle.setText("On");
        } else {
            this.simulationToggle.setText("Off");
        }
    }
    /**
     * Opens the Edit Button from the dashboard.
     * @param event Referring to a mouse activity by the user
     * Calls in the SystemParameter controller to get the user desired values,
     * and changes the values on the dashboard.
     */
    @FXML
    void openEditor(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ParameterEditor.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initOwner(simulationToggle.getScene().getWindow());
            stage.setTitle("Edit Parameters");
            stage.setScene(new Scene(root1));  

            stage.showAndWait();

            SystemParameterController controllerValues = fxmlLoader.getController();
            this.setTemperature(controllerValues.getTemperature());
            this.setDate(controllerValues.getDate());
            this.setLocation(controllerValues.getLocation());
            this.setProfile(controllerValues.getProfile());
            this.setTime(controllerValues.getTime());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    
    }

    //Open the RoomControlPanel window
    @FXML
    void openRoomControlPanel(MouseEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RoomControls.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initOwner(simulationToggle.getScene().getWindow());
            stage.setTitle("Rooms Control Panel");
            stage.setScene(new Scene(root1));  

            stage.showAndWait();
            RoomControlsController roomsControl = fxmlLoader.getController();

            /*
            SystemParameterController controllerValues = fxmlLoader.getController();
            this.setTemperature(controllerValues.getTemperature());
            this.setDate(controllerValues.getDate());
            this.setLocation(controllerValues.getLocation());
            this.setProfile(controllerValues.getProfile());
            this.setTime(controllerValues.getTime());
            */
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    private void setTemperature(String temperature) {
        this.displayTemp.setText(temperature);
    }

    @FXML
    private void setDate(String date) {
        this.displayDate.setText(date);
    }

    @FXML
    private void setLocation(String location) {
        this.UserLocation.setText(location);
    }

    @FXML
    private void setProfile(String profile) {
        this.UserProfile.setText(profile);
    }
    
    @FXML
    private void setTime(String time){
        this.displayTime.setText(time);
    }
}
