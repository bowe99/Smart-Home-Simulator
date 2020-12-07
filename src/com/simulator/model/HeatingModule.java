package com.simulator.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import com.simulator.controller.Logger;

/**
 * The type Heating module.
 */
public class HeatingModule extends SimulationObserver{
    private ArrayList<Zone> zoneList = new ArrayList<Zone>();
    private SecurityModule securityModule;
    private List<Room> roomList;
    private House house;

    private double summerTemperatureAwayMode;
    private double winterTemperatureAwayMode;
    private int startSummer = 0;
    private int endSummer = 0;
    private int startWinter = 0;
    private int endWinter =0;

    /**
     * Heating Module Constructor
     */
    public HeatingModule(){
        try {
            house = House.getInstance();
            roomList = house.getRooms();
        }
        catch (HouseLoadException houseLoadException){
            houseLoadException.printStackTrace();
            System.out.println(houseLoadException.getMessage());
        }
    }

    /**
     * Instantiates a new Heating module.
     *
     * @param time           the time
     * @param securityModule the security module
     */
    public HeatingModule(Time time, SecurityModule securityModule){
        this();
        time.attach(this);
        this.securityModule = securityModule;
    }

    /**
     * Add zone.
     *
     * @param zone1 the zone 1
     */
    public void addZone(Zone zone1){
        zoneList.add(zone1);
    }

    /**
     * Get zone list array list.
     *
     * @return the array list
     */
    public ArrayList<Zone> getZoneList(){ return zoneList; }

    /**
     * Remove a room from their zone.
     *
     * @param room1 the room 1
     */
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

    /**
     * Check if valid zone name boolean.
     *
     * @param checkName the check name
     * @return the boolean
     */
    public boolean checkIfValidZoneName(String checkName){
        for(int i =0; i<zoneList.size(); ++i){
            if(zoneList.get(i).getZoneName().equalsIgnoreCase(checkName))
                return false;
        }
        return true;
    }

    /**
     * Get zones rooms by zone name array list.
     *
     * @param zoneNameInQuestion the zone name in question
     * @return the array list
     */
    public ArrayList<String> getZonesRoomsByZoneName(String zoneNameInQuestion){
        for(int i=0; i<zoneList.size(); i++){
            if (zoneNameInQuestion==zoneList.get(i).getZoneName()){
                return zoneList.get(i).getRoomListOfNames();
            }
        }
        return null;
    }

    /**
     * Set temp for zone.
     *
     * @param zoneName       the zone name
     * @param periodOfTheDay the period of the day
     * @param temp           the temp
     */
    public void setTempForZone(String zoneName, String periodOfTheDay, int temp){
        for(int i=0; i<zoneList.size(); ++i){
            if(zoneList.get(i).getZoneName().equalsIgnoreCase(zoneName)){
                zoneList.get(i).setZoneTemperature(temp, periodOfTheDay);
                return;
            }
        }
    }

    /**
     * Display temperature for room.
     *
     * @param roomName    the room name
     * @param currentUser the current user
     */
    public void displayTemperatureForRoom(String roomName, Profile currentUser){
        if(currentUser.getUserType() == USER_TYPE.STRANGER){
            Logger.getInstance().outputToConsole("[WARNING] Unauthorized action! User does not have the required permissions");
        }
        else{
            Room currentRoom = house.getRoomByName(roomName);
            Logger.getInstance().outputToConsole(String.format("Temperature in %s: %f", roomName, currentRoom.getTemperature().getCurrentTemperature()));
        }    
    }

    /**
     * Override room temperature boolean.
     *
     * @param roomName    the room name
     * @param temperature the temperature
     * @param currentUser the current user
     * @return the boolean
     */
    public boolean overrideRoomTemperature(String roomName, double temperature, Profile currentUser){
        if(currentUser.getUserType() == USER_TYPE.STRANGER || currentUser.getUserType() == USER_TYPE.GUEST || currentUser.getUserType() == USER_TYPE.CHILD){
            Logger.getInstance().outputToConsole("[WARNING] Unauthorized action! User does not have the required permissions");
            return false;
        }
        else{
            Room currentRoom = house.getRoomByName(roomName);
            currentRoom.getTemperature().setTemperatureOverridden(temperature);
            currentRoom.setOverridden(true);
            Logger.getInstance().outputToConsole(String.format("Temperature in %s is set to be at: %f currentTemperature: %f", roomName, temperature, currentRoom.getTemperature().getCurrentTemperature()));
            return true;
        }  
    }

    @Override
    public void updateLocation(Profile profile) { }

    public boolean checkMonths(int start, int end, int currentMonth)
    {
        if(start>end)
        {
            return currentMonth >=start || currentMonth <=end;
        }
        else if(start < end)
        {
            return currentMonth >= start && currentMonth <= end;
        }
        else{
            return currentMonth == start;
        }
    }

    /**
     * Update room target temperature.
     *
     * @param room            the room
     * @param currentTempRoom the current temp room
     * @param currentHour     the current hour
     * @param currentMonth    the current month
     */
    public void updateRoomTargetTemperature(Room room, double currentTempRoom, int currentHour, int currentMonth){
        if(securityModule.getAwayMode()){
            //TODO allow user to define summer and winter months (issue #58)
            if(checkMonths(startSummer, endSummer, currentMonth))
                room.getTemperature().setTemperatureTarget(summerTemperatureAwayMode);
            else if (checkMonths(startWinter,endWinter, currentMonth))
                room.getTemperature().setTemperatureTarget(winterTemperatureAwayMode);
        }
        else if(room.getOverridden()){
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

    /**
     * Check toggle status hvac.
     *
     * @param room            the room
     * @param currentTempRoom the current temp room
     */
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

    /**
     * Check summer cooling.
     *
     * @param room               the room
     * @param currentMonth       the current month
     * @param outdoorTemperature the outdoor temperature
     * @param currentTempRoom    the current temp room
     */
    public void checkSummerCooling(Room room, int currentMonth, double outdoorTemperature, double currentTempRoom){
        // TODO allow user to define summer months (issue #58)
        if (securityModule.getAwayMode() == false)
        {
            if(checkMonths(startSummer,endSummer,currentMonth) && currentTempRoom > outdoorTemperature + 0.25){
                if(room.getCurrentStateHVAC()){
                    Logger.getInstance().outputToConsole("Disabling Air Conditioning: It is summer and cooler outdoors. Open all windows");
                }
                room.setCurrentStateHVAC(false);
                room.openAllWindows();
            }
       }
    }

    /**
     * Get current hour int.
     *
     * @param date the date
     * @return the int
     */
    public int getCurrentHour(Date date){
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Set summer temperature away mode.
     *
     * @param temp the temp
     */
    public void setSummerTemperatureAwayMode(double temp){
        this.summerTemperatureAwayMode = temp;
    }

    /**
     * Set winter temperature away mode.
     *
     * @param temp the temp
     */
    public void setWinterTemperatureAwayMode(double temp){
        this.winterTemperatureAwayMode = temp;
    }
    public void setSummerStartDate(int start){
        this.startSummer =start;
    }
    public void setSummerEndDate(int end){
        this.endSummer =end;
    }

    public void setWinterStartDate(int start){
        this.startWinter = start;
    }
    public void setWinterEndDate(int end){
        this.endWinter = end;
    }


    public void checkTemperatureAnomaly(double currentTemperature, Room room){
        if(currentTemperature < 0 || currentTemperature > 40){
            Logger.getInstance().outputToConsole(String.format("Something abnormal is happening to temperature in room %s, resetting all temperature settings to 21.0 Celsius", room.getName()));
            Logger.getInstance().outputToConsole("If problem persists contact Smart Home providers for technical help!");

            if (currentTemperature < 0){
                Logger.getInstance().outputToConsole(String.format("Pipes may have potentially burst in room %s since temperature is below 0 degrees Celsius", room.getName()));
            }

            double defaultTemperature = 21.0;

            room.getTemperature().setTemperatureOverridden(defaultTemperature);
            room.getTemperature().setTemperatureDay(defaultTemperature);
            room.getTemperature().setTemperatureMorning(defaultTemperature);
            room.getTemperature().setTemperatureNight(defaultTemperature);
        }
    }

    @Override
    public void updateTime(int time) {
        SimulationParameters simulationInstance = SimulationParameters.getInstance();
        int currentMonth = simulationInstance.getDate().getMonth();
        int currentHour = simulationInstance.getTime() / 3600;
        double outdoorTemperature = simulationInstance.getTemperature();
        for(Room room : roomList){
            double currentTempRoom = room.getTemperature().getCurrentTemperature();
            checkTemperatureAnomaly(currentTempRoom, room);
            updateRoomTargetTemperature(room, currentTempRoom, currentHour, currentMonth);
            checkToggleStatusHVAC(room, currentTempRoom);
            checkSummerCooling(room, currentMonth, outdoorTemperature, currentTempRoom);

            room.updateTemperature(outdoorTemperature);
        }

    }
}
