package com.simulator.model;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SimulationParameters
{
    private static SimulationParameters instance = null;
    private Time time; //represents minutes of the day (0-1440)
    private int temperature;
    private Profile currentUser;
    private List<Profile> allUsers;
    private boolean simulationStatus = true;

    private final static String simulationFile = "simulation_parameters.txt";
    private final static String usersFile = "users.txt";

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
        return time.getDate();
    }

    public void setDate(Date date) {
        this.time.setDate(date);
    }

    public int getTime() {
        return time.getTime();
    }

    public void setTime(int time) {
        this.time.setTime(time);
    }
    public void setTimeObject(Time time){
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

    public void setUserLocation(String userName, Room destination){
        for (Profile p : allUsers) {
            if(p.getName().equals(userName))
                p.setCurrentRoom(destination);
        }
    }

    public List<String> getAllUserNames(){
        List<String> userNames = new ArrayList<>();
        for (Profile p: allUsers) {
            userNames.add(p.getName());
        }
        return userNames;
    }
    
    public List<Profile> getAllUsers(){
        return allUsers;
    }

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
            loadedSimulation.setTemperature(Integer.parseInt(line));
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

    public void printToTxtFile(){
        File file = new File(simulationFile);
        try (Writer writer = new FileWriter(file)){
            SimpleDateFormat initialFormat = new SimpleDateFormat("yyyy-MM-dd");
            writer.write(initialFormat.format(time.getDate()));
            writer.write("\n");
            writer.write(String.valueOf(time.getTime()));
            writer.write("\n");
            writer.write(String.valueOf(temperature));
        }
        catch(Exception e){
            System.out.println("Something went writing the txt file " + simulationFile);
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
        }
        catch (Exception e){
            System.out.println("Something went writing the txt file " + usersFile);
            e.printStackTrace();
        }
    }

    public void updateTime(){
        time.update();
    }

    public int getTimeInterval(){
       return  time.getInterval();
    }
}
