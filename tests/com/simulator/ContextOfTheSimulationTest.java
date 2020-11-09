package com.simulator;

import com.simulator.model.SimulationParameters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContextOfTheSimulationTest {

    private Assertions Assert;

    @Test
    void setTimeInterval() {
        System.out.println("Setting the time speed to 2, factor should now equal 30 000");
        SimulationParameters.getInstance().setTimeInterval(2);
        System.out.println("Printing out the new time speed and seeing if they correspond");
        System.out.print(SimulationParameters.getInstance().getTimeInterval());
        //should now equal to 30 000
        Assert.assertEquals(30000, SimulationParameters.getInstance().getTimeInterval());

    }
}