package com.simulator.model;

/**
 * Represents a window within the simulation
 */
public class Window extends Entryway{
    private boolean open;

    /**
     * Constructor for a new window object
     * @param newName the new uniquely identifying name for the window
     */
    public Window(String newName){
        super(newName);
        this.open = false;
    }

    public void setOpen(){
        this.open = true;
    }

    public void setClosed(){
        this.open = false;
    }

    public boolean getOpenOrClosed(){
        return open;
    }

}
