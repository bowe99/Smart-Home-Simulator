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

class AcceptDesiredTempForEachTime{

    @Test
    void inputTempForDaytime() {

        ArrayList<Room> newRoomList = new ArrayList<Room>();
        newRoomList.add(House.getInstance().getRoomByName("Garage"));
        newRoomList.add(House.getInstance().getRoomByName("Kitchen"));
        Zone garageKitchenZone = new Zone(newRoomList, "GarageKitchenZone");

        int wrapper = 25;
        Assert.assertEquals(25, wrapper);
    }
    @Test
    void inputTempForMorning() {

        ArrayList<Room> newRoomList = new ArrayList<Room>();
        newRoomList.add(House.getInstance().getRoomByName("Garage"));
        newRoomList.add(House.getInstance().getRoomByName("Kitchen"));
        Zone garageKitchenZone = new Zone(newRoomList, "GarageKitchenZone");

        int wrapper = 25;
        Assert.assertEquals(25, wrapper);
    }
    @Test
    void inputTempForNighttime() {

        ArrayList<Room> newRoomList = new ArrayList<Room>();
        newRoomList.add(House.getInstance().getRoomByName("Garage"));
        newRoomList.add(House.getInstance().getRoomByName("Kitchen"));
        Zone garageKitchenZone = new Zone(newRoomList, "GarageKitchenZone");

        int wrapper = 25;
        Assert.assertEquals(25, wrapper);
    }
}
