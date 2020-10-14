package com.simulator.model;

public class Door extends Entryway{
    private boolean locked;

    public Door(String newName){
        super(newName);
        this.locked = false;
    }
}
