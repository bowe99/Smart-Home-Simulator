package com.simulator;

import com.simulator.gui.Logger;
import com.simulator.gui.SmartHomeSimulatorController;
import com.simulator.model.House;
import com.simulator.model.SimulationParameters;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SmartHomeDashboardTest {
    @FXML TextArea newArea = null;

    @Test
    void updateHouseView(){
        System.out.println("Updating the house view test");
        SimulationParameters.getInstance().setSimulationStatus(true);
        assertEquals(true, SimulationParameters.getInstance().getSimulationStatus());
        SimulationParameters.getInstance().setSimulationStatus(false);
        assertEquals(false, SimulationParameters.getInstance().getSimulationStatus());
    }
}