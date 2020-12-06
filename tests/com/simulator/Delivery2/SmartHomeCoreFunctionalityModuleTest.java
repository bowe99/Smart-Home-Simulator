package com.simulator.Delivery2;

import com.simulator.model.House;
import com.simulator.model.HouseLoadException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Smart home core functionality module test.
 */
class SmartHomeCoreFunctionalityModuleTest {

    /**
     * Door unlock.
     *
     * @throws HouseLoadException the house load exception
     */
    @Test
    void doorUnlock() throws HouseLoadException {
        System.out.println("Testing if the door can be unlocked");
        House.getInstance().getRoomByName("Kitchen").getDoorByName("OfficeDoor").setUnlocked();
        Assertions.assertEquals(false, House.getInstance().getRoomByName("Kitchen").getDoorByName("OfficeDoor").getLockedStatus());
    }

    /**
     * Door lock.
     *
     * @throws HouseLoadException the house load exception
     */
    @Test
    void doorLock() throws HouseLoadException {
        System.out.println("Testing if the door can be locked");
        House.getInstance().getRoomByName("Kitchen").getDoorByName("OfficeDoor").setLocked();
        Assertions.assertEquals(true, House.getInstance().getRoomByName("Kitchen").getDoorByName("OfficeDoor").getLockedStatus());
    }

    /**
     * Window open.
     *
     * @throws HouseLoadException the house load exception
     */
    @Test
    void windowOpen() throws HouseLoadException {
        System.out.println("Testing if the window can be opened");
        House.getInstance().getRoomByName("Kitchen").getWindowByName("NorthWindow").setOpen();
        Assertions.assertEquals(true, House.getInstance().getRoomByName("Kitchen").getWindowByName("NorthWindow").getOpenOrClosed());
    }

    /**
     * Window close.
     *
     * @throws HouseLoadException the house load exception
     */
    @Test
    void windowClose() throws HouseLoadException {
        System.out.println("Testing if the window can be closed");
        Assertions.assertEquals(true, House.getInstance().getRoomByName("Kitchen").getWindowByName("NorthWindow").getOpenOrClosed());
    }


    /**
     * Light on.
     *
     * @throws HouseLoadException the house load exception
     */
    @Test
    void lightON() throws HouseLoadException {
        System.out.println("Testing if the light can be turned on");


        Assertions.assertEquals(false, House.getInstance().getRoomByName("Kitchen").getLightByName("IslandLight1").getOnOff());
    }

    /**
     * Light off.
     *
     * @throws HouseLoadException the house load exception
     */
    @Test
    void lightOff() throws HouseLoadException {
        System.out.println("Testing if the light can be turned off");

        Assertions.assertEquals(false, House.getInstance().getRoomByName("Kitchen").getLightByName("IslandLight1").getOnOff());
    }

    /**
     * Light auto on.
     *
     * @throws HouseLoadException the house load exception
     */
    @Test
    void lightAutoOn() throws HouseLoadException {
        System.out.println("Testing if the light auto mode can be turned on");

        Assertions.assertEquals(false, House.getInstance().getRoomByName("Kitchen").getLightByName("IslandLight1").getAuto());
    }

    /**
     * Light auto off.
     *
     * @throws HouseLoadException the house load exception
     */
    @Test
    void lightAutoOff() throws HouseLoadException {
        System.out.println("Testing if the light auto mode can be turned off");

        Assertions.assertEquals(false, House.getInstance().getRoomByName("Kitchen").getLightByName("IslandLight1").getAuto());
    }
}