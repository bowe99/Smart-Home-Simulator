package com.simulator.controller;

import com.simulator.model.*;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This is the controller class for the RoomControls.fxml file
 */
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
    @FXML private ImageView a1light;
    @FXML private SmartHomeSimulatorController SHSController;

    private Room currentRoom = null;
    private Light currentLight = null;
    private Door currentDoor = null;
    private Window currentWindow = null;
    private String selectedRoom;
    private String selectedLight;
    private String selectedDoor;
    private String selectedWindow;
    private Image image = new Image(new File("LightOn.jpg").toURI().toString());

    private Permission doorPermission = new Permission(PERMISSION_TYPE.ALL, PERMISSION_TYPE.IN_ROOM, PERMISSION_TYPE.IN_ROOM, PERMISSION_TYPE.NONE);
    private Permission windowPermission = new Permission(PERMISSION_TYPE.ALL, PERMISSION_TYPE.IN_ROOM, PERMISSION_TYPE.IN_ROOM, PERMISSION_TYPE.NONE);
    private Permission lightPermission = new Permission(PERMISSION_TYPE.ALL, PERMISSION_TYPE.IN_ROOM, PERMISSION_TYPE.IN_ROOM, PERMISSION_TYPE.NONE);

    
    /** 
     * Populates the drop down menu with a list of rooms, then populates the Lights, Doors and Windows ComboBox based on room selection
     * @param event
     */
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

    
    /** 
     * Waits to see selected light and then refreshes colours of the buttons to show whether they are on or off
     * @param event
     */
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

    
    /** 
     * Waits to see selected door and then refreshes colours of the buttons to show whether they are on or off
     * @param event
     */
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

    
    /** 
     * Waits to see selected window and then refreshes colours of the buttons to show whether they are on or off
     * @param event
     */
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

    
    /** 
     * turns the selected light on and then refreshes the light buttons colours to reflect whether they are on or off
     * @param event
     */
    @FXML
    void lightON(MouseEvent event) {
        if (currentLight == null || SimulationParameters.getInstance().getSimulationStatus() == false) {
            return;
        } else {
            if(currentLight.getOnOff()){
                return;
            }
            else {
                if(lightPermission.checkPermission(SimulationParameters.getInstance().getCurrentUser(), currentRoom)) {
                    System.out.println("lightON");
                    currentLight.setToOn();
                    changeLightButtonsColours();
            }
             }
        }
    }

    
    /** 
     * turns the selected light off and then refreshes the light buttons colours to reflect whether they are on or off
     * @param event
     */
    @FXML
    void lightOff(MouseEvent event) {
        if (currentLight == null || SimulationParameters.getInstance().getSimulationStatus() == false) {
            return;
        } else {
            if(currentLight.getOnOff()==false){
                return;
            }
            else {
                if(lightPermission.checkPermission(SimulationParameters.getInstance().getCurrentUser(), currentRoom)) {
                    System.out.println("lightOFF");
                    currentLight.setToOff();
                    changeLightButtonsColours();
            }
             }
        }
    }

    
    /** 
     * turns the selected light Auto on and then refreshes the light buttons colours to reflect whether they are on or off
     * @param event
     */
    @FXML
    void lightAutoOn(MouseEvent event) {
        if (currentLight == null || SimulationParameters.getInstance().getSimulationStatus() == false) {
            return;
        } else {
            if(currentLight.getAuto()){
                return;
            }
            else {
                    if(lightPermission.checkPermission(SimulationParameters.getInstance().getCurrentUser(), currentRoom)) {
                System.out.println("lightAutoOn");
                currentLight.setAutoOn();
                changeLightAutoButtonsColours();
            }

             }
        }
    }

    
    /** 
     * turns the selected light Auto off and then refreshes the light buttons colours to reflect whether they are on or off
     * @param event
     */
    @FXML
    void lightAutoOff(MouseEvent event) {
        if (currentLight == null || SimulationParameters.getInstance().getSimulationStatus() == false) {
            return;
        } else {
            if(currentLight.getAuto()==false){
                return;
            }
            else {
                    if(lightPermission.checkPermission(SimulationParameters.getInstance().getCurrentUser(), currentRoom)) {
                System.out.println("lightAutoOFF");
                currentLight.setAutoOff();
                changeLightAutoButtonsColours();
            }
             }
        }
    }

    
    /** 
     * Unlocks the selected door and then refreshes the door buttons colours to reflect whether they are unlocked or locked
     */
    @FXML
    void doorUnlock(MouseEvent event) {
        if (currentDoor == null || SimulationParameters.getInstance().getSimulationStatus() == false) {
            return;
        } else {
            if(currentDoor.getLockedStatus()==false){
                return;
            }
            else {
                    if(doorPermission.checkPermission(SimulationParameters.getInstance().getCurrentUser(), currentRoom)) {
                System.out.println("doorUnlock");
                currentDoor.setUnlocked();
                changeDoorButtonsColours();
                Logger.getInstance().outputToConsole(currentDoor.getName() + " is now set to Unlocked");
            }
            try{
                Logger.getInstance().outputToLogFile(currentDoor.getName()+" is now set to Unlocked");
            }
            catch(Exception e){
                System.out.println("Could not write to txt file");
            }
             }
        }
    }

    
    /** 
     * locks the selected door and then refreshes the door buttons colours to reflect whether they are unlocked or locked
     * @param event
     */
    @FXML
    void doorLock(MouseEvent event) {
        if (currentDoor == null || SimulationParameters.getInstance().getSimulationStatus() == false) {
            return;
        } else {
            if(currentDoor.getLockedStatus()){
                return;
            }
            else {
                    if(doorPermission.checkPermission(SimulationParameters.getInstance().getCurrentUser(), currentRoom)) {
                System.out.println("doorLock");
                currentDoor.setLocked();
                changeDoorButtonsColours();
                Logger.getInstance().outputToConsole(currentDoor.getName() + " is now set to Locked");
            }
            try{
                Logger.getInstance().outputToLogFile(currentDoor.getName()+" is now set to Locked");
            }
            catch(Exception e){
                System.out.println("Could not write to txt file");
            }
             }
        }

    }

    
    /** 
     * opens the selected window and then refreshes the window buttons colours to reflect whether they are opened or closed
     * @param event
     */
    @FXML
    void windowOpen(MouseEvent event) {
        if (currentWindow == null || SimulationParameters.getInstance().getSimulationStatus() == false) {
            return;
        }
            else if(currentWindow.getBlockedBoolean()){
                Logger.getInstance().outputToConsole(currentWindow.getName()+" is blocked, could not open");
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
                        if(windowPermission.checkPermission(SimulationParameters.getInstance().getCurrentUser(), currentRoom)) {
                System.out.println("windowOpen");
                // insert if statement here to check for obstructions
                currentWindow.setOpen();
                changeWindowButtonsColours();
                Logger.getInstance().outputToConsole(currentWindow.getName() + " is now set to Open");
            }
            try{
                Logger.getInstance().outputToLogFile(currentWindow.getName()+" is now set to Open");
            }
            catch(Exception e){
                System.out.println("Could not write to txt file");
                 }
            }
        }
    }

    
     /** 
     * closes the selected window and then refreshes the window buttons colours to reflect whether they are opened or closed
     * @param event
     */
    @FXML
    void windowClose(MouseEvent event) {
        if(currentWindow==null || SimulationParameters.getInstance().getSimulationStatus()==false){
            return;
        }
        else if(currentWindow.getBlockedBoolean()){
            Logger.getInstance().outputToConsole(currentWindow.getName()+" is blocked, could not close");
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
                    if(windowPermission.checkPermission(SimulationParameters.getInstance().getCurrentUser(), currentRoom)) {
                currentWindow.setClosed();
                changeWindowButtonsColours();
                Logger.getInstance().outputToConsole(currentWindow.getName() + " is now set to Closed");
            }
            try{
            Logger.getInstance().outputToLogFile(currentWindow.getName()+" is now set to Closed");
            }
            catch(Exception e){
                System.out.println("Could not write to txt file");
            }
                }
        }
    }

    /** 
     * changes the background colour of button based on the status of their boolean
     */
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
    /** 
     * changes the background colour of button based on the status of their boolean
     */
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
    /** 
     * changes the background colour of button based on the status of their boolean
     */
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
    /** 
     * changes the background colour of button based on the status of their boolean
     */
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
    /** 
     * resets the colours of all buttons to their initial state
     */
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
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing RoomControls");
  }
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

    @FXML
    private void setLightIcon() {
        System.out.println("Lights switched");
    }
    
    
}
