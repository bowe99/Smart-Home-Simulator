package com.simulator.model;

/**
 * Represents an entryway sensor within the simulation
 */
public class EntrywaySensor extends Sensor {

    /**
     * Constructor for a new entryway sensor
     * @param newName the new uniquely identifying name for the sensor
     */
    public EntrywaySensor(String newName){
        super(newName);
    }
    /**
     * Poll sensor
     * @return 0
     */
    @Override
    public int pollSensor() {
        return 0;
    }
}
