package com.simulator.gui;

import com.simulator.model.Profile;
import com.simulator.model.Room;
import com.simulator.model.SecurityModule;

import javafx.application.Platform;
/**
  * This is the controller class for the Dashboard.fxml file
  */
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.simulator.model.*;

/**
 * This is the controller class for the RoomControls.fxml file
 */
import javafx.collections.FXCollections;
import javafx.scene.image.Image;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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

    @FXML private TextArea area1;
    @FXML private TextArea area2;
    @FXML private TextArea area3;
    @FXML private TextArea area4;
    @FXML private TextArea area5;
    @FXML private TextArea area6;
    @FXML private TextArea area7;
    @FXML private TextArea area8;
    @FXML private TextArea area9;
    @FXML private TextArea area10; 
    @FXML private TextArea area11;
    @FXML private TextArea area12;
    @FXML private TextArea area13;
    @FXML private TextArea area14;
    @FXML private TextArea area15;
    @FXML private TextArea area16;
    @FXML private TextArea area17;
    @FXML private TextArea area18;
    @FXML private TextArea area19;
    @FXML private TextArea area20;
    @FXML private TextArea area21;
    @FXML private TextArea area22;
    @FXML private TextArea area23;
    @FXML private TextArea area24;
    @FXML private TextArea area25;

    @FXML private ImageView a1light;
    @FXML private ImageView a2light;
    @FXML private ImageView a3light;
    @FXML private ImageView a4light;
    @FXML private ImageView a5light;
    @FXML private ImageView a6light;
    @FXML private ImageView a7light;
    @FXML private ImageView a8light;
    @FXML private ImageView a9light;
    @FXML private ImageView a10light;
    @FXML private ImageView a11light;
    @FXML private ImageView a12light;
    @FXML private ImageView a13light;
    @FXML private ImageView a14light;
    @FXML private ImageView a15light;
    @FXML private ImageView a16light;
    @FXML private ImageView a17light;
    @FXML private ImageView a18light;
    @FXML private ImageView a19light;
    @FXML private ImageView a20light;
    @FXML private ImageView a21light;
    @FXML private ImageView a22light;
    @FXML private ImageView a23light;
    @FXML private ImageView a24light;
    @FXML private ImageView a25light;

    @FXML private ImageView a1door;
    @FXML private ImageView a2door;
    @FXML private ImageView a3door;
    @FXML private ImageView a4door;
    @FXML private ImageView a5door;
    @FXML private ImageView a6door;
    @FXML private ImageView a7door;
    @FXML private ImageView a8door;
    @FXML private ImageView a9door;
    @FXML private ImageView a10door;
    @FXML private ImageView a11door;
    @FXML private ImageView a12door;
    @FXML private ImageView a13door;
    @FXML private ImageView a14door;
    @FXML private ImageView a15door;
    @FXML private ImageView a16door;
    @FXML private ImageView a17door;
    @FXML private ImageView a18door;
    @FXML private ImageView a19door;
    @FXML private ImageView a20door;
    @FXML private ImageView a21door;
    @FXML private ImageView a22door;
    @FXML private ImageView a23door;
    @FXML private ImageView a24door;
    @FXML private ImageView a25door;

    @FXML private ImageView a1window;
    @FXML private ImageView a2window;
    @FXML private ImageView a3window;
    @FXML private ImageView a4window;
    @FXML private ImageView a5window;
    @FXML private ImageView a6window;
    @FXML private ImageView a7window;
    @FXML private ImageView a8window;
    @FXML private ImageView a9window;
    @FXML private ImageView a10window;
    @FXML private ImageView a11window;
    @FXML private ImageView a12window;
    @FXML private ImageView a13window;
    @FXML private ImageView a14window;
    @FXML private ImageView a15window;
    @FXML private ImageView a16window;
    @FXML private ImageView a17window;
    @FXML private ImageView a18window;
    @FXML private ImageView a19window;
    @FXML private ImageView a20window;
    @FXML private ImageView a21window;
    @FXML private ImageView a22window;
    @FXML private ImageView a23window;
    @FXML private ImageView a24window;
    @FXML private ImageView a25window;
    @FXML private TextArea outputConsole;

    @FXML private ListView allLightsListView;
    @FXML private ListView selectedLightsListView;
    @FXML private TextField startTimeSecurity;
    @FXML private TextField endTimeSecurity;
    @FXML private TextField motionDetectedTimeSecurity;

    @FXML private Label lastSaved;
    @FXML private TabPane tabPane;
    @FXML private Tab SHCTab;
    @FXML private RoomControlsController sHCTabPageController;
    @FXML private Tab SHPTab;
    

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

    private javafx.scene.image.Image lightIcon = new javafx.scene.image.Image(getClass().getResource("lightOn.jpg").toExternalForm());
    private javafx.scene.image.Image unlockedIcon = new javafx.scene.image.Image(getClass().getResource("unlocked.png").toExternalForm());
    private javafx.scene.image.Image lockedIcon = new javafx.scene.image.Image(getClass().getResource("locked.png").toExternalForm());
    private javafx.scene.image.Image openWindowIcon = new javafx.scene.image.Image(getClass().getResource("openwindow.png").toExternalForm());
    private javafx.scene.image.Image closedWindowIcon = new javafx.scene.image.Image(getClass().getResource("closedwindow.png").toExternalForm());



    private SecurityModule securityModule;
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
        setHouseView();
        setLights(house);
        Date currentDateTime = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        lastSaved.setText(format.format(currentDateTime));
        startTimer();
        //creating a new instance of the logger with the output console so that other classes can use it
        Logger.newInstance(outputConsole);
        Logger.getInstance().resetLogFile();
        this.securityModule = new SecurityModule(simulation.getAllUsers(), awayModeToggle, this.simulation.getTimeObject());
    }

    /**
     * Changes the simulation status to on or off
     * @param event Referring to a mouse activity by the user
     */
    @FXML
    void changeSimulationStatus(MouseEvent event) {
        if(simulation.getSimulationStatus()){
            this.simulationToggle.setText("On");
            simulation.setSimulationStatus(false);
            timer.cancel();
        } else {
            this.simulationToggle.setText("Off");
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ParameterEditor.fxml"));
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
    /**
     * Change status to away
     */
    @FXML
    private void setHouseView() {
        for (int Counter = 0; Counter < house.getRooms().size(); Counter++) {
            System.out.println(house.getRooms().get(Counter).getId());
            if (house.getRooms().get(Counter).getId().equals("area1")) {
                area1.setText(house.getRooms().get(Counter).getName());
                area1.setVisible(true);
            }
            if (house.getRooms().get(Counter).getId().equals("area2")) {
                area2.setText(house.getRooms().get(Counter).getName());
                area2.setVisible(true);
            }
            if (house.getRooms().get(Counter).getId().equals("area3")) {
                area3.setText(house.getRooms().get(Counter).getName());
                area3.setVisible(true);
            }
            if (house.getRooms().get(Counter).getId().equals("area4")) {
                area4.setText(house.getRooms().get(Counter).getName());
                area4.setVisible(true);
            }
            if (house.getRooms().get(Counter).getId().equals("area5")) {
                area5.setText(house.getRooms().get(Counter).getName());
                area5.setVisible(true);
            }
            if (house.getRooms().get(Counter).getId().equals("area6")) {
                area6.setText(house.getRooms().get(Counter).getName());
                area6.setVisible(true);
            }
            if (house.getRooms().get(Counter).getId().equals("area7")) {
                area7.setText(house.getRooms().get(Counter).getName());
                area7.setVisible(true);
            }
            if (house.getRooms().get(Counter).getId().equals("area8")) {
                area8.setText(house.getRooms().get(Counter).getName());
                area8.setVisible(true);
            }
            if (house.getRooms().get(Counter).getId().equals("area9")) {
                area9.setText(house.getRooms().get(Counter).getName());
                area9.setVisible(true);
            }
            if (house.getRooms().get(Counter).getId().equals("area10")) {
                area10.setText(house.getRooms().get(Counter).getName());
                area10.setVisible(true);
            }
            if (house.getRooms().get(Counter).getId().equals("area11")) {
                area11.setText(house.getRooms().get(Counter).getName());
                area11.setVisible(true);
            }
            if (house.getRooms().get(Counter).getId().equals("area12")) {
                area12.setText(house.getRooms().get(Counter).getName());
                area12.setVisible(true);
            }
            if (house.getRooms().get(Counter).getId().equals("area13")) {
                area13.setText(house.getRooms().get(Counter).getName());
                area13.setVisible(true);
            }
            if (house.getRooms().get(Counter).getId().equals("area14")) {
                area14.setText(house.getRooms().get(Counter).getName());
                area14.setVisible(true);
            }
            if (house.getRooms().get(Counter).getId().equals("area15")) {
                area15.setText(house.getRooms().get(Counter).getName());
                area15.setVisible(true);
            }
            if (house.getRooms().get(Counter).getId().equals("area16")) {
                area16.setText(house.getRooms().get(Counter).getName());
                area16.setVisible(true);
            }
            if (house.getRooms().get(Counter).getId().equals("area17")) {
                area17.setText(house.getRooms().get(Counter).getName());
                area17.setVisible(true);
            }
            if (house.getRooms().get(Counter).getId().equals("area18")) {
                area18.setText(house.getRooms().get(Counter).getName());
                area18.setVisible(true);
            }
            if (house.getRooms().get(Counter).getId().equals("area19")) {
                area19.setText(house.getRooms().get(Counter).getName());
                area19.setVisible(true);
            }
            if (house.getRooms().get(Counter).getId().equals("area20")) {
                area20.setText(house.getRooms().get(Counter).getName());
                area20.setVisible(true);
            }
            if (house.getRooms().get(Counter).getId().equals("area21")) {
                area21.setText(house.getRooms().get(Counter).getName());
                area21.setVisible(true);
            }
            if (house.getRooms().get(Counter).getId().equals("area22")) {
                area22.setText(house.getRooms().get(Counter).getName());
                area22.setVisible(true);
            }
            if (house.getRooms().get(Counter).getId().equals("area23")) {
                area23.setText(house.getRooms().get(Counter).getName());
                area23.setVisible(true);
            }
            if (house.getRooms().get(Counter).getId().equals("area24")) {
                area24.setText(house.getRooms().get(Counter).getName());
                area24.setVisible(true);
            }
            if (house.getRooms().get(Counter).getId().equals("area25")) {
                area25.setText(house.getRooms().get(Counter).getName());
                area25.setVisible(true);
            }
        }
    }

    public void changeAwayStatus() {
        securityModule.toggleAwayMode();
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
                Logger.getInstance().ouputToConsole("Security settings were successfully updated");
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
     * Initializes the SmartHomeSimulator Dashboard
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
    //                                            Room Controls Controller                                 //
    //                                                                                                     //
    //*****************************************************************************************************//   


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

    @FXML 
    void lightON (MouseEvent event){
        if(currentLight==null || SimulationParameters.getInstance().getSimulationStatus()==false){
            return;
        }
        else{
        System.out.println("lightON");
        currentLight.setToOn();
        changeLightButtonsColours();
        switch (currentRoom.getId()) 
        {
            case "area1": a1light.setVisible(true);
            break;
            case "area2": a2light.setVisible(true);
            break;
            case "area3": a3light.setVisible(true);
            break;
            case "area4": a4light.setVisible(true);
            break;
            case "area5": a5light.setVisible(true);
            break;
            case "area6": a6light.setVisible(true);
            break;               
            case "area7": a7light.setVisible(true);
            break;
            case "area8": a8light.setVisible(true);
            break;
            case "area9": a9light.setVisible(true);
            break;
            case "area10": a10light.setVisible(true);
            break;
            case "area11": a11light.setVisible(true);
            break;
            case "area12": a12light.setVisible(true);
            break;
            case "area13": a13light.setVisible(true);
            break;
            case "area14": a14light.setVisible(true);
            break;
            case "area15": a15light.setVisible(true);
            break;
            case "area16": a16light.setVisible(true);
             break;
            case "area17": a17light.setVisible(true);
            break;
            case "area18": a18light.setVisible(true);
            break;
            case "area19": a19light.setVisible(true);
            break;
            case "area20": a20light.setVisible(true);
            break;
            case "area21": a21light.setVisible(true);
            break;
            case "area22": a22light.setVisible(true);
            break;
            case "area23": a23light.setVisible(true);
            break;
            case "area24": a24light.setVisible(true);
            break;
            case "area25": a25light.setVisible(true);
            break;
            default: break;
        }
    }   
    }
    @FXML
    void lightOff(MouseEvent event){
        if(currentLight==null || SimulationParameters.getInstance().getSimulationStatus()==false){
            return;
        }
        else{
        System.out.println("lightOFF");
        currentLight.setToOff();
        changeLightButtonsColours();
        switch (currentRoom.getId()) 
        {
            case "area1": a1light.setVisible(false);
            break;
            case "area2": a2light.setVisible(false);
            break;
            case "area3": a3light.setVisible(false);
            break;
            case "area4": a4light.setVisible(false);
            break;
            case "area5": a5light.setVisible(false);
            break;
            case "area6": a6light.setVisible(false);
            break;               
            case "area7": a7light.setVisible(false);
            break;
            case "area8": a8light.setVisible(false);
            break;
            case "area9": a9light.setVisible(false);
            break;
            case "area10": a10light.setVisible(false);
            break;
            case "area11": a11light.setVisible(false);
            break;
            case "area12": a12light.setVisible(false);
            break;
            case "area13": a13light.setVisible(false);
            break;
            case "area14": a14light.setVisible(false);
            break;
            case "area15": a15light.setVisible(false);
            break;
            case "area16": a16light.setVisible(false);
             break;
            case "area17": a17light.setVisible(false);
            break;
            case "area18": a18light.setVisible(false);
            break;
            case "area19": a19light.setVisible(false);
            break;
            case "area20": a20light.setVisible(false);
            break;
            case "area21": a21light.setVisible(false);
            break;
            case "area22": a22light.setVisible(false);
            break;
            case "area23": a23light.setVisible(false);
            break;
            case "area24": a24light.setVisible(false);
            break;
            case "area25": a25light.setVisible(false);
            break;
            default: break;
        }
        }
    }
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
    @FXML
    void doorUnlock(MouseEvent event){
        if(currentDoor==null || SimulationParameters.getInstance().getSimulationStatus()==false){
            return;
        }
        else{
        System.out.println("doorUnlock");
        currentDoor.setUnlocked();
        changeDoorButtonsColours();
        switch (currentRoom.getId()) 
        {
            
            case "area8": a8door.setVisible(true);
            a8door.setImage(unlockedIcon);

            break;
            case "area14": a14door.setVisible(true);
            a14door.setImage(unlockedIcon);

            break;
            case "area18": a18door.setVisible(true);
                           a18door.setImage(unlockedIcon);
                           break;
            default: break;
        }
        }
    }
    @FXML
    void doorLock(MouseEvent event){
        if(currentDoor==null || SimulationParameters.getInstance().getSimulationStatus()==false){
            return;
        }
        else{
        System.out.println("doorLock");
        currentDoor.setLocked();
        changeDoorButtonsColours();
        switch (currentRoom.getId()) 
        {
            case "area8": a8door.setImage(lockedIcon);
            break;
            case "area14": a14door.setImage(lockedIcon);
            break;
            case "area18": a18door.setImage(lockedIcon);
            break;
            default: break;
        }
        }

    }
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
        switch (currentRoom.getId()) 
        {
        case "area18": a18window.setImage(openWindowIcon);
        
            break;
            default: break;
        }
    }
    }
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
        switch (currentRoom.getId()) 
        {
        case "area18": a18window.setImage(closedWindowIcon);

        break;
        default: break;
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

    @FXML
    private void setLightIcon() {
        System.out.println("Lights switched");
    }
    
}

