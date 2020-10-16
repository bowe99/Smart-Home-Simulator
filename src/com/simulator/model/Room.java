package src.com.simulator.model;

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
    public int getDoorsAmount(){
        return doors.size();
    }
    public int getWindowsAmount(){
        return windows.size();
    }
    public int getLightsAmount(){
        return lights.size();
    }

}
