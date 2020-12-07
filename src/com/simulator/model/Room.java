package com.simulator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * class type Room used to create Room objects which have doors, windows, a room motionsensor, a name and an ID
 */
public class Room {
    private String name;
    private String id;
    private List<Door> doors;
    private List<Window> windows;
    private List<Light> lights;
    private Temperature temperatureSettings;
    private boolean belongsToZone = false;
    private boolean overriddenTemperature = false;
    private boolean currentStateHVAC = false;

    /**
     * Instantiates a new Room.
     *
     * @param newName the new name
     * @param ID      the id
     */
    public Room(String newName, String ID){
        this.id =  ID;
        this.name = newName;
        this.doors = new ArrayList<>();
        this.windows = new ArrayList<>();
        this.lights = new ArrayList<>();
        this.temperatureSettings = new Temperature();
    }

    /**
     * Reset temperature.
     *
     * @param temperature the temperature
     */
    public void resetTemperature(double temperature){
        this.temperatureSettings.setAllTemperature(temperature);
    }

    /**
     * Add a new door with the name of the door included
     *
     * @param newName the new name
     * @return int int
     */
    public int addDoor(String newName){
        this.doors.add(new Door(newName));
        return 0;
    }

    /**
     * Add a new window with the name of the window included
     *
     * @param newName the new name
     * @return int int
     */
    public int addWindow(String newName){
        this.windows.add(new Window(newName));
        return 0;
    }

    /**
     * Add a new light with the name of the light included
     *
     * @param newName the new name
     * @return int int
     */
    public int addLight(String newName){
        this.lights.add(new Light(newName));
        return 0;
    }

    /**
     * Set belongs to zone.
     *
     * @param belongsToZone the belongs to zone
     */
    public void setBelongsToZone(boolean belongsToZone){
        this.belongsToZone = belongsToZone;
    }

    /**
     * Get belongs to zone boolean.
     *
     * @return the boolean
     */
    public boolean getBelongsToZone(){
        return this.belongsToZone;
    }

    /**
     * Get the name of the room
     *
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name.
     *
     * @param newName the new name
     */
    public void setName(String newName){name=newName;}

    /**
     * Set overridden.
     *
     * @param isOvveride the is ovveride
     */
    public void setOverridden(boolean isOvveride){
        this.overriddenTemperature = isOvveride;
    }

    /**
     * Get overridden boolean.
     *
     * @return the boolean
     */
    public boolean getOverridden(){
        return this.overriddenTemperature;
    }

    /**
     * Get the temperature of the room
     *
     * @return int temperature
     */
    public Temperature getTemperature() {
        return this.temperatureSettings;
    }


    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Get a door by providing the name of the door
     *
     * @param targetName the target name
     * @return Door door by name
     */
    public Door getDoorByName(String targetName) {
        for (Door d : doors) {
            if (d.getName().equals(targetName)) {
                return d;
            }
        }
        return null;
    }

    /**
     * Get a light by providing the name of the door
     *
     * @param targetName the target name
     * @return Light light by name
     */
    public Light getLightByName(String targetName) {
        for (Light l : lights) {
            if (l.getName().equals(targetName)) {
                return l;
            }
        }
        return null;
    }

    /**
     * Get a window by providing the name of the window
     *
     * @param targetName the target name
     * @return Window window by name
     */
    public Window getWindowByName(String targetName) {
        for (Window w : windows) {
            if (w.getName().equals(targetName)) {
                return w;
            }
        }
        return null;
    }

    /**
     * Get the number of doors in the room
     *
     * @return int int
     */
    public int getDoorsAmount(){
        return doors.size();
    }

    /**
     * Get the number of windows in the room
     *
     * @return int int
     */
    public int getWindowsAmount(){
        return windows.size();
    }

    /**
     * Get the number of Lights in the room
     *
     * @return int int
     */
    public int getLightsAmount(){
        return lights.size();
    }

    /**
     * Get a list of the names of the doors
     *
     * @return List<String> list
     */
    public List<String> getDoorsNameList(){
        List<String> doorsListString = new ArrayList<String>();
        for(int i=0; i<doors.size(); ++i){
            doorsListString.add(doors.get(i).getName());
        }
        return doorsListString;
    }

    /**
     * get a list of the names of the lights
     *
     * @return List<String> list
     */
    public List<String> getLightsNameList(){
        List<String> lightsListString = new ArrayList<String>();
        for(int i=0; i<lights.size(); ++i){
            lightsListString.add(lights.get(i).getName());
        }
        return lightsListString;
    }

    /**
     * Get a list of the names of the windows
     *
     * @return List<String> list
     */
    public List<String> getWindowsNameList(){
        List<String> windowsListString = new ArrayList<String>();
        for(int i=0; i<windows.size(); ++i){
            windowsListString.add(windows.get(i).getName());
        }
        return windowsListString;
    }

    /**
     * Get a list of the windows
     *
     * @return List<Window>
     */
    public void openAllWindows(){
        for(Window window : this.windows){
            window.setOpen();
        }
    }

    /**
     * Turn on all lights in the room that are set to auto
     */
    public void turnOnAutoLights(){
        for (Light l : lights) {
            if(l.getAuto())
                l.setToOn();
        }
    }

    /**
     * Set current state hvac.
     *
     * @param currentStateHVAC the current state hvac
     */
    public void setCurrentStateHVAC(boolean currentStateHVAC){
        this.currentStateHVAC = currentStateHVAC;
    }


    /**
     * Get current state hvac boolean.
     *
     * @return the boolean
     */
    public boolean getCurrentStateHVAC(){
        return this.currentStateHVAC;
    }

    /**
     * turn off all lights in room that are set to auto
     */
    public void turnOffAutoLights(){
        for (Light l : lights) {
            if(l.getAuto())
                l.setToOff();
        }
    }

    /**
     * Update temperature.
     *
     * @param outdoorTemperature the outdoor temperature
     */
    public void updateTemperature(double outdoorTemperature){
        double currentTemp = this.temperatureSettings.getCurrentTemperature();
        double targetTemp = this.temperatureSettings.getTemperatureTarget();
        if(this.currentStateHVAC){
            if(targetTemp > currentTemp){
                this.temperatureSettings.setCurrentTemperature(currentTemp + 0.1);
            }
            else if(targetTemp < currentTemp){ 
                this.temperatureSettings.setCurrentTemperature(currentTemp - 0.1);
            }
        }
        else{
            if(outdoorTemperature > currentTemp){
                this.temperatureSettings.setCurrentTemperature(currentTemp + 0.05);
            }
            else if(outdoorTemperature < currentTemp){ 
                this.temperatureSettings.setCurrentTemperature(currentTemp - 0.05);
            }
        }
    }
}
