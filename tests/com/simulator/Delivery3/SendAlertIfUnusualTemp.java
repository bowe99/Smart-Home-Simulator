package com.simulator.Delivery3;

import com.simulator.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Send alert if unusual temp.
 */
class SendAlertIfUnusualTemp{

    /**
     * Unusual temp alert.
     *
     * @throws HouseLoadException the house load exception
     */
    @Test
    void UnusualTempAlert() throws HouseLoadException {
        ArrayList<Room> newRoomList = new ArrayList<Room>();
        newRoomList.add(House.getInstance().getRoomByName("Garage"));
        newRoomList.add(House.getInstance().getRoomByName("Kitchen"));
        Zone garageKitchenZone = new Zone(newRoomList, "GarageKitchenZone");
        Assert.assertEquals(House.getInstance().getRoomByName(garageKitchenZone.getRoomListOfNames().get(0)), House.getInstance().getRoomByName("Garage"));
    }
}
