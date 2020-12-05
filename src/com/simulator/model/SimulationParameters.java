package com.simulator.model;

import com.simulator.controller.Logger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * A simulationParameters class using the singleton pattern to create one instance that can be accessed anywhere
 */
public class SimulationParameters{
    private static SimulationParameters instance = null;
    private Time time; //represents minutes of the day (0-1440)
    private double temperature;
    private Profile currentUser;
    private List<Profile> allUsers;
    private boolean simulationStatus = true;

    private final static String simulationFile = "simulation_parameters.txt";
    private final static String usersFile = "users.txt";

    
    /** 
     * Get the singleton instance and if no instance exists, create a new one
     * @return SimulationParameters
     */
    public static SimulationParameters getInstance(){
        try {
            if(instance == null){
                instance = loadFile();
                System.out.println("ok, created a new instance");
            }
            return instance;
        } catch(Exception e){
            System.out.print("something went wrong");
            return null;
        }
    }
    /** 
     * Private constructor that can only be used by this class
     * @return SimulationParameters
     */
    private SimulationParameters(){
        allUsers = new LinkedList<>();
    }

    
    /** 
     * Get the temperature
     * @return int
     */
    public double getTemperature() {
        return temperature;
    }

    
    /** 
     * Set the temperature
     * @param temperature
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    
    /** 
     * Get the date
     * @return Date
     */
    public Date getDate() {
        return time.getDate();
    }

    
    /** 
     * Set the date
     * @param date
     */
    public void setDate(Date date) {
        this.time.setDate(date);
    }

    
    /** 
     * Get the time
     * @return int
     */
    public int getTimeAndUpdate() {
        return time.getTimeAndUpdate();
    }
    
    
    /** 
     * Get the time
     * @return int
     */
    public int getTime() {
        return time.getTime();
    }

    
    /** 
     * Set the time
     * @param time
     */
    public void setTime(int time) {
        this.time.setTime(time);
    }
    
    /** 
     * Set the time object
     * @param time
     */
    public void setTimeObject(Time time){
        this.time = time;
    }  
  
    /**
     *Get the time object
     *
     */
    public Time getTimeObject(){
        return this.time;
    }
    
    /** 
     * Get the current user
     * @return Profile
     */

    public Profile getCurrentUser() {
        return currentUser;
    }

    
    /** 
     * Set the current user
     * @param currentUser
     */
    public void setCurrentUser(Profile currentUser) {
        this.currentUser = currentUser;
    }

    
    /** 
     * Get the simulation status
     * @return boolean
     */
    public boolean getSimulationStatus() {
        return simulationStatus;
    }

    
    /** 
     * Set the simulation status
     * @param simulationStatus
     */
    public void setSimulationStatus(boolean simulationStatus) {
        this.simulationStatus = simulationStatus;
    }

    
    /** 
     * Set the time interval
     * @param speed
     */
    public void setTimeInterval (int speed)
    {
        this.time.changeInterval(speed);
    }

    
    /** 
     * Add a new user to allUsers
     * @param newUser
     */
    public void addUser(Profile newUser){
        allUsers.add(newUser);
    }

    
    /** 
     * Get the user by a name provided
     * @param name
     * @return Profile
     */
    public Profile getUserByName(String name){
        for (Profile p : allUsers) {
            if(p.getName().toLowerCase().equals(name.toLowerCase()))
                return p;
        }
        return null;
    }

    
    /** 
     * Set the current user location
     * @param destination
     */
    public void setCurrentUserLocation(Room destination){
        currentUser.setCurrentRoom(destination);
    }

    
    /** 
     * Set the User Location
     * Check if auto lights need to be turned on
     * @param userName
     * @param destination
     */
    public void setUserLocation(String userName, Room destination){
        for (Profile p : allUsers) {
            if(p.getName().equals(userName)) {
                boolean otherUsersInRoom = false;
                for (Profile otherUser : allUsers) {
                    if(otherUser.getCurrentRoom() == p.getCurrentRoom() && otherUser != p) {
                        otherUsersInRoom = true;
                        break;
                    }
                }
                if(!otherUsersInRoom)
                    p.getCurrentRoom().turnOffAutoLights();
                p.setCurrentRoom(destination);
                p.getCurrentRoom().turnOnAutoLights();
            }
        }
    }

    
    /** 
     * Get all user names in a List<String>
     * @return List<String>
     */
    public List<String> getAllUserNames(){
        List<String> userNames = new ArrayList<>();
        for (Profile p: allUsers) {
            userNames.add(p.getName());
        }
        return userNames;
    }
    
    
    /** 
     * Get all users List
     * @return List<Profile>
     */
    public List<Profile> getAllUsers(){
        return allUsers;
    }

    
    /** 
     * Load simulation paramaters from the simulation_parameters.txt file
     * @return SimulationParameters
     */
    private static SimulationParameters loadFile(){
        SimulationParameters loadedSimulation = new SimulationParameters();
        File file = new File(simulationFile);
        loadedSimulation.setTimeObject(new Time());
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line = reader.readLine();
            SimpleDateFormat initialFormat = new SimpleDateFormat("yyyy-MM-dd");
            loadedSimulation.setDate(initialFormat.parse(line));
            line = reader.readLine();
            loadedSimulation.setTime(Integer.parseInt(line));
            line = reader.readLine();
            loadedSimulation.setTemperature(Double.parseDouble(line));
        }
        catch(Exception e){
            System.out.println("Something went wrong loading the txt file");
            e.printStackTrace();
            return null;
        }

        file = new File(usersFile);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            Profile userToAdd = null;
            String line = reader.readLine();
            while(line != null && line != "")
            {
                String name = line;
                USER_TYPE user_type = USER_TYPE.valueOf(reader.readLine());
                Room room = House.getInstance().getRoomByName(reader.readLine());
                userToAdd = new Profile(name, user_type, room);
                loadedSimulation.addUser(userToAdd);
                reader.readLine();
                line = reader.readLine();
            }
            loadedSimulation.setCurrentUser(userToAdd);
        }
        catch(Exception e){
            System.out.println("Something went wrong loading the txt file");
            e.printStackTrace();
            return null;
        }

        return loadedSimulation;
    }

    /**
     * Print to the simulation file
     */
    public void printToTxtFile(){
        File file = new File(simulationFile);
        try (Writer writer = new FileWriter(file)){
            SimpleDateFormat initialFormat = new SimpleDateFormat("yyyy-MM-dd");
            writer.write(initialFormat.format(time.getDate()));
            writer.write("\n");
            writer.write(String.valueOf(time.getTimeAndUpdate()));
            writer.write("\n");
            writer.write(String.valueOf(temperature));
            Logger.getInstance().outputToConsole("Simulation parameters have been saved");
        }
        catch(Exception e){
            Logger.getInstance().outputToConsole("Something went writing the txt file " + simulationFile);
            e.printStackTrace();
        }

        file = new File(usersFile);
        try (Writer writer = new FileWriter(file)) {
            for (Profile p : allUsers) {
                writer.write(p.getName());
                writer.write("\n");
                writer.write(p.getUserType().name());
                writer.write("\n");
                writer.write(p.getCurrentRoom().getName());
                writer.write("\n");
                writer.write("\n");
            }
            Logger.getInstance().outputToConsole("User info has been saved");
        }
        catch (Exception e){
            Logger.getInstance().outputToConsole("Something went writing the txt file " + usersFile);
            e.printStackTrace();
        }
    }

    /**
     * Update the time
     */
    public void updateTime(){
        time.update();
    }

    /** 
     * Get the time interval
     * @return int
     */
    public int getTimeInterval(){
       return  time.getInterval();
    }
}
