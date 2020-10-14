package com.simulator.model;

public class MotionSensor extends Sensor {

    public MotionSensor(String newName) {
        super(newName);
    }

    @Override
    public int pollSensor() {
        return 0;
    }
}
