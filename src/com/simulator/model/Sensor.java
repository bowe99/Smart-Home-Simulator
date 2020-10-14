package com.simulator.model;

public abstract class Sensor {
    private String name;
    private boolean triggered;

    public Sensor(String newName){
        this.name = newName;
    }

    public abstract int pollSensor();
}
