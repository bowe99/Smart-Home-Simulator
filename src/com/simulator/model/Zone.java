package com.simulator.model;

import com.simulator.controller.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Zone.
 */
public class Zone {
    private List<Room> roomArrayList = new ArrayList<Room>();
    private String zoneName;
    private int temperatureMorning;
    private int temperatureDay;
    private int temperatureNight;
    private boolean emptyZone;

    /**
     * Instantiates a new Zone.
     *
     * @param roomList  the room list
     * @param zoneName1 the zone name 1
     */
    public Zone(ArrayList<Room> roomList, String zoneName1){
        this.roomArrayList = roomList;
        this.zoneName = zoneName1;
    }

    /**
     * Set zone temperature.
     * if temperature has not been overwritten, update the temperature for the morning, daytime and nighttime
     *
     * @param temp       the temp
     * @param timePeriod the time period
     */
    public void setZoneTemperature(int temp, String timePeriod){
        if(timePeriod.contains("Morning")){
            //Set for the zone, then set for each of the rooms if they have not already been overwritten
            temperatureMorning = temp;
            for(int i = 0; i < roomArrayList.size(); ++i){
                if(!roomArrayList.get(i).getName().contains("Overwritten")){
                    roomArrayList.get(i).getTemperature().setTemperatureMorning(temp);
                }
                else {
                    roomArrayList.get(i).setOverridden(false);
                    roomArrayList.get(i).getTemperature().setTemperatureMorning(temp);
                    Logger.getInstance().outputToConsole("Room "+roomArrayList.get(i).getName()+" was overwritten so it's morning temperature will be updated to the zone temperature.");
                }

            }
        }
        else if(timePeriod.contains("Day")){
            //Set for the zone, then set for each of the rooms if they have not already been overwritten
            temperatureDay = temp;
            for(int i = 0; i < roomArrayList.size(); ++i){
                if(!roomArrayList.get(i).getName().contains("Overwritten")){
                    roomArrayList.get(i).getTemperature().setTemperatureDay(temp);
                }
                else {
                    roomArrayList.get(i).setOverridden(false);
                    roomArrayList.get(i).getTemperature().setTemperatureDay(temp);
                    Logger.getInstance().outputToConsole("Room "+roomArrayList.get(i).getName()+" was overwritten so it's daytime temperature will be updated to the zone temperature.");
                }
            }
        }
        else if(timePeriod.contains("Night")){
            //Set for the zone, then set for each of the rooms if they have not already been overwritten
            temperatureNight = temp;
            for(int i = 0; i < roomArrayList.size(); ++i){
                if(!roomArrayList.get(i).getName().contains("Overwritten")){
                    roomArrayList.get(i).getTemperature().setTemperatureNight(temp);
                }
                else {
                    roomArrayList.get(i).setOverridden(false);
                    roomArrayList.get(i).getTemperature().setTemperatureNight(temp);
                    Logger.getInstance().outputToConsole("Room "+roomArrayList.get(i).getName()+" was overwritten so it's nighttime temperature will be updated to the zone temperature.");
                }
            }
        }
        Logger.getInstance().outputToConsole("The updated temperature values for Zone "+this.zoneName+" are now: \nMorning Temperature: "+getMorningTemperature()+"\nDay Temperature: "+getDayTemperature()+"\nNight Temperature: "+getNightTemperature());
    }

    /**
     * Get morning temperature int.
     *
     * @return the int
     */
    public int getMorningTemperature(){return temperatureMorning;}

    /**
     * Get day temperature int.
     *
     * @return the int
     */
    public int getDayTemperature(){return temperatureDay;}

    /**
     * Get night temperature int.
     *
     * @return the int
     */
    public int getNightTemperature(){return temperatureNight;}

    /**
     * Get room list of names array list.
     *
     * @return the array list
     */
    public ArrayList<String> getRoomListOfNames(){
        ArrayList<String> roomListOfNames = new ArrayList<String>();
        for(int i=0; i<roomArrayList.size(); ++i){
            roomListOfNames.add(roomArrayList.get(i).getName());
        }
        return roomListOfNames;
    }


    /**
     * Is empty zone boolean.
     *
     * @return the boolean
     */
    public boolean isEmptyZone(){
        if(roomArrayList.size() == 0){
            emptyZone=true;
        }
        else emptyZone = false;

        return emptyZone;
    }

    /**
     * Set zone name.
     *
     * @param zoneName1 the zone name 1
     */
    public void setZoneName(String zoneName1){
        this.zoneName = zoneName1;
    }

    /**
     * Contains room boolean.
     *
     * @param room1 the room 1
     * @return the boolean
     */
    public boolean containsRoom(String room1){
        for(int k = 0; k < roomArrayList.size(); ++k){
            if(roomArrayList.get(k).getName().contains(room1))
                return true;
        }
        return false;
    }

    /**
     * Add new room to zone.
     *
     * @param room1 the room 1
     */
    public void addNewRoomToZone(Room room1){
        roomArrayList.add(room1);
        //update temperature for the room
        if(!room1.getName().contains("Overwritten")){
            room1.getTemperature().setTemperatureMorning(temperatureMorning);
            room1.getTemperature().setTemperatureDay(temperatureDay);
            room1.getTemperature().setTemperatureNight(temperatureNight);
        }
        room1.setOverridden(false);
    }

    /**
     * Print rooms in zone.
     */
    public void printRoomsInZone(){
        System.out.println(zoneName);
        for(int i = 0; i < roomArrayList.size(); ++i){
            System.out.println(roomArrayList.get(i).getName());
        }
    }

    /**
     * Remove room in zone.
     *
     * @param r the r
     */
    public void removeRoomInZone(Room r){
        roomArrayList.remove(r);
    }

    /**
     * Remove room in zone by name.
     *
     * @param room1 the room 1
     */
    public void removeRoomInZoneByName(String room1){
        for(int j = 0; j < roomArrayList.size(); ++j){
            if(roomArrayList.get(j).getName().equalsIgnoreCase(room1)){
                roomArrayList.remove(roomArrayList.get(j));
            }
        }
    }

    /**
     * Get zone name string.
     *
     * @return the string
     */
    public String getZoneName(){
        return zoneName;
    }
}
