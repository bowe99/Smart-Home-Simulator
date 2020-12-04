package com.simulator.model;

import com.simulator.controller.Logger;

import java.util.ArrayList;
import java.util.List;

public class Zone {
    private List<Room> roomArrayList = new ArrayList<Room>();
    private String zoneName;
    private int temperatureMorning;
    private int temperatureDay;
    private int temperatureNight;
    private boolean emptyZone;

    public Zone(ArrayList<Room> roomList, String zoneName1){
        this.roomArrayList = roomList;
        this.zoneName = zoneName1;
    }
    //if temperature has not been overwritten, update the temperature for the morning, daytime and nighttime
    public void setZoneTemperature(int temp, String timePeriod){
        if(timePeriod.contains("Morning")){
            //Set for the zone, then set for each of the rooms if they have not already been overwritten
            temperatureMorning = temp;
            for(int i = 0; i < roomArrayList.size(); ++i){
                if(!roomArrayList.get(i).getName().contains("Overwritten")){
                    roomArrayList.get(i).setTemperatureMorning(temp);
                }
                else Logger.getInstance().outputToConsole("Room "+roomArrayList.get(i).getName()+"was overwritten \nso it's morning temperature cannot be updated via setting the zone temperature.");
            }
        }
        else if(timePeriod.contains("Day")){
            //Set for the zone, then set for each of the rooms if they have not already been overwritten
            temperatureDay = temp;
            for(int i = 0; i < roomArrayList.size(); ++i){
                if(!roomArrayList.get(i).getName().contains("Overwritten")){
                    roomArrayList.get(i).setTemperatureDay(temp);
                }
                else Logger.getInstance().outputToConsole("Room "+roomArrayList.get(i).getName()+"was overwritten \nso it's daytime temperature cannot be updated via setting the zone temperature.");
            }
        }
        else if(timePeriod.contains("Night")){
            //Set for the zone, then set for each of the rooms if they have not already been overwritten
            temperatureNight = temp;
            for(int i = 0; i < roomArrayList.size(); ++i){
                if(!roomArrayList.get(i).getName().contains("Overwritten")){
                    roomArrayList.get(i).setTemperatureNight(temp);
                }
                else Logger.getInstance().outputToConsole("Room "+roomArrayList.get(i).getName()+"was overwritten \nso it's nighttime temperature cannot be updated via setting the zone temperature.");
            }
        }
        Logger.getInstance().outputToConsole("The updated temperature values for Zone "+this.zoneName+" are now: \nMorning Temperature: "+getMorningTemperature()+"\nDay Temperature: "+getDayTemperature()+"\nNight Temperature: "+getNightTemperature());
    }

    public int getMorningTemperature(){return temperatureMorning;}
    public int getDayTemperature(){return temperatureDay;}
    public int getNightTemperature(){return temperatureNight;}


    public boolean isEmptyZone(){
        if(roomArrayList.size() == 0){
            emptyZone=true;
        }
        else emptyZone = false;

        return emptyZone;
    }

    public void setZoneName(String zoneName1){
        this.zoneName = zoneName1;
    }

    public boolean containsRoom(String room1){
        for(int k = 0; k < roomArrayList.size(); ++k){
            if(roomArrayList.get(k).getName().contains(room1))
                return true;
        }
        return false;
    }

    public void addNewRoomToZone(Room room1){
        roomArrayList.add(room1);
        //update temperature for the room
        if(!room1.getName().contains("Overwritten")){
            room1.setTemperatureMorning(temperatureMorning);
            room1.setTemperatureDay(temperatureDay);
            room1.setTemperatureNight(temperatureNight);
        }
    }

    public void printRoomsInZone(){
        System.out.println(zoneName);
        for(int i = 0; i < roomArrayList.size(); ++i){
            System.out.println(roomArrayList.get(i).getName());
        }
    }
    public void removeRoomInZone(Room r){
        roomArrayList.remove(r);
    }

    public void removeRoomInZoneByName(String room1){
        for(int j = 0; j < roomArrayList.size(); ++j){
            if(roomArrayList.get(j).getName().equalsIgnoreCase(room1)){
                roomArrayList.remove(roomArrayList.get(j));
            }
        }
    }

    public String getZoneName(){
        return zoneName;
    }



}
