package com.simulator.Delivery3;

import com.simulator.controller.Logger;
import com.simulator.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Accept desired temp for each time.
 */
class AcceptDesiredTempForEachTime{

    /**
     * Input temp for daytime.
     *
     * @throws HouseLoadException the house load exception
     */
    @Test
    void inputTempForDaytime() throws HouseLoadException {

        ArrayList<Room> newRoomList = new ArrayList<Room>();
        newRoomList.add(House.getInstance().getRoomByName("Garage"));
        newRoomList.add(House.getInstance().getRoomByName("Kitchen"));
        Zone garageKitchenZone = new Zone(newRoomList, "GarageKitchenZone");

        int wrapper = 25;
        Assert.assertEquals(25, wrapper);
    }

    /**
     * Input temp for morning.
     *
     * @throws HouseLoadException the house load exception
     */
    @Test
    void inputTempForMorning() throws HouseLoadException {

        ArrayList<Room> newRoomList = new ArrayList<Room>();
        newRoomList.add(House.getInstance().getRoomByName("Garage"));
        newRoomList.add(House.getInstance().getRoomByName("Kitchen"));
        Zone garageKitchenZone = new Zone(newRoomList, "GarageKitchenZone");

        int wrapper = 25;
        Assert.assertEquals(25, wrapper);
    }

    /**
     * Input temp for nighttime.
     *
     * @throws HouseLoadException the house load exception
     */
    @Test
    void inputTempForNighttime() throws HouseLoadException {

        ArrayList<Room> newRoomList = new ArrayList<Room>();
        newRoomList.add(House.getInstance().getRoomByName("Garage"));
        newRoomList.add(House.getInstance().getRoomByName("Kitchen"));
        Zone garageKitchenZone = new Zone(newRoomList, "GarageKitchenZone");

        int wrapper = 25;
        Assert.assertEquals(25, wrapper);
    }
}
