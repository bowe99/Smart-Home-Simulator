package com.simulator.Delivery2;

import com.simulator.model.SimulationParameters;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimulationParametersTest {
    @FXML TextArea newArea = null;

    @Test
    void getSimulationStatus() {
        System.out.println(SimulationParameters.getInstance().getSimulationStatus());
        SimulationParameters.getInstance().setSimulationStatus(false);
        assertEquals(false, SimulationParameters.getInstance().getSimulationStatus());
    }

    @Test
    void setSimulationStatus() {
        System.out.println("Setting the simulation Status to true then printing, then setting to false and then asserting \n");
        SimulationParameters.getInstance().setSimulationStatus(true);
        assertEquals(true, SimulationParameters.getInstance().getSimulationStatus());
        SimulationParameters.getInstance().setSimulationStatus(false);
        assertEquals(false, SimulationParameters.getInstance().getSimulationStatus());

    }

    @Test
    void printToTxtFile() {System.out.println("printing to simulation_parameters.txt"); }
}