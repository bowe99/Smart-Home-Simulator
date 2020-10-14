package com.simulator.model;

import java.util.ArrayList;
import java.util.List;

public class House {
    private String address;
    private List<Room> rooms;

    public House(String newAddress) {
        this.address = newAddress;
        this.rooms = new ArrayList<>();
    }

    public int addRoom(String newName) {
        this.rooms.add(new Room(newName));
        return 0;
    }

    public int addDoor(String newName, String targetName) {
        Room targetRoom = getRoomByName(targetName);
        if (targetRoom != null) {
            targetRoom.addDoor(newName);
            return 0;
        }
        return 1;
    }

    public int addWindow(String newName, String targetName) {
        Room targetRoom = getRoomByName(targetName);
        if (targetRoom != null) {
            targetRoom.addWindow(newName);
            return 0;
        }
        return 1;
    }

    public int addLight(String newName, String targetName) {
        Room targetRoom = getRoomByName(targetName);
        if (targetRoom != null) {
            targetRoom.addLight(newName);
            return 0;
        }
        return 1;
    }

    public int addMotionSensor(String newName, String targetName) {
        Room targetRoom = getRoomByName(targetName);
        if (targetRoom != null) {
            targetRoom.addMotionSensor(newName);
            return 0;
        }
        return 1;
    }

    public int addEntrywaySensor(String newName, String roomName, String targetName) {
        Room targetRoom = getRoomByName(roomName);
        if (targetRoom != null) {
            targetRoom.addEntrywaySensor(newName, targetName);
            return 0;
        }
        return 1;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private Room getRoomByName(String targetName) {
        for (Room r : rooms) {
            if (r.getName().equals(targetName)) {
                return r;
            }
        }
        return null;
    }
}
