package com.simulator.gui;

import com.simulator.model.*;

import javafx.application.Platform;
/**
 * This is the controller class for the RoomControls.fxml file
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class RoomControlsController {

    @FXML private ComboBox<String> roomList = new ComboBox<String>();
    @FXML private ComboBox<String> lightList = new ComboBox<String>();
    @FXML private ComboBox<String> doorList = new ComboBox<String>();
    @FXML private ComboBox<String> windowList = new ComboBox<String>();
    @FXML private Button finished;

    private Room currentRoom;
    private Light currentLight;
    private Door currentDoor;
    private Window currentWindow;
    private String selectedRoom;
    private String selectedLight;
    private String selectedDoor;
    private String selectedWindow;
    private SmartHomeSimulatorController mainController = new SmartHomeSimulatorController();

    @FXML
    void selectingRoom(MouseEvent event){
        System.out.println("arrived displayRoomList");
        roomList.setItems(FXCollections.observableList(House.getInstance().getRoomsListString()));
        selectedRoom = roomList.getValue();

        //Detecting when the option is selected
        roomList.setCellFactory(lv -> {
            ListCell<String> cell = new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item);
                }
            };
            //Upon Mouse press on the option
            cell.setOnMousePressed(e -> {
                if (! cell.isEmpty()) {
                    System.out.println("Click on "+cell.getItem());

                    //room is selected and the string is stored in selected room
                    selectedRoom = cell.getItem();

                    //Selected room now as an object in currentRoom
                    currentRoom = House.getInstance().getRoomByName(selectedRoom);
                    System.out.println(currentRoom+" HERE HERE HERE");

                    //Populate the Selecting Lights, doors and windows field from the corresponding room
                    lightList.setItems(FXCollections.observableList(currentRoom.getLightsListString()));
                    doorList.setItems(FXCollections.observableList(currentRoom.getDoorsListString()));
                    windowList.setItems(FXCollections.observableList(currentRoom.getWindowsListString()));
                }
            });
            return cell ;
        });
    }

    @FXML
    void modifyLight(MouseEvent event){
        System.out.println("arrived displayLightList");

        //Listen for when an option is selected
        lightList.setCellFactory(lv -> {
            ListCell<String> cell = new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item);
                }
            };
            //Upon Mouse press on the option
            cell.setOnMousePressed(e -> {
                if (! cell.isEmpty()) {
                    System.out.println("Click on "+cell.getItem());

                    //room is selected and the string is stored in selected room
                    selectedLight = cell.getItem();
                    //Selected room now as an object in currentRoom
                    currentLight = currentRoom.getLightByName(selectedLight);

                    System.out.println("Current Light that I'm holding: "+currentLight.getName());
                }
            });
            return cell ;
        });
    }

    @FXML
    void modifyDoor(MouseEvent event){
        System.out.println("arrived displayDoorList");
        //Listen for when an option is selected
        doorList.setCellFactory(lv -> {
            ListCell<String> cell = new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item);
                }
            };
            //Upon Mouse press on the option
            cell.setOnMousePressed(e -> {
                if (! cell.isEmpty()) {
                    System.out.println("Click on "+cell.getItem());

                    //room is selected and the string is stored in selected room
                    selectedDoor = cell.getItem();
                    //Selected room now as an object in currentRoom
                    currentDoor = currentRoom.getDoorByName(selectedDoor);

                    System.out.println("Current door object that I'm holding: "+currentDoor.getName());
                }
            });
            return cell ;
        });
        
    }

    @FXML
    void modifyWindow(MouseEvent event){
        System.out.println("arrived displayWindowList");
        //Listen for when an option is selected
        windowList.setCellFactory(lv -> {
            ListCell<String> cell = new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item);
                }
            };
            //Upon Mouse press on the option
            cell.setOnMousePressed(e -> {
                if (! cell.isEmpty()) {
                    System.out.println("Click on "+cell.getItem());

                    //room is selected and the string is stored in selected room
                    selectedWindow = cell.getItem();
                    //Selected room now as an object in currentRoom
                    currentWindow = currentRoom.getWindowByName(selectedWindow);

                    System.out.println("Current window object that I'm holding: "+currentWindow.getName());

                }
            });
            return cell ;
        });
    }

    @FXML 
    void lightON (MouseEvent event){
        System.out.println("lightON");

    }
    @FXML
    void lightOff(MouseEvent event){
        System.out.println("lightOFF");


    }
    @FXML
    void lightAutoOn(MouseEvent event){
        System.out.println("lightAutoOn");


    }
    @FXML
    void lightAutoOff(MouseEvent event){
        System.out.println("lightAutoOFF");


    }
    @FXML
    void doorUnlock(MouseEvent event){
        System.out.println("doorUnlock");


    }
    @FXML
    void doorLock(MouseEvent event){
        System.out.println("doorLock");


    }
    @FXML
    void windowOpen(MouseEvent event){
        System.out.println("windowOpen");

    }
    @FXML
    void windowClose(MouseEvent event){
        System.out.println("windowClose");


    }


    @FXML
    void returnData (MouseEvent event){    
        try{

           }

        catch (Exception e){
        e.printStackTrace();
        }
        
    }
    /**
     * Closes the Room Control Panel window pop-up.
     * @param event Referring to a mouse activity by the user
     */
    @FXML
    void closeWindow (MouseEvent event){
        try{
            Stage stage = (Stage) finished.getScene().getWindow();
            stage.close();
           }
        catch (Exception e){
        e.printStackTrace();
        }
    }

}
