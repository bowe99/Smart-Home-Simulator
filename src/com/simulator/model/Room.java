package com.simulator.model;

public class Room {
    private String name;
    private Door[] doors;
    private Window[] windows;
    private Light[] lights;
    private MotionSensor motionSensors;

    public Room(String newName){
        this.name = newName;
        this.doors = new Door[3];//arbitrary maximum, may require dynamically resizable array
        this.windows = new Window[3];//arbitrary maximum, may require dynamically resizable array
        this.lights = new Light[3];//arbitrary maximum, may require dynamically resizable array
    }

    public int addDoor(String newName){
        return 0;
    }

    public int addWindow(String newName){
        return 0;
    }

    public int addLight(String newName){
        return 0;
    }

    public int addMotionSensor(String newName){
        return 0;
    }
    public int addEntrywaySensor(String newName, String targetName){
        return 0;
    }
}
