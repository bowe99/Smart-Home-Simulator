package com.simulator.model;

/**
 * Represents a window within the simulation
 */
public class Window extends Entryway{
    private boolean open;
    private boolean blocked;

    /**
     * Constructor for a new window object
     * @param newName the new uniquely identifying name for the window
     */
    public Window(String newName){
        super(newName);
        this.open = false;
    }
    /**
     * Set the open boolean to true
     */
    public void setOpen(){
        this.open = true;
    }
    /** 
     * Set the open boolean to false
     */
    public void setClosed(){
        this.open = false;
    }
    /** 
     * Set the blocked boolean to true
     */
    public void setBlockedTrue(){
        this.blocked = true;
    }
    /** 
     * Set the blocked boolean to false
     */
    public void setBlockedFalse(){
        this.blocked=false;
    }
    
    /** 
     * Get the blocked boolean
     * @return boolean
     */
    public boolean getBlockedBoolean(){
        return blocked;
    }
    
    /** 
     * Get the open boolean
     * @return boolean
     */
    public boolean getOpenOrClosed(){
        return open;
    }

}
