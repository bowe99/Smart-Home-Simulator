package com.simulator.gui;
import com.simulator.model.*;
/**
 * This is the controller class for the RoomControls.fxml file
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


public class RoomControlsController {

    @FXML private ChoiceBox roomSelection;
    @FXML private ChoiceBox lightSelection;
    @FXML private ChoiceBox doorSelection;
    @FXML private ChoiceBox windowSelection;
    @FXML private Button confirmValue;
    @FXML private Button cancelBtn;

    private String room;
    private String light;
    private String door;
    private String window;
    private House house;

    private void loadHouse() throws Exception {
        house = HouseLayoutParser.loadFile("house_layout_txt.txt");

    }

        /**
     * Closes the Room Control Panel window pop-up.
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
