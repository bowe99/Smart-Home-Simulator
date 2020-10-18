package com.simulator.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;


public class SystemParameterController {

    @FXML private TextField temperatureValue;
    @FXML private Button confirmValue;
    @FXML private DatePicker dateValue;
    
    private String temperature;
    private String date;

    @FXML
    void returnData (MouseEvent event){
        try{
            this.temperature = (temperatureValue.getText()+" °C");
            formatDate(this.dateValue.getEditor().getText());
            Stage stage = (Stage) temperatureValue.getScene().getWindow();
            stage.close();
           }

        catch (Exception e){
        e.printStackTrace();
        }
    }

    String getTemperature(){
        return this.temperature;
    }

    String getDate(){
        return this.date;
    }

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
