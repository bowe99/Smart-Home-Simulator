package com.simulator.Delivery2;

import com.simulator.model.House;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartHomeCoreFunctionalityModuleTest {

    @Test
    void doorUnlock() {
        System.out.println("Testing if the door can be unlocked");
        House.getInstance().getRoomByName("Kitchen").getDoorByName("OfficeDoor").setUnlocked();
        Assertions.assertEquals(false, House.getInstance().getRoomByName("Kitchen").getDoorByName("OfficeDoor").getLockedStatus());
    }

    @Test
    void doorLock() {
        System.out.println("Testing if the door can be locked");
        House.getInstance().getRoomByName("Kitchen").getDoorByName("OfficeDoor").setLocked();
        Assertions.assertEquals(true, House.getInstance().getRoomByName("Kitchen").getDoorByName("OfficeDoor").getLockedStatus());
    }

    @Test
    void windowOpen() {
        System.out.println("Testing if the window can be opened");
        House.getInstance().getRoomByName("Kitchen").getWindowByName("NorthWindow").setOpen();
        Assertions.assertEquals(true, House.getInstance().getRoomByName("Kitchen").getWindowByName("NorthWindow").getOpenOrClosed());
    }

    @Test
    void windowClose() {
        System.out.println("Testing if the window can be closed");
        Assertions.assertEquals(true, House.getInstance().getRoomByName("Kitchen").getWindowByName("NorthWindow").getOpenOrClosed());
    }


    @Test
    void lightON() {
        System.out.println("Testing if the light can be turned on");


        Assertions.assertEquals(false, House.getInstance().getRoomByName("Kitchen").getLightByName("IslandLight1").getOnOff());
    }

    @Test
    void lightOff() {
        System.out.println("Testing if the light can be turned off");

        Assertions.assertEquals(false, House.getInstance().getRoomByName("Kitchen").getLightByName("IslandLight1").getOnOff());
    }

    @Test
    void lightAutoOn() {
        System.out.println("Testing if the light auto mode can be turned on");

        Assertions.assertEquals(false, House.getInstance().getRoomByName("Kitchen").getLightByName("IslandLight1").getAuto());
    }

    @Test
    void lightAutoOff() {
        System.out.println("Testing if the light auto mode can be turned off");

        Assertions.assertEquals(false, House.getInstance().getRoomByName("Kitchen").getLightByName("IslandLight1").getAuto());
    }
}