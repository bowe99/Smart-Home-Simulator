package com.simulator.Delivery2;

import com.simulator.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Smart home security module test.
 */
class SmartHomeSecurityModuleTest {
    /**
     * The Away mode toggle.
     */
    @FXML ToggleButton awayModeToggle;


    /**
     * Detect motion and notify.
     *
     * @throws HouseLoadException the house load exception
     */
    @Test
    void detectMotionAndNotify() throws HouseLoadException {
        SimulationParameters simulation = SimulationParameters.getInstance();
        SecurityModule securityModule = new SecurityModule(simulation.getAllUsers(), awayModeToggle, simulation.getTimeObject());
        Profile profile = new Profile("Timmy", USER_TYPE.CHILD, House.getInstance().getRoomByName("Kitchen"));
        profile.notifyAllObservers();
    }

    /**
     * Sets time to pass.
     *
     * @throws HouseLoadException the house load exception
     */
    @Test
    void setTimeToPass() throws HouseLoadException {
        SimulationParameters simulation = SimulationParameters.getInstance();
        SecurityModule securityModule = new SecurityModule(simulation.getAllUsers(), awayModeToggle, simulation.getTimeObject());
        Profile profile = new Profile("Timmy", USER_TYPE.CHILD, House.getInstance().getRoomByName("Kitchen"));
        profile.notifyAllObservers();
    }

    /**
     * Toggle away mode.
     *
     * @throws HouseLoadException the house load exception
     */
    @Test
    void toggleAwayMode() throws HouseLoadException {
        SimulationParameters simulation = SimulationParameters.getInstance();
        SecurityModule securityModule = new SecurityModule(simulation.getAllUsers(), awayModeToggle, simulation.getTimeObject());
        System.out.print(securityModule.getAwayMode());
        assertEquals(false, securityModule.getAwayMode());
        System.out.println("Test passed");
    }
}