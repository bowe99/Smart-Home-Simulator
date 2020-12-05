package com.simulator.model;

import java.util.ArrayList;
import java.util.Map;

import com.simulator.controller.Logger;

public class HeatingModule {
    private ArrayList<Zone> zoneList = new ArrayList<Zone>();

    public HeatingModule(){

    }
    public void addZone(Zone zone1){
        zoneList.add(zone1);
    }
    public ArrayList<Zone> getZoneList(){ return zoneList; }

    public void removeARoomFromTheirZone(String room1){
        for(int j=0; j<zoneList.size(); ++j){
            if (zoneList.get(j).containsRoom(room1)){
                zoneList.get(j).removeRoomInZoneByName(room1);
                //if the zone is empty, remove it from the heating module
                if(zoneList.get(j).isEmptyZone()){
                    zoneList.remove(j);
                }
                return;
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

    public ArrayList<String> getZonesRoomsByZoneName(String zoneNameInQuestion){
        for(int i=0; i<zoneList.size(); i++){
            if (zoneNameInQuestion==zoneList.get(i).getZoneName()){
                return zoneList.get(i).getRoomListOfNames();
            }
        }
        return null;
    }

    public void setTempForZone(String zoneName, String periodOfTheDay, int temp){
        for(int i=0; i<zoneList.size(); ++i){
            if(zoneList.get(i).getZoneName().equalsIgnoreCase(zoneName)){
                zoneList.get(i).setZoneTemperature(temp, periodOfTheDay);
                return;
            }
        }
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
