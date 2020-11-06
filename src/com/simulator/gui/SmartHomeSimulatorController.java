package com.simulator.gui;
import com.simulator.model.Profile;
import com.simulator.model.Room;
import javafx.application.Platform;
/**
  * This is the controller class for the Dashboard.fxml file
  */
import com.simulator.model.House;
import com.simulator.model.SimulationParameters;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;


public class SmartHomeSimulatorController {

    @FXML private ToggleButton simulationToggle;
    @FXML private Button editButton;
    @FXML private Button roomsControlPanelButton;
    @FXML private Label displayTemp;
    @FXML private Label displayDate;
    @FXML private Label userProfile;
    @FXML private Label userLocation;
    @FXML private Label displayTime;
    private House house;
    private SimulationParameters simulation;

    //todo save simulation context (profiles, variables, etc.) in text file
    //todo implement/fix to string methods for classes to the saved


    public SmartHomeSimulatorController()
    {
        house = House.getInstance();
        simulation = SimulationParameters.getInstance();

        //load permissions?
        try {

        }
        catch(Exception e){
            System.out.println("Error parsing permissions");
        }
    }

    @FXML
    public void initialize(){
        setTemperature(simulation.getTemperature());
        setDate(simulation.getDate());
        setLocation(simulation.getCurrentUser().getCurrentRoom());
        setProfile(simulation.getCurrentUser());
        setTime(simulation.getTime());
    }

    /**
     * Changes the simulation status to on or off
     * @param event Referring to a mouse activity by the user
     */
    @FXML
    void changeSimulationStatus(MouseEvent event) {
        if(simulationToggle.isSelected()){
            this.simulationToggle.setText("On");
            simulation.setSimulationStatus(true);
        } else {
            this.simulationToggle.setText("Off");
            simulation.setSimulationStatus(false);
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

            setTemperature(simulation.getTemperature());
            setDate(simulation.getDate());
            setLocation(simulation.getCurrentUser().getCurrentRoom());
            setProfile(simulation.getCurrentUser());
            setTime(simulation.getTime());
        }
        catch (Exception e){
            e.printStackTrace();
        }  
    }
    @FXML
    void openRoomControls(MouseEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RoomControls.fxml"));
            Parent root2 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initOwner(simulationToggle.getScene().getWindow());
            stage.setTitle("Rooms Control Panel");
            stage.setScene(new Scene(root2));  
            stage.showAndWait();

            //todo update changes following closure of the RoomControlPanel

        }
        catch (Exception e){
            e.printStackTrace();
            Platform.exit();
        }
    }

    @FXML
    private void setTemperature(int temperature) {
        this.displayTemp.setText(Integer.toString(temperature) + "°C");
    }

    @FXML
    private void setDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd, YYYY");
        this.displayDate.setText(format.format(date));
    }

    @FXML
    private void setLocation(Room location) {
        this.userLocation.setText(location.getName());
    }

    @FXML
    private void setProfile(Profile profile) {
        this.userProfile.setText(profile.getName());
    }
    
    @FXML
    private void setTime(int time){
        String hours = String.format("%02d", time/60);
        String mins = String.format("%02d", time%60);
        this.displayTime.setText(hours + ":" + mins);
    }

    //todo increment time over certain interval, return new time in minutes, remember to increment date if time rollsover 1440 minutes
    private int updateTime(){
        return 0;
    }
    @FXML
    protected ToggleButton getSimToggle(){
        return simulationToggle;
    }
}
