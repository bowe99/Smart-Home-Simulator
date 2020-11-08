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

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RoomControlsController implements Initializable {

    @FXML private ComboBox<String> roomList = new ComboBox<String>();
    @FXML private ComboBox<String> lightList = new ComboBox<String>();
    @FXML private ComboBox<String> doorList = new ComboBox<String>();
    @FXML private ComboBox<String> windowList = new ComboBox<String>();
    @FXML private Button lightOn;
    @FXML private Button lightOff;
    @FXML private Button lightAutoOn;
    @FXML private Button lightAutoOff;
    @FXML private Button doorUnlock;
    @FXML private Button doorLock;
    @FXML private Button windowOpen;
    @FXML private Button windowClose;
    @FXML private Button finished;

    private Room currentRoom = null;
    private Light currentLight = null;
    private Door currentDoor = null;
    private Window currentWindow = null;
    private String selectedRoom;
    private String selectedLight;
    private String selectedDoor;
    private String selectedWindow;

    @FXML
    void selectingRoom(MouseEvent event) {
        if (SimulationParameters.getInstance().getSimulationStatus() == true) {
            System.out.println("arrived displayRoomList");
            roomList.setItems(FXCollections.observableList(House.getInstance().getRoomsNameList()));
            selectedRoom = roomList.getValue();

            // Detecting when the option is selected
            roomList.setCellFactory(lv -> {
                ListCell<String> cell = new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? null : item);
                    }
                };
                // Upon Mouse press on the option
                cell.setOnMousePressed(e -> {
                    if (!cell.isEmpty()) {
                        System.out.println("Click on " + cell.getItem());

                        // room is selected and the string is stored in selected room
                        selectedRoom = cell.getItem();

                        // Selected room now as an object in currentRoom
                        currentRoom = House.getInstance().getRoomByName(selectedRoom);
                        System.out.println(currentRoom + " HERE HERE HERE");

                        // Populate the Selecting Lights, doors and windows field from the corresponding
                        // room
                        lightList.setItems(FXCollections.observableList(currentRoom.getLightsNameList()));
                        doorList.setItems(FXCollections.observableList(currentRoom.getDoorsNameList()));
                        windowList.setItems(FXCollections.observableList(currentRoom.getWindowsNameList()));
                        resetAllButtonColours();

                    }
                });
                return cell;
            });
        } else
            return;
    }

    @FXML
    void modifyLight(MouseEvent event) {
        if (SimulationParameters.getInstance().getSimulationStatus() == true) {
            System.out.println("arrived displayLightList");

            // Listen for when an option is selected
            lightList.setCellFactory(lv -> {
                ListCell<String> cell = new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? null : item);
                    }
                };
                // Upon Mouse press on the option
                cell.setOnMousePressed(e -> {
                    if (!cell.isEmpty()) {

                        // room is selected and the string is stored in selected room
                        selectedLight = cell.getItem();
                        // Selected room now as an object in currentRoom
                        currentLight = currentRoom.getLightByName(selectedLight);
                        changeLightButtonsColours();
                        changeLightAutoButtonsColours();
                    }
                });
                return cell;
            });
        } else
            return;
    }

    @FXML
    void modifyDoor(MouseEvent event) {
        if (SimulationParameters.getInstance().getSimulationStatus() == true) {
            System.out.println("arrived displayDoorList");
            // Listen for when an option is selected
            doorList.setCellFactory(lv -> {
                ListCell<String> cell = new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? null : item);
                    }
                };
                // Upon Mouse press on the option
                cell.setOnMousePressed(e -> {
                    if (!cell.isEmpty()) {

                        // room is selected and the string is stored in selected room
                        selectedDoor = cell.getItem();
                        // Selected room now as an object in currentRoom
                        currentDoor = currentRoom.getDoorByName(selectedDoor);
                        changeDoorButtonsColours();
                    }
                });
                return cell;
            });
        } else
            return;
    }

    @FXML
    void modifyWindow(MouseEvent event) {
        if (SimulationParameters.getInstance().getSimulationStatus() == true) {
            System.out.println("arrived displayWindowList");
            // Listen for when an option is selected
            windowList.setCellFactory(lv -> {
                ListCell<String> cell = new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? null : item);
                    }
                };
                // Upon Mouse press on the option
                cell.setOnMousePressed(e -> {
                    if (!cell.isEmpty()) {

                        // room is selected and the string is stored in selected room
                        selectedWindow = cell.getItem();
                        // Selected room now as an object in currentRoom
                        currentWindow = currentRoom.getWindowByName(selectedWindow);
                        changeWindowButtonsColours();
                    }
                });
                return cell;
            });
        } else
            return;
    }

    @FXML
    void lightON(MouseEvent event) {
        if (currentLight == null || SimulationParameters.getInstance().getSimulationStatus() == false) {
            return;
        } else {
            if(currentLight.getOnOff()){
                return;
            }
            else {
                    currentLight.setToOn();
                    changeLightButtonsColours();
                    Logger.getInstance().ouputToConsole(currentLight.getName()+" is now set to On");
                    try{
                        Logger.getInstance().outputToLogFile(currentLight.getName()+" is now set to On");
                    }
                    catch(Exception e){
                        System.out.println("Could not write to txt file");
                    }
             }
        }

    }

    @FXML
    void lightOff(MouseEvent event) {
        if (currentLight == null || SimulationParameters.getInstance().getSimulationStatus() == false) {
            return;
        } else {
            if(currentLight.getOnOff()==false){
                return;
            }
            else {
                    currentLight.setToOff();
                    changeLightButtonsColours();
                    Logger.getInstance().ouputToConsole(currentLight.getName()+" is now set to Off");
                    try{
                        Logger.getInstance().outputToLogFile(currentLight.getName()+" is now set to Off");
                    }
                    catch(Exception e){
                        System.out.println("Could not write to txt file");
                    }
             }
        }
    }

    @FXML
    void lightAutoOn(MouseEvent event) {
        if (currentLight == null || SimulationParameters.getInstance().getSimulationStatus() == false) {
            return;
        } else {
            if(currentLight.getAuto()){
                return;
            }
            else {
                    currentLight.setAutoOn();
                    changeLightAutoButtonsColours();
                    Logger.getInstance().ouputToConsole(currentLight.getName()+" Auto is now set to On");
                    try{
                        Logger.getInstance().outputToLogFile(currentLight.getName()+" Auto is now set to On");
                    }
                    catch(Exception e){
                        System.out.println("Could not write to txt file");
                    }
             }
        }
    }

    @FXML
    void lightAutoOff(MouseEvent event) {
        if (currentLight == null || SimulationParameters.getInstance().getSimulationStatus() == false) {
            return;
        } else {
            if(currentLight.getAuto()==false){
                return;
            }
            else {
                    currentLight.setAutoOff();
                    changeLightAutoButtonsColours();
                    Logger.getInstance().ouputToConsole(currentLight.getName()+" Auto is now set to Off");
                    try{
                        Logger.getInstance().outputToLogFile(currentLight.getName()+" Auto is now set to Off");
                    }
                    catch(Exception e){
                        System.out.println("Could not write to txt file");
                    }
             }
        }
    }

    @FXML
    void doorUnlock(MouseEvent event) {
        if (currentDoor == null || SimulationParameters.getInstance().getSimulationStatus() == false) {
            return;
        } else {
            if(currentDoor.getLockedStatus()==false){
                return;
            }
            else {
                    currentDoor.setUnlocked();
                    changeDoorButtonsColours();
                    Logger.getInstance().ouputToConsole(currentDoor.getName()+" is now set to Unlocked");
                    try{
                        Logger.getInstance().outputToLogFile(currentDoor.getName()+" is now set to Unlocked");
                    }
                    catch(Exception e){
                        System.out.println("Could not write to txt file");
                    }
             }
        }
    }

    @FXML
    void doorLock(MouseEvent event) {
        if (currentDoor == null || SimulationParameters.getInstance().getSimulationStatus() == false) {
            return;
        } else {
            if(currentDoor.getLockedStatus()){
                return;
            }
            else {
                    currentDoor.setLocked();
                    changeDoorButtonsColours();
                    Logger.getInstance().ouputToConsole(currentDoor.getName()+" is now set to Locked");
                    try{
                        Logger.getInstance().outputToLogFile(currentDoor.getName()+" is now set to Locked");
                    }
                    catch(Exception e){
                        System.out.println("Could not write to txt file");
                    }
             }
        }

    }

    @FXML
    void windowOpen(MouseEvent event) {
        if (currentWindow == null || SimulationParameters.getInstance().getSimulationStatus() == false) {
            return;
        }
            else if(currentWindow.getBlockedBoolean()){
                Logger.getInstance().ouputToConsole(currentWindow.getName()+" is blocked, could not open");
                try{
                    Logger.getInstance().outputToLogFile(currentWindow.getName()+" is blocked, could not open");
                }
                catch(Exception e){
                    System.out.println("Could not write to txt file");
                }
                return;
            }
            else{
                if(currentWindow.getOpenOrClosed()==true){
                    return;
                }
                else {
                        currentWindow.setOpen();
                        changeWindowButtonsColours();
                        Logger.getInstance().ouputToConsole(currentWindow.getName()+" is now set to Open");
                        try{
                            Logger.getInstance().outputToLogFile(currentWindow.getName()+" is now set to Open");
                        }
                        catch(Exception e){
                            System.out.println("Could not write to txt file");
                        }
                 }
            }
    }

    @FXML
    void windowClose(MouseEvent event) {
        if(currentWindow==null || SimulationParameters.getInstance().getSimulationStatus()==false){
            return;
        }
        else if(currentWindow.getBlockedBoolean()){
            Logger.getInstance().ouputToConsole(currentWindow.getName()+" is blocked, could not close");
            try{
                Logger.getInstance().outputToLogFile(currentWindow.getName()+" is blocked, could not close");
            }
            catch(Exception e){
                System.out.println("Could not write to txt file");
            }
            return;
        }
        else{
            if(currentWindow.getOpenOrClosed()==false){
                return;
            }
            else {
                    currentWindow.setClosed();
                    changeWindowButtonsColours();
                    Logger.getInstance().ouputToConsole(currentWindow.getName()+" is now set to Closed");
                    try{
                        Logger.getInstance().outputToLogFile(currentWindow.getName()+" is now set to Closed");
                    }
                    catch(Exception e){
                        System.out.println("Could not write to txt file");
                    }
                }
        }
    }

    @FXML 
    void changeLightButtonsColours(){
        if(currentLight.getOnOff()==true){
            lightOn.setStyle("-fx-background-color: #7FFF00");
            lightOff.setStyle("-fx-all: initial");
        }
        if(currentLight.getOnOff()==false){
            lightOn.setStyle("-fx-all: initial");
            lightOff.setStyle("-fx-background-color: #FF0000");
        }
    }

    @FXML 
    void changeLightAutoButtonsColours(){
        if(currentLight.getAuto()==true){
            lightAutoOn.setStyle("-fx-background-color: #7FFF00");
            lightAutoOff.setStyle("-fx-all: initial");
        }
        if(currentLight.getAuto()==false){
            lightAutoOn.setStyle("-fx-all: initial");
            lightAutoOff.setStyle("-fx-background-color: #FF0000");
        }
    }

    @FXML 
    void changeDoorButtonsColours(){
        if(currentDoor.getLockedStatus()==false){
            doorUnlock.setStyle("-fx-background-color: #7FFF00");
            doorLock.setStyle("-fx-all: initial");
        }
        if(currentDoor.getLockedStatus()==true){
            doorUnlock.setStyle("-fx-all: initial");
            doorLock.setStyle("-fx-background-color: #FF0000");
        }
    }

    @FXML 
    void changeWindowButtonsColours(){
        if(currentWindow.getOpenOrClosed()==true){
            windowOpen.setStyle("-fx-background-color: #7FFF00");
            windowClose.setStyle("-fx-all: initial");
        }
        if(currentWindow.getOpenOrClosed()==false){
            windowOpen.setStyle("-fx-all: initial");
            windowClose.setStyle("-fx-background-color: #FF0000");
        }
    }

    @FXML 
    void resetAllButtonColours(){
        lightOn.setStyle("-fx-all: initial");
        lightOff.setStyle("-fx-all: initial");
        lightAutoOff.setStyle("-fx-all: initial");
        lightAutoOn.setStyle("-fx-all: initial");
        doorUnlock.setStyle("-fx-all: initial");
        doorLock.setStyle("-fx-all: initial");
        windowOpen.setStyle("-fx-all: initial");
        windowClose.setStyle("-fx-all: initial");
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing !!!!!!");
        

    }

}
