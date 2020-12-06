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
 * The type Change temp when in away mode.
 */
class ChangeTempWhenInAwayMode{

    /**
     * Change temp according to user preference.
     *
     * @throws HouseLoadException the house load exception
     */
    @Test
    void ChangeTempAccordingToUserPreference() throws HouseLoadException {
        ArrayList<Room> newRoomList = new ArrayList<Room>();
        newRoomList.add(House.getInstance().getRoomByName("Garage"));
        newRoomList.add(House.getInstance().getRoomByName("Kitchen"));
        Zone garageKitchenZone = new Zone(newRoomList, "GarageKitchenZone");
        Assert.assertEquals(House.getInstance().getRoomByName(garageKitchenZone.getRoomListOfNames().get(0)), House.getInstance().getRoomByName("Garage"));
    }
}
