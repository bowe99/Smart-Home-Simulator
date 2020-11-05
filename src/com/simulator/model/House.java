package com.simulator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the house within the simulation
 */
public class House {
    private static volatile House instance = null;
    private House(){}
    private String address;
    private List<Room> rooms;

    public static House getInstance() throws Exception {
        if(instance == null){
            instance = HouseLayoutParser.loadFile("house_layout_txt.txt");
            System.out.println("ok, created a new instance");
        }
        return instance;
     }

    /**
     * Constructor for a new house object
     * @param newAddress the address of which the house is associated
     */
    protected House(String newAddress) {
        this.address = newAddress;
        this.rooms = new ArrayList<>();
    }

    /**
     * Adds a room to the house
     * @param newName the new uniquely identifying name for the room
     * @return an integer representing the success or failure of the request
     * a return value of 0 represents success
     * a return value of 1 represents failure
     */
    public int addRoom(String newName) {
        this.rooms.add(new Room(newName));
        return 0;
    }

    /**
     * Adds a door to a room in the house
     * @param newName the new uniquely identifying name for the door
     * @param targetName the name of the room the door is a part of
     * @return an integer representing the success or failure of the request
     * a return value of 0 represents success
     * a return value of 1 represents failure
     */
    public int addDoor(String newName, String targetName) {
        Room targetRoom = getRoomByName(targetName);
        if (targetRoom != null) {
            targetRoom.addDoor(newName);
            return 0;
        }
        return 1;
    }

    /**
     * Adds a window to a room in the house
     * @param newName the new uniquely identifying name for the window
     * @param targetName the name of the room the window is a part of
     * @return an integer representing the success or failure of the request
     * a return value of 0 represents success
     * a return value of 1 represents failure
     */
    public int addWindow(String newName, String targetName) {
        Room targetRoom = getRoomByName(targetName);
        if (targetRoom != null) {
            targetRoom.addWindow(newName);
            return 0;
        }
        return 1;
    }

    /**
     * Adds a light to a room in the house
     * @param newName the new uniquely identifying name for the light
     * @param targetName the name of the room the light is a part of
     * @return an integer representing the success or failure of the request
     * a return value of 0 represents success
     * a return value of 1 represents failure
     */
    public int addLight(String newName, String targetName) {
        Room targetRoom = getRoomByName(targetName);
        if (targetRoom != null) {
            targetRoom.addLight(newName);
            return 0;
        }
        return 1;
    }

    /**
     * Adds a motion sensor to a room in the house
     * @param newName the new uniquely identifying name for the motion sensor
     * @param targetName the name of the room the motion sensor is a part of
     * @return an integer representing the success or failure of the request
     * a return value of 0 represents success
     * a return value of 1 represents failure
     */
    public int addMotionSensor(String newName, String targetName) {
        Room targetRoom = getRoomByName(targetName);
        if (targetRoom != null) {
            targetRoom.addMotionSensor(newName);
            return 0;
        }
        return 1;
    }

    /**
     * Adds an entryway sensor to an entryway of the house
     * @param newName the new uniquely identifying name for the motion sensor
     * @param roomName the name of the room the entryway is a part of
     * @param targetName the name of the entry way the sensor is to be placed on
     * @return an integer representing the success or failure of the request
     * a return value of 0 represents success
     * a return value of 1 represents failure
     */
    public int addEntrywaySensor(String newName, String roomName, String targetName) {
        Room targetRoom = getRoomByName(roomName);
        if (targetRoom != null) {
            targetRoom.addEntrywaySensor(newName, targetName);
            return 0;
        }
        return 1;
    }

    /**
     * Get the address of the house
     * @return the address of the house
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the address of the house
     * @param address the new address of the house
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Find room object within house with a given name
     * @param targetName the name of the room
     * @return object of type Room with the given name
     * returns null if there is no room matching the requested name
     */
    public Room getRoomByName(String targetName) {
        for (Room r : rooms) {
            if (r.getName().equals(targetName)) {
                return r;
            }
        }
        return null;
    }

    /**
     * Print out information detailing the House layout file
     * @return blank string to satisfy toString() requirements
     */
    public String toString(){
        System.out.println("This house's address is at "+address+".");
        System.out.println("This house has "+rooms.size()+" rooms:");
        for(int i=0; rooms.size()>i; ++i){
            System.out.println("Room "+i+": \n\tName: "+rooms.get(i).getName());
            System.out.println("\tDoors: "+rooms.get(i).getDoorsAmount()+" doors, ");
            System.out.println("\tWindows: "+rooms.get(i).getWindowsAmount()+" windows, ");
            System.out.println("\tLights: "+rooms.get(i).getLightsAmount()+" lights, ");
        }
        return "";
    }
    
}
