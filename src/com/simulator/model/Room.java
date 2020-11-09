package com.simulator.model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name;
    private List<Door> doors;
    private List<Window> windows;
    private List<Light> lights;
    private MotionSensor motionSensors;

    public Room(String newName){
        this.name = newName;
        this.doors = new ArrayList<>();
        this.windows = new ArrayList<>();
        this.lights = new ArrayList<>();
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

}
