package com.simulator.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import com.simulator.controller.Logger;

public class HeatingModule extends SimulationObserver{
    private ArrayList<Zone> zoneList = new ArrayList<Zone>();
    private List<Room> roomList = House.getInstance().getRooms();

    private double summerTemperatureAwayMode;
    private double winterTemperatureAwayMode;

    public HeatingModule(Time time){
        time.attach(this);
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
            Logger.getInstance().outputToConsole(String.format("Temperature in %s: %f", roomName, currentRoom.getTemperature().getCurrentTemperature()));
    
        }    
    }

    public boolean overrideRoomTemperature(String roomName, double temperature, Profile currentUser){
        if(currentUser.getUserType() == USER_TYPE.STRANGER){
            Logger.getInstance().outputToConsole("[WARNING] Unauthorized action! User does not have the required permissions");
            return false;
        }
        else{
            Room currentRoom = House.getInstance().getRoomByName(roomName);
            currentRoom.getTemperature().setTemperatureOverridden(temperature);
            currentRoom.setOverridden(true);
            Logger.getInstance().outputToConsole(String.format("Temperature in %s is set to be at: %f currentTemperature: %f", roomName, temperature, currentRoom.getTemperature().getCurrentTemperature()));
            return true;
        }  
    }

    @Override
    public void updateLocation(Profile profile) {
        return;
    }

    public void updateRoomTargetTemperature(Room room, double currentTempRoom, int currentHour){
        if(room.getOverridden()){
            room.getTemperature().setTemperatureTarget(room.getTemperature().getTemperatureOverridden());
        }
        else{
            if(currentHour >=0 && currentHour < 8){
                room.getTemperature().setTemperatureTarget(room.getTemperature().getTemperatureMorning());
            }
            else if(currentHour >= 8 && currentHour < 16){
                room.getTemperature().setTemperatureTarget(room.getTemperature().getTemperatureDay());
            }
            else if(currentHour >= 16 && currentHour < 24){
                room.getTemperature().setTemperatureTarget(room.getTemperature().getTemperatureNight());
            }
        }
    }

    public void checkToggleStatusHVAC(Room room, double currentTempRoom){
        double targetTemperature = room.getTemperature().getTemperatureTarget();

        if (currentTempRoom > targetTemperature + 0.25 || currentTempRoom < targetTemperature - 0.25){
            room.setCurrentStateHVAC(true);
        }
        // Account for posibility of target.#5
        else if(currentTempRoom >= targetTemperature - 0.05 && currentTempRoom <= targetTemperature + 0.05){
            room.setCurrentStateHVAC(false);
        }
    }

    public void checkSummerCooling(Room room, int currentMonth, double outdoorTemperature, double currentTempRoom){
        // TODO check if in away mode before opening windows
        if((currentMonth <= 9 && currentMonth >= 6) && currentTempRoom > outdoorTemperature + 0.25){
            if(room.getCurrentStateHVAC()){
                Logger.getInstance().outputToConsole("Disabling Air Conditioning: It is summer and cooler outdoors. Open all windows");
            }
            room.setCurrentStateHVAC(false);
            room.openAllWindows();
        }
    }

    public int getCurrentHour(Date date){
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.HOUR_OF_DAY);
    }


    @Override
    public void updateTime(int time) {
        SimulationParameters simulationInstance = SimulationParameters.getInstance();
        int currentMonth = simulationInstance.getDate().getMonth();
        int currentHour = simulationInstance.getTime() / 3600;
        double outdoorTemperature = simulationInstance.getTemperature();
        for(Room room : roomList){
            double currentTempRoom = room.getTemperature().getCurrentTemperature();
            updateRoomTargetTemperature(room, currentTempRoom, currentHour);
            checkToggleStatusHVAC(room, currentTempRoom);
            checkSummerCooling(room, currentMonth, outdoorTemperature, currentTempRoom);

            room.updateTemperature(outdoorTemperature);

        }

    }
}
