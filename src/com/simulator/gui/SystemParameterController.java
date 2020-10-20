package com.simulator.gui;
 /**
  * This is the controller class for the ParametorEditor.fxml file
  */
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
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


public class SystemParameterController {

    @FXML private TextField temperatureValue;
    @FXML private Button confirmValue;
    @FXML private DatePicker dateValue;
    @FXML private ChoiceBox userProfileChoice;
    @FXML private ChoiceBox userLocationChoice;
    @FXML private TextField theTime;
    @FXML private Button cancelBtn;

    private String temperature;
    private String date;
    private String profile;
    private String location;
    private String time;

    /**
     * Returns data to the SmartHomeSimulator controller.
     * @param event Referring to a mouse activity by the user
     * Sets the system parameters to the demanded value.
     */
    @FXML
    void returnData (MouseEvent event){
        try{
            this.temperature = (temperatureValue.getText()+" °C");
            formatDate(this.dateValue.getEditor().getText());
            Stage stage = (Stage) temperatureValue.getScene().getWindow();
            stage.close();
            this.profile = (String)(userProfileChoice.getValue());
            this.location = (String)(userLocationChoice.getValue());
            this.time= (String)(theTime.getText());
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
    /**
     * Getter for the System parameter: temperature
     */
    @FXML
    String getTemperature(){
        return this.temperature;
    }
    /**
     * Getter for the System parameter: date
     */
     @FXML
    String getDate(){
        return this.date;
    }
    /**
     * Getter for the System parameter: profile
     */
    @FXML
    String getProfile(){
        return this.profile;
    }
    /**
     * Getter for the System parameter: location
     */
    @FXML
    String getLocation(){
        return this.location;
    }
    /**
     * Getter for the System parameter: time
     */
    @FXML
    String getTime(){
        return this.time;
    }

    @FXML
    private void formatDate(String date){
        System.out.println(date);

        SimpleDateFormat initialFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat finalFormat = new SimpleDateFormat("EEE MMM dd, YYYY");
        try{
            Date dateString = initialFormat.parse(date);
            long dateTime = dateString.getTime();
            Date finalDate = new Date(dateTime);
            String finalDateString = finalFormat.format(finalDate); 
            this.date = finalDateString;

        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
