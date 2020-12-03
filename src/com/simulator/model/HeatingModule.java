package com.simulator.model;

import java.util.ArrayList;
import java.util.Map;

import com.simulator.controller.Logger;

public class HeatingModule {
    private ArrayList<Zone> zoneList = new ArrayList<Zone>();

    public HeatingModule(){

    }
    public void addZone(Zone z){
        zoneList.add(z);
    }


    public void displayTemperatureForRoom(String roomName, Profile currentUser){
        if(currentUser.getUserType() == USER_TYPE.STRANGER){
            Logger.getInstance().outputToConsole("[WARNING] Unauthorized action! User does not have the required permissions");
        }
        else{
            Room currentRoom = House.getInstance().getRoomByName(roomName);
            Logger.getInstance().outputToConsole(String.format("Temperature in %s: %d", roomName, currentRoom.getTemperature()));
    
        }    
    }

    public boolean overrideRoomTemperature(String roomName, int temperature, Profile currentUser){
        if(currentUser.getUserType() == USER_TYPE.STRANGER){
            Logger.getInstance().outputToConsole("[WARNING] Unauthorized action! User does not have the required permissions");
            return false;
        }
        else{
            Room currentRoom = House.getInstance().getRoomByName(roomName);
            currentRoom.setTemperature(temperature);
            Logger.getInstance().outputToConsole(String.format("Temperature in %s: %d", roomName, currentRoom.getTemperature()));
            return true;
        }  
    }
}
