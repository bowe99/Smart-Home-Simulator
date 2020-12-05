package com.simulator.model;

/**
 * The abstract Observer class
 */
public abstract class SimulationObserver{
    public abstract void updateLocation(Profile profile);
    public abstract void updateTime(int time);
 }