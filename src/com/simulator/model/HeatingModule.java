package com.simulator.model;

import java.util.Map;

import com.simulator.controller.Logger;

public class HeatingModule {

    public HeatingModule(){

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
