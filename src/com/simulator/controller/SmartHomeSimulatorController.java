package com.simulator.controller;

import com.simulator.model.Profile;
import com.simulator.model.Room;
import com.simulator.model.SecurityModule;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
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
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.simulator.model.*;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;

/**
 * This is the controller class the Dashboard.fxml file
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
    @FXML private ImageView[] temperatureImages;

    @FXML private ImageView awayIcon1;
    @FXML private ImageView awayIcon2;

    @FXML private TextArea outputConsole;

    @FXML private ListView allLightsListView;
    @FXML private ListView selectedLightsListView;
    @FXML private TextField startTimeSecurity;
    @FXML private TextField endTimeSecurity;
    @FXML private TextField motionDetectedTimeSecurity;

    @FXML private ListView allRoomsDisplayTemp;
    @FXML private TextField setTemperatureSingleRoom;
    @FXML private TextField winterAwayModeTemperature;
    @FXML private TextField summerAwayModeTemperature;
    @FXML private TextField SummerStart;
    @FXML private TextField SummerEnd;
    @FXML private TextField WinterStart;
    @FXML private TextField WinterEnd;

    @FXML private Label lastSaved;

    @FXML private ListView allRoomsListViewZone;
    @FXML private ListView selectedRoomsListForZone;
    @FXML private ListView listZones;
    @FXML private TextField temperatureTextField;
    @FXML private ComboBox temperatureComboBox;
    @FXML private TextField zoneName;

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
    @FXML private Button windowBlock;
    @FXML private Button windowUnblock;
    @FXML private Button finished;

    private Room currentRoom=null;
    private Light currentLight=null;
    private Door currentDoor=null;
    private Window currentWindow=null;
    private String selectedRoom;
    private String selectedLight;
    private String selectedDoor;
    private String selectedWindow;

    private static final String RESOURCE_PATH = "/com/simulator/view/";
    private javafx.scene.image.Image lightOnIcon = new javafx.scene.image.Image(getClass().getResource(RESOURCE_PATH + "lightOn.jpg").toExternalForm());
    private javafx.scene.image.Image lightOffIcon = new javafx.scene.image.Image(getClass().getResource(RESOURCE_PATH + "lightOff.png").toExternalForm());
    private javafx.scene.image.Image unlockedIcon = new javafx.scene.image.Image(getClass().getResource(RESOURCE_PATH + "unlocked.png").toExternalForm());
    private javafx.scene.image.Image lockedIcon = new javafx.scene.image.Image(getClass().getResource(RESOURCE_PATH + "locked.png").toExternalForm());
    private javafx.scene.image.Image openWindowIcon = new javafx.scene.image.Image(getClass().getResource(RESOURCE_PATH + "openwindow.png").toExternalForm());
    private javafx.scene.image.Image closedWindowIcon = new javafx.scene.image.Image(getClass().getResource(RESOURCE_PATH + "closedwindow.png").toExternalForm());
    private javafx.scene.image.Image personIcon = new javafx.scene.image.Image(getClass().getResource(RESOURCE_PATH + "person.png").toExternalForm());
    private javafx.scene.image.Image heaterIcon = new javafx.scene.image.Image(getClass().getResource(RESOURCE_PATH + "heater.png").toExternalForm());
    private javafx.scene.image.Image acIcon = new javafx.scene.image.Image(getClass().getResource(RESOURCE_PATH + "ac.png").toExternalForm());

    private SecurityModule securityModule;
    private HeatingModule heatingModule;
    private Timer timer = new Timer();

    /**
     * The Security permission.
     */
    Permission securityPermission;

    /**
     * Instantiates a new Smart home simulator controller.
     */
    public SmartHomeSimulatorController()
    {
        try {
            this.house = House.getInstance();
        }
        catch (HouseLoadException houseLoadException)
        {
            houseLoadException.printStackTrace();
            System.out.println(houseLoadException.getMessage());
        }
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
        setTime(simulation.getTimeAndUpdate());
        initializeHouseView();
        setHouseView();
        // Initialize lists in fxml with values from house template
        setLights(house);
        setRoomsInHeatingModule(house);
        setInitialRoomTemperatures();
        Date currentDateTime = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        lastSaved.setText(format.format(currentDateTime));
        startTimer();
        //creating a new instance of the logger with the output console so that other classes can use it
        Logger.newInstance(outputConsole);
        Logger.getInstance().resetLogFile();
        this.securityModule = new SecurityModule(simulation.getAllUsers(), awayModeToggle, this.simulation.getTimeObject());
        this.heatingModule = new HeatingModule(this.simulation.getTimeObject(), this.securityModule);
        temperatureComboBox.getItems().addAll("Morning", "Day", "Night");

    }

    /**
     * Set initial room temperatures.
     */
    public void setInitialRoomTemperatures(){
        for(Room room : this.house.getRooms()){
            room.resetTemperature(this.simulation.getTemperature());
        }
    }

    /**
     * Changes the simulation status to on or off
     *
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
     *
     * @param event Referring to a mouse activity by the user Calls in the SystemParameter controller to get the user desired values, and changes the values on the dashboard.
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
            stage.focusedProperty().addListener((ov, onHidden, onShown) -> {
                if(!stage.isFocused())
                    Platform.runLater(() -> stage.close());
            });

            if(simulation.getSimulationStatus())
                timer.cancel();

            stage.showAndWait();
            
            if(simulation.getSimulationStatus())
                startTimer();

            setTemperature(simulation.getTemperature());
            setDate(simulation.getDate());
            setLocation(simulation.getCurrentUser().getCurrentRoom());
            setProfile(simulation.getCurrentUser());
            setTime(simulation.getTimeAndUpdate());
            setUsersInLayout();
        }
        catch (Exception e){
            e.printStackTrace();
        }  
    }

    /**
     * Initializes the house view
     *
     */
    private void initializeHouseView(){
        areas = new TextArea[NUMBER_OF_GRID_ELEMENTS + 1];
        lightImages = new ImageView[NUMBER_OF_GRID_ELEMENTS + 1];
        doorImages = new ImageView[NUMBER_OF_GRID_ELEMENTS + 1];
        windowImages = new ImageView[NUMBER_OF_GRID_ELEMENTS + 1];
        personImages = new ImageView[NUMBER_OF_GRID_ELEMENTS + 1];
        temperatureImages = new ImageView[NUMBER_OF_GRID_ELEMENTS + 1];

        for (int i = 1; i <= NUMBER_OF_GRID_ELEMENTS ; i++)
        {
            areas[i] = (TextArea) houseLayoutPane.lookup("#area" + i);
            lightImages[i] = (ImageView) houseLayoutPane.lookup("#a"+ i +"light");
            doorImages[i] = (ImageView) houseLayoutPane.lookup("#a"+ i +"door");
            windowImages[i] = (ImageView) houseLayoutPane.lookup("#a"+ i +"window");
            personImages[i] = (ImageView) houseLayoutPane.lookup("#a"+ i +"person");
            temperatureImages[i] = (ImageView) houseLayoutPane.lookup("#a"+ i +"temperature");
        }
        
        for (int i = 1; i <= NUMBER_OF_GRID_ELEMENTS; i++) {
            areas[i].setVisible(false);
            lightImages[i].setVisible(false);
            doorImages[i].setVisible(false);
            windowImages[i].setVisible(false);
            personImages[i].setVisible(false);
            temperatureImages[i].setVisible(false);
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
            areas[counter].setVisible(false);
            lightImages[counter].setVisible(false);
            doorImages[counter].setVisible(false);
            windowImages[counter].setVisible(false);
            personImages[counter].setVisible(false);
            temperatureImages[counter].setVisible(false);
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
        setUsersInLayout();
        setTemperatureIconsInLayout();
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
            }
        }
    }

    /**
     * Sets users in the house layout view.
     */
    @FXML
    public void setUsersInLayout(){
        //reset all user icons first
        for (int loop=1; loop < (NUMBER_OF_GRID_ELEMENTS + 1); loop++)
        {
               personImages[loop].setVisible(false);
        }

        //add user icons to any room that contains at least one user
        for (int loop=0; loop< simulation.getAllUsers().size(); loop++)
        {
            String roomIDstring = simulation.getAllUsers().get(loop).getCurrentRoom().getId();
            if(roomIDstring.length() > 4) {
                int currentRoomID = Integer.parseInt(roomIDstring.substring(4));
                ImageView currentRoomPersonImage = personImages[currentRoomID];
                currentRoomPersonImage.setImage(personIcon);
                currentRoomPersonImage.setVisible(true);
            }
        }
    }

     /**
     * Sets AC/Heating Icons in the house layout view.
     */
    @FXML
    public void setTemperatureIconsInLayout(){
        List<Room> rooms = house.getRooms();
        for (Room room : rooms)
        {
            String roomIDstring = room.getId();
            if(room.getCurrentStateHVAC() && roomIDstring.length()>=4) {
                int currentRoomID = Integer.parseInt(roomIDstring.substring(4));

                if(room.getTemperature().getTemperatureTarget() > room.getTemperature().getCurrentTemperature()){
                    ImageView currentRoomTemperatureImage = temperatureImages[currentRoomID];
                    currentRoomTemperatureImage.setImage(heaterIcon);
                    currentRoomTemperatureImage.setVisible(true);
                }
                else if(room.getTemperature().getTemperatureTarget() < room.getTemperature().getCurrentTemperature()){ 
                    ImageView currentRoomTemperatureImage = temperatureImages[currentRoomID];
                    currentRoomTemperatureImage.setImage(acIcon);
                    currentRoomTemperatureImage.setVisible(true);                
                }         
            }
        }
    }

    /**
     * Adds room to the selected rooms panel
     */
    @FXML
    private void addRoomToSelectedRoomsPanel(){
        Object selectedItem = this.allRoomsListViewZone.getSelectionModel().getSelectedItem();
        this.selectedRoomsListForZone.getItems().add((String) selectedItem);
        this.allRoomsListViewZone.getItems().remove(selectedItem);
    }

    /**
     * Removes room to the selected rooms panel
     */
    @FXML
    private void removeRoomFromSelectedRoomsPanel(){
        Object selectedItem = this.selectedRoomsListForZone.getSelectionModel().getSelectedItem();
        this.selectedRoomsListForZone.getItems().remove((String) selectedItem);
        this.allRoomsListViewZone.getItems().add(selectedItem);
    }

    /**
     * Adds preset temperature to zone
     */
    @FXML
    private void addPresetTemperatureToZone(){
        if(temperatureTextField.getText() == null || temperatureTextField.getText().trim().isEmpty() || !temperatureTextField.getText().matches("[0-9]+")){
            Logger.getInstance().outputToConsole("Please make sure a valid temperature is entered");
            return;
        }
        int tempPreset = Integer.valueOf(temperatureTextField.getText());
        if(listZones.getSelectionModel().isEmpty()){
            Logger.getInstance().outputToConsole("Please make sure you have selected a zone");
            return;
        }
        String selectedZone = (String)listZones.getSelectionModel().getSelectedItem();
        if(temperatureComboBox.getSelectionModel().isEmpty()){
            Logger.getInstance().outputToConsole("Please make sure you have selected a period of time");
            return;
        }
        String selectedPeriodOfTheDay = (String)temperatureComboBox.getSelectionModel().getSelectedItem();

        heatingModule.setTempForZone(selectedZone, selectedPeriodOfTheDay, tempPreset);

        //Resetting fields to contain the proper room names
        ArrayList<String> newRoomArrayList = heatingModule.getZonesRoomsByZoneName(selectedZone);
        //resetting the rooms in the Display Temperature list to remove the word "overwritten"
        for(int i=0; i<newRoomArrayList.size(); ++i) {
            if (newRoomArrayList.get(i).contains("Overwritten")) {
                house.getRoomByName(newRoomArrayList.get(i)).setName(newRoomArrayList.get(i).substring(0, newRoomArrayList.get(i).length() - 14));
                allRoomsDisplayTemp.getItems().clear();
                allRoomsDisplayTemp.getItems().addAll(house.getRoomsNameList());
            }
        }
        selectedRoomsListForZone.getItems().clear();
        allRoomsListViewZone.getItems().clear();
        allRoomsListViewZone.getItems().addAll(allRoomsDisplayTemp.getItems());
    }

    /**
     * Saves the SHH settings
     */
    @FXML
    private void saveSHHSettings(MouseEvent event){
        try {
            heatingModule.setSummerTemperatureAwayMode(Double.parseDouble(summerAwayModeTemperature.getText()));
            Logger.getInstance().outputToConsole("Summer away mode temperature successfully updated");
        }
        catch (NumberFormatException e)
        {
            Logger.getInstance().outputToConsole("Could not parse entry for summer away mode temperature");
        }
        try {
            heatingModule.setWinterTemperatureAwayMode(Double.parseDouble(winterAwayModeTemperature.getText()));
            Logger.getInstance().outputToConsole("Winter away mode temperature successfully updated");
        }
        catch (NumberFormatException e)
        {
            Logger.getInstance().outputToConsole("Could not parse entry for winter away mode temperature");
        }
        try {
            heatingModule.setSummerStartDate(Integer.parseInt(SummerStart.getText()));
            Logger.getInstance().outputToConsole("Summer Start Month successfully parsed");
        }
        catch (NumberFormatException e)
        {
            Logger.getInstance().outputToConsole("Could not parse entry for summer start month");
        }
        try {
            heatingModule.setSummerEndDate(Integer.parseInt(SummerEnd.getText()));
            Logger.getInstance().outputToConsole("Summer End Month successfully parsed");
        }
        catch (NumberFormatException e)
        {
            Logger.getInstance().outputToConsole("Could not parse entry for summer end month");
        }
        try {
            heatingModule.setWinterStartDate(Integer.parseInt(WinterStart.getText()));
            Logger.getInstance().outputToConsole("Winter Start Month successfully parsed");
        }
        catch (NumberFormatException e)
        {
            Logger.getInstance().outputToConsole("Could not parse entry for winter start month");
        }
        try {
            heatingModule.setWinterEndDate(Integer.parseInt(WinterEnd.getText()));
            Logger.getInstance().outputToConsole("Winter End Month successfully parsed");
        }
        catch (NumberFormatException e)
        {
            Logger.getInstance().outputToConsole("Could not parse entry for winter end month");
        }

    }

    /**
     * Creates a new Zone with the input information
     */
    @FXML
    private void createZoneButton(){
        //Check to see if the user selected some entries
        if(selectedRoomsListForZone.getItems().size() == 0){
            Logger.getInstance().outputToConsole("Please select some entries for the zone");
            return;
        }

        //check to see if the string already exists or if an invalid length string was entered
        if(zoneName.getText().length()<1 || !heatingModule.checkIfValidZoneName(zoneName.getText())){
            Logger.getInstance().outputToConsole("Please enter a valid name");
            return;
        }

        //Create a new ArrayList containing each of the selected rooms
        String newZoneName = zoneName.getText();
        ObservableList<String> newZoneObservableList = (ObservableList<String>) this.selectedRoomsListForZone.getItems();
        ArrayList<Room> newRoomArrayList = new ArrayList<Room>();

        for(int i = 0; i < newZoneObservableList.size(); ++i){
            newRoomArrayList.add(house.getRoomByName(newZoneObservableList.get(i)));
        }

        //If there is a word that is overwritten
        //check if there is a zone that already contains a specific room that is to be added to a zone
        for (int i = 0; i < newRoomArrayList.size(); ++i){
            if(newRoomArrayList.get(i).getBelongsToZone()){
                Logger.getInstance().outputToConsole("Room "+newRoomArrayList.get(i).getName()+" already belongs to a zone. \nIt's zone will be updated to "+newZoneName+".");
                heatingModule.removeARoomFromTheirZone(newRoomArrayList.get(i).getName());
            }
            newRoomArrayList.get(i).setBelongsToZone(true);
        }

        //create a new zone and assign the rooms and a name
        Zone newZone = new Zone(newRoomArrayList, newZoneName);
        this.heatingModule.addZone(newZone);
        newZone.printRoomsInZone();

        //Check if there are any non-existant zones that are being displayed in the display Zone window
        for(int k = 0; k < listZones.getItems().size(); ++k){
            if(heatingModule.checkIfValidZoneName((String) listZones.getItems().get(k))){
                listZones.getItems().remove(listZones.getItems().get(k));
            }
        }

        //Putting this new Zone into the Zone display window so that a temperature for the zone can be set
        this.listZones.getItems().addAll(newZone.getZoneName());

        //Resetting both RoomList and the selectedRoomsList to their initial values
        this.allRoomsListViewZone.getItems().addAll(this.selectedRoomsListForZone.getItems());
        this.selectedRoomsListForZone.getItems().clear();
        zoneName.setText("");

        //Log new creation
        Logger.getInstance().outputToConsole("Created a new Zone named \""+newZone.getZoneName()+"\" with rooms: ");
        for(int i = 0; i < newRoomArrayList.size(); ++i){
            Logger.getInstance().outputToConsole(newRoomArrayList.get(i).getName()+" ");
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
    private void setTemperature(double temperature) {
        this.displayTemp.setText(Double.toString(temperature) + "°C");
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
        String hours = String.format("%02d", time/3600);
        String minutes = String.format("%02d", (time%3600)/60);
        String seconds = String.format("%02d", time % 60);

        this.displayTime.setText(hours + ":" + minutes + ":" + seconds);
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
                int startTime = Integer.parseInt(startTimeInput) * 60;
                int endTime = Integer.parseInt(endTimeInput) * 60;
                int motionDetectedTime = Integer.parseInt(motionDetectedTimeInput) * 60;

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
                allRoomsListViewZone.getItems().add(room);
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
        final String selectedRoomNoOverwrite = selectedRoom;
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
            double newTemperatureInt = Double.parseDouble(setTemperatureSingleRoom.getText());

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

        //update rooms in list of rooms
        for(int i =0; i<allRoomsListViewZone.getItems().size(); ++i){
            if(((String) allRoomsListViewZone.getItems().get(i)).contains(selectedRoomNoOverwrite)){
                //remove the room that does not include "overwritten"
                this.allRoomsListViewZone.getItems().remove(i);
                //add the room that does contain the word "overwritten"
                this.allRoomsListViewZone.getItems().add(selectedRoom);
                break;
            }
        }

        //update rooms in list of selected rooms
        for(int i =0; i<selectedRoomsListForZone.getItems().size(); ++i){
            if(((String) selectedRoomsListForZone.getItems().get(i)).contains(selectedRoomNoOverwrite)){
                //remove the room that does not include "overwritten"
                this.selectedRoomsListForZone.getItems().remove(i);
                //add the room that does contain the word "overwritten"
                this.selectedRoomsListForZone.getItems().add(selectedRoom);
                break;
            }
        }

        //rename the old room in the house with the name from the new room
        for(int i=0; i<house.getRoomsNameList().size(); ++i){
            if(selectedRoom.contains(house.getRoomsNameList().get(i))){
                house.renameRoom(house.getRoomsNameList().get(i), selectedRoom);
                break;
            }
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
                    setTime(simulation.getTimeAndUpdate());
                    setDate(simulation.getDate());
                    setTemperatureIconsInLayout();
                });
            }
        }, simulation.getTimeInterval() ,simulation.getTimeInterval());
    }

    /**
     * Stop timer.
     */
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
     *
     * @return ToggleButton toggle button
     */
    @FXML
    protected ToggleButton getSimToggle(){
        return simulationToggle;
    }

    /**
     * Allows room to be selected from combobox.
     *
     * @param event the event
     */
    @FXML
    void selectingRoom(MouseEvent event){
        if(SimulationParameters.getInstance().getSimulationStatus()==true){
        System.out.println("arrived displayRoomList");
        roomList.setItems(FXCollections.observableList(house.getRoomsNameList()));
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
                    try {
                        currentRoom = House.getInstance().getRoomByName(selectedRoom);
                    }
                    catch (HouseLoadException houseLoadException){
                        houseLoadException.printStackTrace();
                        System.out.println(houseLoadException.getMessage());
                    }
                    System.out.println(currentRoom+" HERE HERE HERE");

                    //Populate the Selecting Lights, doors and windows field from the corresponding room
                    lightList.setItems(FXCollections.observableList(currentRoom.getLightsNameList()));
                    doorList.setItems(FXCollections.observableList(currentRoom.getDoorsNameList()));
                    windowList.setItems(FXCollections.observableList(currentRoom.getWindowsNameList()));
                    clearCurrentObjects();
                    resetAllButtonColours();

                }
            });
            return cell ;
        });
    }
        else return;
    }

    /**
     * Modify light.
     *
     * @param event the event
     */
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

    /**
     * Modify door.
     *
     * @param event the event
     */
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

    /**
     * Modify window.
     *
     * @param event the event
     */
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
     *
     * @param event the event
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
            lightImages[currentRoomID].setImage(lightOnIcon);
        }
    }

    /**
     * Turns off light.
     *
     * @param event the event
     */
    @FXML
    void lightOff(MouseEvent event){
        if(currentLight==null || SimulationParameters.getInstance().getSimulationStatus()==false){
            return;
        }
        else{
            System.out.println("lightOFF");
            int currentRoomID = Integer.parseInt(currentRoom.getId().substring(4));
            List<String> roomLights = currentRoom.getLightsNameList();
            int lightOnCount = 0;
            for (int loop = 0; loop<currentRoom.getLightsAmount();loop++)
            {
                if(currentRoom.getLightByName(roomLights.get(loop)).getOnOff()== true)
                {
                    lightOnCount++;
                }
            }
            if (lightOnCount == 1)
            {
                lightImages[currentRoomID].setImage(lightOffIcon);
            }
            currentLight.setToOff();
            changeLightButtonsColours();
        }
    }

    /**
     * Turns auto light mode on.
     *
     * @param event the event
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
     *
     * @param event the event
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
     *
     * @param event the event
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
     *
     * @param event the event
     */
    @FXML
    void doorLock(MouseEvent event){
        if(currentDoor==null || SimulationParameters.getInstance().getSimulationStatus()==false){
            return;
        }
        else{
            System.out.println("doorLock");
            int currentRoomID = Integer.parseInt(currentRoom.getId().substring(4));
            List<String> doorsLocked = currentRoom.getDoorsNameList();
            int doorsUnlockedCount = 0;
            for (int loop = 0; loop<currentRoom.getDoorsAmount();loop++)
            {
                if(currentRoom.getDoorByName(doorsLocked.get(loop)).getLockedStatus()== false)
                {
                    doorsUnlockedCount++;
                }
            }
            if (doorsUnlockedCount == 1)
            {
                doorImages[currentRoomID].setImage(lockedIcon);
            }
            currentDoor.setLocked();
            changeDoorButtonsColours();
        }

    }

    /**
     * Opens window.
     *
     * @param event the event
     */
    @FXML
    void windowOpen(MouseEvent event){
        if(currentWindow==null || SimulationParameters.getInstance().getSimulationStatus()==false){
            return;
        }
        else {
            System.out.println("windowOpen");
            if (currentWindow.setOpen()) {
                changeWindowButtonsColours();
                int currentRoomID = Integer.parseInt(currentRoom.getId().substring(4));
                windowImages[currentRoomID].setImage(openWindowIcon);
            }
        }
    }

    /**
     * Closes window.
     *
     * @param event the event
     */
    @FXML
    void windowClose(MouseEvent event){
        if(currentWindow==null || SimulationParameters.getInstance().getSimulationStatus()==false){
            return;
        }
        else{
            System.out.println("windowClose");
            if(currentWindow.setClosed()) {
                int currentRoomID = Integer.parseInt(currentRoom.getId().substring(4));
                List<String> windows = currentRoom.getWindowsNameList();
                int windowsOpenedCount = 0;
                for (int loop = 0; loop<currentRoom.getWindowsAmount();loop++)
                {
                    if(currentRoom.getWindowByName(windows.get(loop)).getOpenOrClosed() == true)
                    {
                        windowsOpenedCount++;
                    }
                }
                if (windowsOpenedCount == 0)
                {
                    windowImages[currentRoomID].setImage(closedWindowIcon);
                }
                changeWindowButtonsColours();
            }
        }
    }

    /**
     * Blocks window.
     *
     * @param event the event
     */
    @FXML
    void windowBlock(MouseEvent event){
        if(currentWindow==null || !SimulationParameters.getInstance().getSimulationStatus()){
            return;
        }
        else{
            System.out.println("windowBlock");
            currentWindow.setBlockedTrue();
            changeWindowButtonsColours();
        }
    }

    /**
     * Unblocks window.
     *
     * @param event the event
     */
    @FXML
    void windowUnblock(MouseEvent event){
        if(currentWindow==null || !SimulationParameters.getInstance().getSimulationStatus()){
            return;
        }
        else{
            System.out.println("windowClose");
            currentWindow.setBlockedFalse();
            changeWindowButtonsColours();
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

        if(currentWindow.getBlockedBoolean()){
            windowBlock.setStyle("-fx-background-color: #FF0000");
            windowUnblock.setStyle("-fx-all: initial");
        }
        else{
            windowBlock.setStyle("-fx-all: initial");
            windowUnblock.setStyle("-fx-background-color: #7FFF00");
        }
    }

    /**
     * Sets instances of currentLight, currentDoor, and currentWindow to null
     */
    private void clearCurrentObjects() {
        currentDoor = null;
        currentLight = null;
        currentWindow = null;
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
        windowBlock.setStyle("-fx-all: initial");
        windowUnblock.setStyle("-fx-all: initial");
    }

    /**
     * Closes the Room Control Panel window pop-up.
     *
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

