package com.simulator.gui;

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
    @FXML private ChoiceBox UserProfileChoice;
    @FXML private ChoiceBox UserLocationChoice;

    private String temperature;
    private String date;
    private String profile;
    private String location;

    @FXML
    void returnData (MouseEvent event){
        try{
            this.temperature = (temperatureValue.getText()+" °C");
            formatDate(this.dateValue.getEditor().getText());
            Stage stage = (Stage) temperatureValue.getScene().getWindow();
            stage.close();
            this.profile = (String)(UserProfileChoice.getValue());
            this.location = (String)(UserLocationChoice.getValue());

           }

        catch (Exception e){
        e.printStackTrace();
        }
    }

    @FXML
    String getTemperature(){
        return this.temperature;
    }
     @FXML
    String getDate(){
        return this.date;
    }
    @FXML
    String getProfile(){
        return this.profile;
    }
    @FXML
    String getlocation(){
        return this.location;
    }


    @FXML
    void formatDate(String date){
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
