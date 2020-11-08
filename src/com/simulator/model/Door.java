package com.simulator.model;

/**
 * Represents a door within the simulation
 */
public class Door extends Entryway{
    private boolean locked;

    /**
     * Constructor for a new Door object
     * @param newName the new uniquely identifying name for the door
     */
    public Door(String newName){
        super(newName);
        this.locked = false;
    }

    public void setLocked(){
        this.locked = true;
    }
    
    public void setUnlocked(){
        this.locked = false;
    }

    public boolean getLockedStatus(){
        return locked;
    }
}
