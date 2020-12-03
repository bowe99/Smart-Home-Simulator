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
    public ArrayList<Zone> getZoneList(){ return zoneList; }

    public void removeARoomFromTheirZone(String r){
        for(int j=0; j<zoneList.size(); ++j){
            if (zoneList.get(j).containsRoom(r)){
                zoneList.get(j).removeRoomInZoneByName(r);
            }
        }
    }

    public boolean checkIfValidZoneName(String checkName){
        for(int i =0; i<zoneList.size(); ++i){
            if(zoneList.get(i).getZoneName().equalsIgnoreCase(checkName))
                return false;
        }
        return true;
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
