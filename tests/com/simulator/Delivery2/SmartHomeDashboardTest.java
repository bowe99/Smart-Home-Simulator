package com.simulator.Delivery2;

import com.simulator.model.SimulationParameters;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.junit.jupiter.api.Test;

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