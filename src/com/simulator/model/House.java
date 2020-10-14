package com.simulator.model;

public class House {
    public String address;
    private Room[] rooms;

    public House(String newAddress)
    {
        this.address = newAddress;
        this.rooms = new Room[10];//arbitrary maximum, may require dynamically resizable array
    }

    public int addRoom(String newName){
        return 0;
    }

    public int addDoor(String newName, String targetName){
        return 0;
    }

    public int addWindow(String newName, String targetName){
        return 0;
    }

    public int addLight(String newName, String targetName){
        return 0;
    }

    public int addMotionSensor(String name, String targetName){
        return 0;
    }

    public int addEntrywaySensor(String name, String roomName, String targetName){
        return 0;
    }
}
