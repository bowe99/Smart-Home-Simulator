package com.simulator;

import com.simulator.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartHomeSecurityModuleTest {
    @FXML ToggleButton awayModeToggle;


    @Test
    void detectMotionAndNotify() {
        SimulationParameters simulation = SimulationParameters.getInstance();
        SecurityModule securityModule = new SecurityModule(simulation.getAllUsers(), awayModeToggle, simulation.getTimeObject());
        Profile profile = new Profile("Timmy", USER_TYPE.CHILD, House.getInstance().getRoomByName("Kitchen"));
        profile.notifyAllObservers();
    }

    @Test
    void setTimeToPass() {
        SimulationParameters simulation = SimulationParameters.getInstance();
        SecurityModule securityModule = new SecurityModule(simulation.getAllUsers(), awayModeToggle, simulation.getTimeObject());
        Profile profile = new Profile("Timmy", USER_TYPE.CHILD, House.getInstance().getRoomByName("Kitchen"));
        profile.notifyAllObservers();
    }

    @Test
    void toggleAwayMode() {
        SimulationParameters simulation = SimulationParameters.getInstance();
        SecurityModule securityModule = new SecurityModule(simulation.getAllUsers(), awayModeToggle, simulation.getTimeObject());
        System.out.print(securityModule.getAwayMode());
        assertEquals(false, securityModule.getAwayMode());
        System.out.println("Test passed");
    }
}