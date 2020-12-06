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

class MonitorIndoorAndOutdoorTemp{

    @Test
    void monitorIndoorTemp() throws HouseLoadException {
        Assert.assertTrue((House.getInstance().getRoomByName("Garage").getTemperature().getTemperatureDay())==0);
    }
    @Test
    void monitorOutdoorTemp() throws HouseLoadException {
        Assert.assertTrue((House.getInstance().getRoomByName("Garage").getTemperature().getTemperatureDay())==0);
    }
}
