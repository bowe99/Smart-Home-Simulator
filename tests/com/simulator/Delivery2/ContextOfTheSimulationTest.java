package com.simulator.Delivery2;

import com.simulator.model.SimulationParameters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Context of the simulation test.
 */
class ContextOfTheSimulationTest {

    private Assertions Assert;

    /**
     * Sets time interval.
     */
    @Test
    void setTimeInterval() {
        System.out.println("Setting the time speed to 2, factor should now equal 30 000");
        SimulationParameters.getInstance().setTimeInterval(30000);
        int temp = 0;
        //should now equal to 30 000
        Assert.assertEquals(SimulationParameters.getInstance().getTimeInterval(), temp);
    }
}
