package com.simulator.model;

/**
 * Abstraction representing a sensor in the home within the simulation (motion, entryway)
 */
public abstract class Sensor {
    private String name;
    private boolean triggered;

    /**
     * Constructor for a new sensor object
     *
     * @param newName the new uniquely identifying name for the sensor
     */
    public Sensor(String newName){
        this.name = newName;
    }

    /**
     * Have sensor test the entryway for obstructions
     *
     * @return int  an integer representing the triggering of the sensor a return value of 0 means the sensor is not triggered a return value of 1 means the sensor is triggered
     */
    public abstract int pollSensor();

    /**
     * Get the name of the sensor object
     *
     * @return the name of the sensor object
     */
    public String getName() {
        return name;
    }
}
