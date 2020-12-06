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
 * The type Shut down ac open windows.
 */
class ShutDownACOpenWindows{

    /**
     * Shut down ac auto.
     *
     * @throws HouseLoadException the house load exception
     */
    @Test
    void ShutDownACAuto() throws HouseLoadException {
        House.getInstance().getRoomByName("Garage").setCurrentStateHVAC(true);
        Assert.assertTrue(House.getInstance().getRoomByName("Garage").getCurrentStateHVAC());
        House.getInstance().getRoomByName("Garage").resetTemperature(0);
        Assert.assertTrue(House.getInstance().getRoomByName("Garage").getCurrentStateHVAC());
    }

    /**
     * Open windows auto.
     *
     * @throws HouseLoadException the house load exception
     */
    @Test
    void OpenWindowsAuto() throws HouseLoadException {
        House.getInstance().getRoomByName("Garage").openAllWindows();
        Assert.assertTrue(House.getInstance().getRoomByName("Garage").getWindowsAmount()==2);
    }
}
