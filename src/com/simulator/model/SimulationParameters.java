package com.simulator.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SimulationParameters
{
    private static SimulationParameters instance = null;

    private Date date;
    private int time; //represents minutes of the day (0-1440)
    private int temperature;
    private Profile currentUser;
    private List<Profile> allUsers;
    private boolean simulationStatus = true;

    public static SimulationParameters getInstance(){
        try {
            if(instance == null){
                instance = loadFile("simulation_parameters.txt", "users.txt");
                System.out.println("ok, created a new instance");
            }
            return instance;
        } catch(Exception e){
            System.out.print("something went wrong");
            return null;
        }
    }

    private SimulationParameters(){
        allUsers = new LinkedList<>();
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Profile getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Profile currentUser) {
        this.currentUser = currentUser;
    }

    public boolean getSimulationStatus() {
        return simulationStatus;
    }

    public void setSimulationStatus(boolean simulationStatus) {
        this.simulationStatus = simulationStatus;
    }

    public void addUser(Profile newUser){
        allUsers.add(newUser);
    }

    public Profile getUserByName(String name){
        for (Profile p : allUsers) {
            if(p.getName().toLowerCase().equals(name.toLowerCase()))
                return p;
        }
        return null;
    }

    public void setCurrentUserLocation(Room destination){
        currentUser.setCurrentRoom(destination);
    }

    public List<String> getAllUserNames(){
        List<String> userNames = new ArrayList<>();
        for (Profile p: allUsers) {
            userNames.add(p.getName());
        }
        return userNames;
    }

    private static SimulationParameters loadFile(String simulationFile, String usersFile){
        SimulationParameters loadedSimulation = new SimulationParameters();
        File layoutFile = new File(simulationFile);

        try (BufferedReader reader = new BufferedReader(new FileReader(layoutFile))){
            String line = reader.readLine();
            SimpleDateFormat initialFormat = new SimpleDateFormat("yyyy-MM-dd");
            loadedSimulation.setDate(initialFormat.parse(line));
            line = reader.readLine();
            loadedSimulation.setTime(Integer.parseInt(line));
            line = reader.readLine();
            loadedSimulation.setTemperature(Integer.parseInt(line));
        }
        catch(Exception e){
            System.out.println("Something went wrong loading the txt file");
            e.printStackTrace();
            return null;
        }

        layoutFile = new File(usersFile);
        try (BufferedReader reader = new BufferedReader(new FileReader(layoutFile))){
            Profile userToAdd = null;
            String line = reader.readLine();
            while(line != null)
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

    //todo
    public void printToTxtFile(){

    }



}
