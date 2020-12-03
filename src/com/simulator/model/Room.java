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
    private MotionSensor motionSensors;
    private int temperature;
    private int temperatureMorning;
    private int temperatureDay;
    private int temperatureNight;
    private boolean belongsToZone = false;

    public Room(String newName, String ID){
        this.id =  ID;
        this.name = newName;
        this.doors = new ArrayList<>();
        this.windows = new ArrayList<>();
        this.lights = new ArrayList<>();
        this.temperature = 20;
        this.temperatureMorning = 20;
        this.temperatureDay = 20;
        this.temperatureNight = 20;
    }

    
    /** 
     * Add a new door with the name of the door included
     * @param newName
     * @return int
     */
    public int addDoor(String newName){
        this.doors.add(new Door(newName));
        return 0;
    }

    
    /** 
     * Add a new window with the name of the window included
     * @param newName
     * @return int
     */
    public int addWindow(String newName){
        this.windows.add(new Window(newName));
        return 0;
    }

    
    /** 
     * Add a new light with the name of the light included
     * @param newName
     * @return int
     */
    public int addLight(String newName){
        this.lights.add(new Light(newName));
        return 0;
    }

    public void setBelongsToZone(boolean tf){
        belongsToZone = tf;
    }
    public boolean getBelongsToZone(){
        return belongsToZone;
    }
    
    /** 
     * Add new motion sensors
     * @param newName
     * @return int
     */
    public int addMotionSensor(String newName){
        this.motionSensors = new MotionSensor(newName);
        return 0;
    }
    
    /** 
     * Add new entryway sensors
     * @param newName
     * @param targetName
     * @return int
     */
    public int addEntrywaySensor(String newName, String targetName){
        List<Entryway> doorsAndWindows = new ArrayList<Entryway>(doors);
        doorsAndWindows.addAll(windows);
        for (Entryway e: doorsAndWindows) {
            if(e.getName().equals(targetName)) {
                e.addEntrywaySensor(newName);
                return 0;
            }
        }
        return 1;
    }

    
    /** 
     * Get the name of the room
     * @return String
     */
    public String getName() {
        return name;
    }
    
    
    /** 
     * Get the temperature of the room
     * @return int 
     */
    public int getTemperature() {
        return this.temperature;
    }
    public int getTemperatureMorning() {
        return this.temperatureMorning;
    }
    public int getTemperatureDay() {
        return this.temperatureDay;
    }
    public int getTemperatureNight() {
        return this.temperatureNight;
    }


    /** 
     * Set the temperature of the room
     */
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
    public void setTemperatureMorning(int temperature) {
        this.temperatureMorning = temperature;
    }
    public void setTemperatureDay(int temperature) {
        this.temperatureDay = temperature;
    }
    public void setTemperatureNight(int temperature) {
        this.temperatureNight = temperature;
    }



    public String getId() {
        return id;
    }

  /** 
     * Get a door by providing the name of the door
     * @param targetName
     * @return Door
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
     * @param targetName
     * @return Light
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
     * @param targetName
     * @return Window
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
     * @return int
     */
    public int getDoorsAmount(){
        return doors.size();
    }
    
    /** 
     * Get the number of windows in the room
     * @return int
     */
    public int getWindowsAmount(){
        return windows.size();
    }
    
    /** 
     * Get the number of Lights in the room
     * @return int
     */
    public int getLightsAmount(){
        return lights.size();
    }


    
    /** 
     * Get a list of the names of the doors
     * @return List<String>
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
     * @return List<String>
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
     * @return List<String>
     */
    public List<String> getWindowsNameList(){
        List<String> windowsListString = new ArrayList<String>();
        for(int i=0; i<windows.size(); ++i){
            windowsListString.add(windows.get(i).getName());
        }
        return windowsListString;
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
     * turn off all lights in room that are set to auto
     */
    public void turnOffAutoLights(){
        for (Light l : lights) {
            if(l.getAuto())
                l.setToOff();
        }
    }
}
