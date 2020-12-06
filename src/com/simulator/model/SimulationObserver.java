package com.simulator.model;

/**
 * The abstract Observer class
 */
public abstract class SimulationObserver{
    /**
     * Update location.
     *
     * @param profile the profile
     */
    public abstract void updateLocation(Profile profile);

    /**
     * Update time.
     *
     * @param time the time
     */
    public abstract void updateTime(int time);
 }
 
