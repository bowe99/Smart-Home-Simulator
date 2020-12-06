package com.simulator.Delivery2;

import com.simulator.model.SimulationParameters;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Simulation parameters test.
 */
class SimulationParametersTest {
    /**
     * The New area.
     */
    @FXML TextArea newArea = null;

    /**
     * Gets simulation status.
     */
    @Test
    void getSimulationStatus() {
        System.out.println(SimulationParameters.getInstance().getSimulationStatus());
        SimulationParameters.getInstance().setSimulationStatus(false);
        assertEquals(false, SimulationParameters.getInstance().getSimulationStatus());
    }

    /**
     * Sets simulation status.
     */
    @Test
    void setSimulationStatus() {
        System.out.println("Setting the simulation Status to true then printing, then setting to false and then asserting \n");
        SimulationParameters.getInstance().setSimulationStatus(true);
        assertEquals(true, SimulationParameters.getInstance().getSimulationStatus());
        SimulationParameters.getInstance().setSimulationStatus(false);
        assertEquals(false, SimulationParameters.getInstance().getSimulationStatus());

    }

    /**
     * Print to txt file.
     */
    @Test
    void printToTxtFile() {System.out.println("printing to simulation_parameters.txt"); }
}