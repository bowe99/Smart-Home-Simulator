package com.simulator.controller;

import com.simulator.model.Profile;
import com.simulator.model.Room;
import com.simulator.model.SecurityModule;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.simulator.model.*;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;

/**
 * This is the controller class for the RoomControls.fxml file AND the Dashboard.fxml file
 */
public class SmartHomeSimulatorController {

    @FXML private ToggleButton simulationToggle;
    @FXML private ToggleButton awayModeToggle;
    @FXML private Button editButton;
    @FXML private Button roomsControlPanelButton;
    @FXML private Button saveSecurity;
    @FXML private Button saveButton;
    @FXML private Label displayTemp;
    @FXML private Hyperlink displayDate;
    @FXML private Hyperlink userProfile;
    @FXML private Label userLocation;
    @FXML private Hyperlink displayTime;
    @FXML private Label layoutViewText;

    private final int NUMBER_OF_GRID_ELEMENTS = 25;
    @FXML private Pane houseLayoutPane;
    @FXML private TextArea[] areas;
    @FXML private ImageView[] lightImages;
    @FXML private ImageView[] doorImages;
    @FXML private ImageView[] windowImages;
    @FXML private ImageView[] personImages;


    @FXML private ImageView awayIcon1;
    @FXML private ImageView awayIcon2;

    @FXML private TextArea outputConsole;

    @FXML private ListView allLightsListView;
    @FXML private ListView selectedLightsListView;
    @FXML private TextField startTimeSecurity;
    @FXML private TextField endTimeSecurity;
    @FXML private TextField motionDetectedTimeSecurity;

    @FXML private ListView allRoomsDisplayTemp;
    @FXML private ListView allRoomsCreateGroups;
    @FXML private Button displayRoomTempButton;
    @FXML private TextField setTemperatureSingleRoom;

    @FXML private Label lastSaved;
    @FXML private TabPane tabPane;
    @FXML private Tab SHCTab;
    @FXML private RoomControlsController sHCTabPageController;
    @FXML private Tab SHPTab;

    @FXML private ListView allRoomsListViewZone;
    @FXML private ListView selectedRoomsListForZone;

    private House house;
    private SimulationParameters simulation;

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
    @FXML private SmartHomeSimulatorController SHSController;

    private Room currentRoom=null;
    private Light currentLight=null;
    private Door currentDoor=null;
    private Window currentWindow=null;
    private String selectedRoom;
    private String selectedLight;
    private String selectedDoor;
    private String selectedWindow;

    private static final String RESOURCE_PATH = "/com/simulator/view/";
    private javafx.scene.image.Image lightIcon = new javafx.scene.image.Image(getClass().getResource(RESOURCE_PATH + "lightOn.jpg").toExternalForm());
    private javafx.scene.image.Image unlockedIcon = new javafx.scene.image.Image(getClass().getResource(RESOURCE_PATH + "unlocked.png").toExternalForm());
    private javafx.scene.image.Image lockedIcon = new javafx.scene.image.Image(getClass().getResource(RESOURCE_PATH + "locked.png").toExternalForm());
    private javafx.scene.image.Image openWindowIcon = new javafx.scene.image.Image(getClass().getResource(RESOURCE_PATH + "openwindow.png").toExternalForm());
    private javafx.scene.image.Image closedWindowIcon = new javafx.scene.image.Image(getClass().getResource(RESOURCE_PATH + "closedwindow.png").toExternalForm());
    private javafx.scene.image.Image personIcon = new javafx.scene.image.Image(getClass().getResource(RESOURCE_PATH + "person.png").toExternalForm());
    private javafx.scene.image.Image peopleIcon = new javafx.scene.image.Image(getClass().getResource(RESOURCE_PATH + "people.jpg").toExternalForm());





    private SecurityModule securityModule;
    private HeatingModule heatingModule;
    private Timer timer = new Timer();
    Permission securityPermission;

    public SmartHomeSimulatorController()
    {
        this.house = House.getInstance();
        this.simulation = SimulationParameters.getInstance();
        securityPermission = new Permission(PERMISSION_TYPE.ALL, PERMISSION_TYPE.ALL, PERMISSION_TYPE.NONE, PERMISSION_TYPE.NONE);
    }
    /**
     * Initializes the SmartHomeSimulator Dashboard
     */
    @FXML
    public void initialize(){
        setTemperature(simulation.getTemperature());
        setDate(simulation.getDate());
        setLocation(simulation.getCurrentUser().getCurrentRoom());
        setProfile(simulation.getCurrentUser());
        setTime(simulation.getTime());
        initializeHouseView();
        setHouseView();

        // Initialize lists in fxml with values from house template
        setLights(house);
        setRoomsInHeatingModule(house);

        //Initializing SHH

        setRoomsZonePanel();

        Date currentDateTime = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        lastSaved.setText(format.format(currentDateTime));
        startTimer();
        //creating a new instance of the logger with the output console so that other classes can use it
        Logger.newInstance(outputConsole);
        Logger.getInstance().resetLogFile();
        this.securityModule = new SecurityModule(simulation.getAllUsers(), awayModeToggle, this.simulation.getTimeObject());
        this.heatingModule = new HeatingModule();
    }

    /**
     * Changes the simulation status to on or off
     * @param event Referring to a mouse activity by the user
     */
    @FXML
    void changeSimulationStatus(MouseEvent event) {
        if(simulation.getSimulationStatus()){
            this.simulationToggle.setText("On");
            disableHouseView();
            simulation.setSimulationStatus(false);
            timer.cancel();
        } else {
            this.simulationToggle.setText("Off");
            setHouseView();
            simulation.setSimulationStatus(true);
            startTimer();
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(RESOURCE_PATH + "ParameterEditor.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initOwner(simulationToggle.getScene().getWindow());
            stage.setTitle("Edit Parameters");
            stage.setScene(new Scene(root1));  

            if(simulation.getSimulationStatus())
                timer.cancel();

            stage.showAndWait();
            
            if(simulation.getSimulationStatus())
                startTimer();

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

    private void initializeHouseView(){
        areas = new TextArea[NUMBER_OF_GRID_ELEMENTS + 1];
        lightImages = new ImageView[NUMBER_OF_GRID_ELEMENTS + 1];
        doorImages = new ImageView[NUMBER_OF_GRID_ELEMENTS + 1];
        windowImages = new ImageView[NUMBER_OF_GRID_ELEMENTS + 1];
        personImages = new ImageView[NUMBER_OF_GRID_ELEMENTS + 1];

        for (int i = 1; i <= NUMBER_OF_GRID_ELEMENTS ; i++)
        {
            areas[i] = (TextArea) houseLayoutPane.lookup("#area" + i);
            lightImages[i] = (ImageView) houseLayoutPane.lookup("#a"+ i +"light");
            doorImages[i] = (ImageView) houseLayoutPane.lookup("#a"+ i +"door");
            windowImages[i] = (ImageView) houseLayoutPane.lookup("#a"+ i +"window");
            personImages[i] = (ImageView) houseLayoutPane.lookup("#a"+ i +"person");
        }
    }

    /**
     * Removes house layout view from UI.
     */
    private void disableHouseView() {
        layoutViewText.setText("Turn On Simulator for House View");
        layoutViewText.setTranslateX(-80);
        layoutViewText.setOpacity(1);
        awayIcon1.setVisible(false);
        awayIcon2.setVisible(false);

        for (int counter = 1; counter <= NUMBER_OF_GRID_ELEMENTS; counter++) {
            areas[counter].setOpacity(1);//necessary?
            areas[counter].setVisible(false);
            lightImages[counter].setVisible(false);
            doorImages[counter].setVisible(false);
            windowImages[counter].setVisible(false);
            personImages[counter].setVisible(false);
        }
    }
        
    /**
     * Sets house layout view in UI.
     */
    @FXML
    private void setHouseView() {
        layoutViewText.setText("House View");
        layoutViewText.setTranslateX(20);       
        layoutViewText.setOpacity(1);
        for (int loop=0; loop< simulation.getAllUsers().size(); loop++)
        {
            //BUG: if there are 3 people in the room, the second hit will set the image to 'peopleIcon' and the third will set it back to 'personIcon'
            //BUG: if there is only 1 person in the room, toggling the simulation on and off will cause the image to alternate between person and people icons
            String roomIDstring = simulation.getAllUsers().get(loop).getCurrentRoom().getId();
            if(roomIDstring.length() > 4) {
                int currentRoomID = Integer.parseInt(roomIDstring.substring(4));
                ImageView currentRoomPersonImage = personImages[currentRoomID];
                if (currentRoomPersonImage.getImage() == personIcon)
                    currentRoomPersonImage.setImage(peopleIcon);
                else
                    currentRoomPersonImage.setImage(personIcon);
            }
        }

        for (int counter = 0; counter < house.getRooms().size(); counter++) {
            String roomIDstring = house.getRooms().get(counter).getId();
            if(roomIDstring.length() > 4) {
                int roomID = Integer.parseInt(roomIDstring.substring(4));
                TextArea roomArea = areas[roomID];
                roomArea.setText(house.getRooms().get(counter).getName());
                roomArea.setOpacity(1);
                roomArea.setVisible(true);
                lightImages[roomID].setVisible(true);
                doorImages[roomID].setVisible(true);
                windowImages[roomID].setVisible(true);
                personImages[roomID].setVisible(true);
            }
        }
    }

        //Ended here
    @FXML
    private void addRoomToZoneFunction(){

    }
    @FXML
    private void removeRoomFromZoneFunction(){

    }
    @FXML
    private void setRoomsZonePanel(){
        ArrayList<String> roomNameList = (ArrayList<String>) House.getInstance().getRoomsNameList();

        for (String room: roomNameList){
            allRoomsListViewZone.getItems().add(room);
        }
    }


    /**
     * Changes the away status of the simulator.
     */
    public void changeAwayStatus() {
        securityModule.toggleAwayMode();
        if (securityModule.getAwayMode())
        {
            awayIcon1.setVisible(true);
            awayIcon2.setVisible(true);
            layoutViewText.setTranslateX(-88);       
            layoutViewText.setText("House View - Away Mode Activated");
        }
        else 
        {
            awayIcon1.setVisible(false);
            awayIcon2.setVisible(false);
            layoutViewText.setTranslateX(20); 
            layoutViewText.setText("House View");      
        }
    }
    /**
     * Add security Light
     */
    @FXML
    void addSecurityLight(){
        Object selectedItem = this.allLightsListView.getSelectionModel().getSelectedItem();
        this.selectedLightsListView.getItems().add((String) selectedItem);
        this.allLightsListView.getItems().remove(selectedItem);

        System.out.println((String) selectedItem);

        Light selectedLight = this.house.getLightByName((String) selectedItem);
        this.securityModule.addLight(selectedLight);
    }
    /**
     * remove security light
     */
    @FXML
    void removeSecurityLight(){

        Object selectedItem = this.selectedLightsListView.getSelectionModel().getSelectedItem();
        this.allLightsListView.getItems().add((String) selectedItem);
        this.selectedLightsListView.getItems().remove(selectedItem);

        System.out.println((String) selectedItem);

        Light selectedLight = this.house.getLightByName((String) selectedItem);
        this.securityModule.removeLight(selectedLight);
    }

    
    /** 
     * set temperature
     * @param temperature
     */
    @FXML
    private void setTemperature(int temperature) {
        this.displayTemp.setText(Integer.toString(temperature) + "°C");
    }

    
    /** 
     * set date
     * @param date
     */
    @FXML
    private void setDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd, YYYY");
        this.displayDate.setText(format.format(date));
    }

    
    /** 
     * set location
     * @param location
     */
    @FXML
    private void setLocation(Room location) {
        this.userLocation.setText(location.getName());
    }

    
    /** 
     * Set profile
     * @param profile
     */
    @FXML
    private void setProfile(Profile profile) {
        this.userProfile.setText(profile.getName());
    }
    
    
    /** 
     * set time
     * @param time
     */
    @FXML
    private void setTime(int time){
        String hours = String.format("%02d", time/60);
        String mins = String.format("%02d", time%60);
        this.displayTime.setText(hours + ":" + mins);
    }
    /**
     * Save Security Settings
     */
    @FXML
    private void saveSecuritySettings(){
        if(securityPermission.checkPermission(simulation.getCurrentUser(), simulation.getCurrentUser().getCurrentRoom())) {
            String startTimeInput = this.startTimeSecurity.getText();
            String endTimeInput = this.endTimeSecurity.getText();
            String motionDetectedTimeInput = this.motionDetectedTimeSecurity.getText();
            try {
                int startTime = Integer.parseInt(startTimeInput);
                int endTime = Integer.parseInt(endTimeInput);
                int motionDetectedTime = Integer.parseInt(motionDetectedTimeInput);

                if (startTime > 1440 || startTime < 0 || endTime < 0 || endTime > 1440) {
                    throw new Exception("Time is not in the correct range");
                }

                this.securityModule.saveSettings(startTime, endTime, motionDetectedTime);
                Logger.getInstance().outputToConsole("Security settings were successfully updated");
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid input");
                alert.setContentText("No information was saved! Please try again.");
                alert.showAndWait();
            }
        }
    }

    
    /** 
     * set the lights
     * @param house
     */
    @FXML
    private void setLights(House house){
        ArrayList<String> lightNameList = new ArrayList<String>();

        lightNameList = this.house.getLightsNameList();

        for (String light: lightNameList){
            allLightsListView.getItems().add(light);
        }
    }
    
    
    /** 
     * Set the rooms for the lists in the heating module
     * @param house
     */
    @FXML
    private void setRoomsInHeatingModule(House house){
        ArrayList<String> roomNameList = new ArrayList<String>();

        roomNameList = this.house.getRoomsNameList();

        for (String room: roomNameList){
            //Do not want to include Away as a room for the setting of the temperature
            if(!room.equals("Away")){
                allRoomsCreateGroups.getItems().add(room);
                allRoomsDisplayTemp.getItems().add(room);
            }
        }
    }

    /**
     * Set the temperature for a specific room in the house
     */
    @FXML
    private void setRoomTemperature(){
        Object selectedItem = allRoomsDisplayTemp.getSelectionModel().getSelectedItem();
        String selectedRoom = (String) selectedItem;
        int selectedIndex = allRoomsDisplayTemp.getItems().indexOf(selectedItem);

        boolean success;

        // Checks if there is a room selected from the list
        if(selectedIndex < 0){
            Logger.getInstance().outputToConsole("No Room was selected unable to update temperature");
        }

        // If entry currently has 'Overwritten' in the name remove it to make it cleaner for the console
        if(selectedRoom.contains(" (Overwritten)")){
            selectedRoom = selectedRoom.substring(0, selectedRoom.length() - 14);
        }

        //This try block will parse the string for an integer value if it fails it will not change the temperature and if
        // it succeeds it will output to console the new change
        try {
            int newTemperatureInt = Integer.parseInt(setTemperatureSingleRoom.getText());

            success = this.heatingModule.overrideRoomTemperature(selectedRoom, newTemperatureInt, this.simulation.getCurrentUser());
        } catch (Exception e) {
            Logger.getInstance().outputToConsole("Invalid String input for temperature");
            System.out.println(e);
            // On fail we do not want to continue and change the selected listview cell
            return;
        }

        // Update listview item with the Overwritten tag
        if(success){
            if(!selectedRoom.contains(" (Overwritten)")){
                selectedRoom = selectedRoom + " (Overwritten)";
            }
            allRoomsDisplayTemp.getItems().remove(selectedIndex);
            allRoomsDisplayTemp.getItems().add(selectedIndex, selectedRoom);
        }
    }


    /** 
     * Displays the temperature of a room in the console
     */
    @FXML
    private void printRoomTemperature(){
        String roomName = (String) this.allRoomsDisplayTemp.getSelectionModel().getSelectedItem();

        if(roomName.contains(" (Overwritten)")){
            roomName = roomName.substring(0, roomName.length() - 14);
        }
        this.heatingModule.displayTemperatureForRoom(roomName, simulation.getCurrentUser());
    }

    
    /**
     * Starts the timer
     */
    private void startTimer(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    simulation.updateTime();
                    setTime(simulation.getTime());
                    setDate(simulation.getDate());
                });
            }
        }, simulation.getTimeInterval() ,simulation.getTimeInterval());
    }


    protected void stopTimer(){
        this.timer.cancel();
        System.out.println("Timer has been stopped");
    }

    
    /** 
     * save contents that were entered
     * @param event
     */
    @FXML
    private void save(MouseEvent event){
        simulation.printToTxtFile();
        Date currentDateTime = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        lastSaved.setText(format.format(currentDateTime));
    }

    
    /** 
     * return the toggle button
     * @return ToggleButton
     */
    @FXML
    protected ToggleButton getSimToggle(){
        return simulationToggle;
    }

    //*****************************************************************************************************//
    //                                                                                                     //
    //                                         Room Controls Controller                                    //
    //                                                                                                     //
    //*****************************************************************************************************//   

    /**
     * Allows room to be selected from combobox.
     */
    @FXML
    void selectingRoom(MouseEvent event){
        if(SimulationParameters.getInstance().getSimulationStatus()==true){
        System.out.println("arrived displayRoomList");
        roomList.setItems(FXCollections.observableList(House.getInstance().getRoomsNameList()));
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
                    lightList.setItems(FXCollections.observableList(currentRoom.getLightsNameList()));
                    doorList.setItems(FXCollections.observableList(currentRoom.getDoorsNameList()));
                    windowList.setItems(FXCollections.observableList(currentRoom.getWindowsNameList()));
                    resetAllButtonColours();

                }
            });
            return cell ;
        });
    }
        else return;
    }

    @FXML
    void modifyLight(MouseEvent event){
        if(SimulationParameters.getInstance().getSimulationStatus()==true){
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

                    //room is selected and the string is stored in selected room
                    selectedLight = cell.getItem();
                    //Selected room now as an object in currentRoom
                    currentLight = currentRoom.getLightByName(selectedLight);
                    changeLightButtonsColours();
                    changeLightAutoButtonsColours();
                }
            });
            return cell ;
        });
        }else return;
    }

    @FXML
    void modifyDoor(MouseEvent event){
        if(SimulationParameters.getInstance().getSimulationStatus()==true){
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

                    //room is selected and the string is stored in selected room
                    selectedDoor = cell.getItem();
                    //Selected room now as an object in currentRoom
                    currentDoor = currentRoom.getDoorByName(selectedDoor);
                    changeDoorButtonsColours();
                }
            });
            return cell ;
        });
        }else return;
    }

    @FXML
    void modifyWindow(MouseEvent event){
        if(SimulationParameters.getInstance().getSimulationStatus()==true){
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

                    //room is selected and the string is stored in selected room
                    selectedWindow = cell.getItem();
                    //Selected room now as an object in currentRoom
                    currentWindow = currentRoom.getWindowByName(selectedWindow);
                    changeWindowButtonsColours();
                }
            });
            return cell ;
        });
        }else return;
    }

    /**
     * Turns on light.
     */
    @FXML 
    void lightON (MouseEvent event){
        if(currentLight==null || SimulationParameters.getInstance().getSimulationStatus()==false){
            return;
        }
        else{
            System.out.println("lightON");
            currentLight.setToOn();
            changeLightButtonsColours();
            int currentRoomID = Integer.parseInt(currentRoom.getId().substring(4));
            lightImages[currentRoomID].setVisible(true);
        }
    }   

    /**
     * Turns off light.
     */
    @FXML
    void lightOff(MouseEvent event){
        if(currentLight==null || SimulationParameters.getInstance().getSimulationStatus()==false){
            return;
        }
        else{
            System.out.println("lightOFF");
            currentLight.setToOff();
            changeLightButtonsColours();
            int currentRoomID = Integer.parseInt(currentRoom.getId().substring(4));
            lightImages[currentRoomID].setVisible(false);
        }
    }
    
    /**
     * Turns auto light mode on.
     */
    @FXML
    void lightAutoOn(MouseEvent event){
        if(currentLight==null || SimulationParameters.getInstance().getSimulationStatus()==false){
            return;
        }
        else{
        System.out.println("lightAutoOn");
        currentLight.setAutoOn();
        changeLightAutoButtonsColours();
        }
        
    }

    /**
     * Turns auto light mode off.
     */
    @FXML
    void lightAutoOff(MouseEvent event){
        if(currentLight==null || SimulationParameters.getInstance().getSimulationStatus()==false){
            return;
        }
        else{
        System.out.println("lightAutoOFF");
        currentLight.setAutoOff();
        changeLightAutoButtonsColours();
        }

    }

    /**
     * Unlocks door.
     */
    @FXML
    void doorUnlock(MouseEvent event){
        if(currentDoor==null || SimulationParameters.getInstance().getSimulationStatus()==false){
            return;
        }
        else{
            System.out.println("doorUnlock");
            currentDoor.setUnlocked();
            changeDoorButtonsColours();
            int currentRoomID = Integer.parseInt(currentRoom.getId().substring(4));
            doorImages[currentRoomID].setImage(unlockedIcon);
        }
    }

    /**
     * Locks door.
     */
    @FXML
    void doorLock(MouseEvent event){
        if(currentDoor==null || SimulationParameters.getInstance().getSimulationStatus()==false){
            return;
        }
        else{
            System.out.println("doorLock");
            currentDoor.setLocked();
            changeDoorButtonsColours();
            int currentRoomID = Integer.parseInt(currentRoom.getId().substring(4));
            doorImages[currentRoomID].setImage(lockedIcon);
        }

    }

    /**
     * Opens window.
     */
    @FXML
    void windowOpen(MouseEvent event){
        if(currentWindow==null || SimulationParameters.getInstance().getSimulationStatus()==false){
            return;
        }
        else{
            System.out.println("windowOpen");
            //insert if statement here to check for obstructions
            currentWindow.setOpen();
            changeWindowButtonsColours();
            int currentRoomID = Integer.parseInt(currentRoom.getId().substring(4));
            windowImages[currentRoomID].setImage(openWindowIcon);
        }
    }

    /**
     * Closes window.
     */
    @FXML
    void windowClose(MouseEvent event){
        if(currentWindow==null || SimulationParameters.getInstance().getSimulationStatus()==false){
            return;
        }
        else{
            System.out.println("windowClose");
            //insert if statement here to check for obstructions
            currentWindow.setClosed();
            changeWindowButtonsColours();
            int currentRoomID = Integer.parseInt(currentRoom.getId().substring(4));
            windowImages[currentRoomID].setImage(closedWindowIcon);
        }
    }

    /**
     * Changes colour of light button.
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
     * Changes colour of auto light button.
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
     * Changes colour of door button.
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
     * Changes colour of window button.
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
     * Resets all button colours.
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

