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

    public int addDoor(String newName){
        this.doors.add(new Door(newName));
        return 0;
    }

    public int addWindow(String newName){
        this.windows.add(new Window(newName));
        return 0;
    }

    public int addLight(String newName){
        this.lights.add(new Light(newName));
        return 0;
    }

    public int addMotionSensor(String newName){
        this.motionSensors = new MotionSensor(newName);
        return 0;
    }
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

    public String getName() {
        return name;
    }

    public Door getDoorByName(String targetName) {
        for (Door d : doors) {
            if (d.getName().equals(targetName)) {
                return d;
            }
        }
        return null;
    }

    public Light getLightByName(String targetName) {
        for (Light l : lights) {
            if (l.getName().equals(targetName)) {
                return l;
            }
        }
        return null;
    }

    public Window getWindowByName(String targetName) {
        for (Window w : windows) {
            if (w.getName().equals(targetName)) {
                return w;
            }
        }
        return null;
    }


    public int getDoorsAmount(){
        return doors.size();
    }
    public int getWindowsAmount(){
        return windows.size();
    }
    public int getLightsAmount(){
        return lights.size();
    }

    public List<Door> getDoorsList(){
        return doors;
    }
    public List<Light> getLightsList(){
        return lights;
    }
    public List<Window> getWindowsList(){
        return windows;
    }

    //get a the list of names of doors
    public List<String> getDoorsListString(){
        List<String> doorsListString = new ArrayList<String>();
        for(int i=0; i<doors.size(); ++i){
            doorsListString.add(doors.get(i).getName());
        }
        return doorsListString;
    }
    //get a the list of names of lights
    public List<String> getLightsListString(){
        List<String> lightsListString = new ArrayList<String>();
        for(int i=0; i<lights.size(); ++i){
            lightsListString.add(lights.get(i).getName());
        }
        return lightsListString;
    }    
    //get a the list of names of windows
    public List<String> getWindowsListString(){
        List<String> windowsListString = new ArrayList<String>();
        for(int i=0; i<windows.size(); ++i){
            windowsListString.add(windows.get(i).getName());
        }
        return windowsListString;
    }

}
