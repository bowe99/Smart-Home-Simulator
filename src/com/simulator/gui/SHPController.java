package com.simulator.gui;

import com.simulator.model.*;
import javafx.application.Platform;
/**
 * This is the controller class for the SHPDisplay.fxml file
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class SHPController implements Initializable{

    @FXML private ToggleButton awayToggle;
    @FXML private Label alertTime;
    @FXML private TextField inputTime;
    @FXML private Button confirmBtn;

    /**
     * Toggles the away status
     * @param event Referring to a mouse activity by the user
     */
    @FXML
    void changeAwayStatus(MouseEvent event) {
        if(awayToggle.isSelected()){
            this.awayToggle.setText("On");
        } else {
            this.awayToggle.setText("Off");
        }
    }
    /**
     * Changes the time to call authorities.
     * @param event Referring to a mouse activity by the user
     */
    @FXML
    void changeAlertTime(MouseEvent event) {
        inputTime.setVisible(true);
        confirmBtn.setVisible(true);
    }
    @FXML
    void submitAlertTime(MouseEvent event) {
        if ((inputTime.getText()).length() == 1)
        {
            alertTime.setText(inputTime.getText()+" minutes");
        }
        inputTime.setVisible(false);
        confirmBtn.setVisible(false);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }
}