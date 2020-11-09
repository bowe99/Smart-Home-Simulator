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

    /**
     * set the locked boolean to true
     */
    public void setLocked(){
        this.locked = true;
    }
    
    /**
     * set the locked boolean to false
     */
    public void setUnlocked(){
        this.locked = false;
    }
    
    /** 
     * @return boolean
     */
    public boolean getLockedStatus(){
        return locked;
    }
}
