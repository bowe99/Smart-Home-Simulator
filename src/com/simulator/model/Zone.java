package com.simulator.model;

import java.util.ArrayList;
import java.util.List;

public class Zone {
    private List<Room> roomArrayList = new ArrayList<Room>();
    private String zoneName;
    private int temperature;

    public Zone(ArrayList<Room> rl, String zN){
        this.roomArrayList = rl;
        this.zoneName = zN;
    }
    //if temperature has not been overwritten, update the temperature
    public void setZoneTemperature(int temp){
        temperature = temp;
        for(int i =0; i<=roomArrayList.size(); ++i){
            if(!roomArrayList.get(i).getName().contains("Overwritten")){
                roomArrayList.get(i).setTemperature(temp);
            }
        }
    }
    public void setZoneName(String zN){
        this.zoneName = zN;
    }

    public void addNewRoomToZone(Room r){
        roomArrayList.add(r);
        //update temperature for the room
        if(!r.getName().contains("Overwritten")){
            r.setTemperature(temperature);
        }
    }

    public void removeRoomInZone(Room r){
        roomArrayList.remove(r);
    }

    public String getZoneName(){
        return zoneName;
    }



}
