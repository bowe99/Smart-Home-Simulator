package com.simulator.model;


public abstract class Observer {
    public abstract void updateLocation(Profile profile);
    public abstract void updateTime(int time);
 }