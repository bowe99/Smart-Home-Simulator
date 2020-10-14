package com.simulator.model;

public class EntrywaySensor extends Sensor {

    public EntrywaySensor(String newName){
        super(newName);
    }

    @Override
    public int pollSensor() {
        return 0;
    }
}
