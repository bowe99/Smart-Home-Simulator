package com.simulator.model;

/**
 * Represents a motion detector with the simulation
 */
public class MotionSensor extends Sensor {

    /**
     * Constructor for a new motion sensor object
     * @param newName the new uniquely identifying name for the motion sensor
     */
    public MotionSensor(String newName) {
        super(newName);
    }

    
    /** 
     * return 0
     * @return int
     */
    @Override
    public int pollSensor() {
        return 0;
    }
}
