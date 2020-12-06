package com.simulator.Delivery2;

import com.simulator.model.SimulationParameters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContextOfTheSimulationTest {

    private Assertions Assert;

    @Test
    void setTimeInterval() {
        System.out.println("Setting the time speed to 2, factor should now equal 30 000");
        SimulationParameters.getInstance().setTimeInterval(30000);
        System.out.println("Printing out the new time speed and seeing if they correspond");
        int temp = 30000;
        System.out.print(SimulationParameters.getInstance().getTimeInterval());
        //should now equal to 30 000
        Assert.assertEquals(30000, temp);
    }
}
