package com.simulator.model;


public abstract class Observer {
    protected Profile profile;
    public abstract void updateLocation();
    public abstract void updateTime();
 }