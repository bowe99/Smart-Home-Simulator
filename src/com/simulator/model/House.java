package com.simulator.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the house within the simulation, uses Singleton pattern (creational pattern)
 */
public class House {
    private static House instance = null;
    private House(){}
    private String address;
    private List<Room> rooms;

    /**
     * Get instance of the house object that is saved in this singleton class, creates new instance if one does not exist
     *
     * @return House instance
     */
    public static House getInstance() throws HouseLoadException{
        if(instance == null){
            instance = loadFile("house_layout_txt.txt");
            System.out.println("ok, created a new instance");
        }
        return instance;
    }

    /**
     * Constructor for a new house object
     * @param newAddress the address of which the house is associated
     */
    private House(String newAddress) {
        this.address = newAddress;
        this.rooms = new ArrayList<>();
    }

    /**
     * Adds a room to the house
     *
     * @param newName the new uniquely identifying name for the room
     * @param ID      the id
     * @return an integer representing the success or failure of the request a return value of 0 represents success a return value of 1 represents failure
     */
    public int addRoom(String newName, String ID) {
        this.rooms.add(new Room(newName, ID));
        return 0;
    }

    /**
     * Rename room.
     *
     * @param roomToBeRenamedString the room to be renamed string
     * @param newNameString         the new name string
     */
    public void renameRoom(String roomToBeRenamedString, String newNameString){
        getRoomByName(roomToBeRenamedString).setName(newNameString);
    }

    /**
     * Gets rooms.
     *
     * @return the rooms
     */
    public List<Room> getRooms()
    {
        return this.rooms;
    }

    /**
     * Adds a door to a room in the house
     *
     * @param newName    the new uniquely identifying name for the door
     * @param targetName the name of the room the door is a part of
     * @return an integer representing the success or failure of the request a return value of 0 represents success a return value of 1 represents failure
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
     *
     * @param newName    the new uniquely identifying name for the window
     * @param targetName the name of the room the window is a part of
     * @return an integer representing the success or failure of the request a return value of 0 represents success a return value of 1 represents failure
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
     *
     * @param newName    the new uniquely identifying name for the light
     * @param targetName the name of the room the light is a part of
     * @return an integer representing the success or failure of the request a return value of 0 represents success a return value of 1 represents failure
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
     * Get the address of the house
     *
     * @return the address of the house
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the address of the house
     *
     * @param address the new address of the house
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Find room object within house with a given name
     *
     * @param targetName the name of the room
     * @return object of type Room with the given name returns null if there is no room matching the requested name
     */
    public Room getRoomByName(String targetName) {
        for (Room r : rooms) {
            if (r.getName().contains(targetName)) {
                return r;
            }
        }
        return null;
    }

    /**
     * Get the Light instance that is referred to from the string that is passed in
     *
     * @param target the target
     * @return Light light
     */
    public Light getLightByName(String target){
        for (Room r : this.rooms) {
            Light targetLight = r.getLightByName(target);
            if(targetLight != null){
                return targetLight;
            }
        }
        return null;
    }

    /**
     * return a list of all the names of the rooms
     *
     * @return List<String> array list
     */
    public ArrayList<String> getRoomsNameList(){
        ArrayList<String> roomsListString = new ArrayList<String>();
        for(int i=0; i<rooms.size(); ++i){
            roomsListString.add(rooms.get(i).getName());
        }
        return roomsListString;
    }

    /**
     * return a list of the names of all of the lights
     *
     * @return ArrayList<String> array list
     */
    public ArrayList<String> getLightsNameList(){
        ArrayList<String> lightsListString = new ArrayList<String>();
        for(Room room : this.rooms){
            lightsListString.addAll(room.getLightsNameList());
        }

        return lightsListString;
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
            System.out.println("RoomID"+i+": \n\tName: "+rooms.get(i).getName());
            System.out.println("\tDoors: "+rooms.get(i).getDoorsAmount()+" doors, ");
            System.out.println("\tWindows: "+rooms.get(i).getWindowsAmount()+" windows, ");
            System.out.println("\tLights: "+rooms.get(i).getLightsAmount()+" lights, ");
        }
        return "";
    }

    /** 
     * Reads the house_layout_txt.txt file containing the specifications of the house layout and converts it to objects
     * @param fileName
     * @return House
     * @throws HouseLoadException
     */
    private static House loadFile(String fileName) throws HouseLoadException{
        House loadedHouse;
        String[] elementStack = new String[5];
        int depth = -1;
        String attribute = "";
        String attributeID = "";
        File layoutFile = new File(fileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(layoutFile))){
            int linenumber = 1;
            String line = reader.readLine();
            //remove whitespace
            line = line.trim();

            //first line should be a house and address
            if(line.toLowerCase().contains("$house")){
                attribute = getAttribute(line);
                loadedHouse = new House(attribute);
                elementStack[++depth] = attribute;
            }
            else{
                System.out.println("Unable to find address field for house at line: " + linenumber);
                return null;
            }
            line = reader.readLine();

            //To scan through the lines of information
            while(line !=null){
                ++linenumber;

                //remove whitespace
                line = line.trim();
                String lowerCaseLine = line.toLowerCase();

                if (lowerCaseLine.charAt(0) == '}') {
                    depth--;
                }
                else if (lowerCaseLine.contains("$room")) {
                    attribute = getAttribute(line);
                    elementStack[++depth] = attribute;
                    line = reader.readLine();
                    line = line.trim();
                    attributeID = getAttribute(line);
                    loadedHouse.addRoom(attribute, attributeID);
                    elementStack[++depth] = attributeID;
                }
                else if (lowerCaseLine.contains("$window")) {
                    attribute = getAttribute(line);
                    loadedHouse.addWindow(attribute, elementStack[depth]);
                    elementStack[++depth] = attribute;
                }
                else if (lowerCaseLine.contains("$door")) {
                    attribute = getAttribute(line);
                    loadedHouse.addDoor(attribute, elementStack[depth]);
                    elementStack[++depth] = attribute;
                }
                else if (lowerCaseLine.contains("$light")) {
                    attribute = getAttribute(line);
                    loadedHouse.addLight(attribute, elementStack[depth]);
                    elementStack[++depth] = attribute;
                }
                else{
                    throw new HouseLoadException("Unexpected identifier on line " + linenumber + " of the house layout file. ");
                }

                //moving onto the next line of information
                line = reader.readLine();
            }
            reader.close();
            return loadedHouse;
        }
        catch(IOException e){
            System.out.println("Something went wrong loading the txt file");
            e.printStackTrace();
            return null;
        }
    }

    /** 
     * get the attribute
     * @param line
     * @return String
     */
    private static String getAttribute(String line){
        String attribute = "";
        attribute = line.substring(line.indexOf("\"") + 1);
        return attribute.substring(0, attribute.indexOf("\""));
    }
    
}
