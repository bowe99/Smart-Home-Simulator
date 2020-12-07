package com.simulator.model;

import com.simulator.controller.Logger;

/**
 * Represents a window within the simulation
 */
public class Window extends Entryway{
    private boolean open;
    private boolean blocked;

    /**
     * Constructor for a new window object
     *
     * @param newName the new uniquely identifying name for the window
     */
    public Window(String newName){
        super(newName);
        this.open = false;
    }

    /**
     * Set the open boolean to true (if path is not blocked)
     *
     * @return boolean representing successful completion of action
     */
    public boolean setOpen(){
        if(this.blocked && !this.open) {
            Logger.getInstance().outputToConsole("Window " + this.getName() +
                    " can not be opened because its path is blocked. \nWindow has remained closed.");
            return false;
        }
        else{
            if(this.open){
                Logger.getInstance().outputToConsole(String.format("Window: %s is now open", this.getName()));
            }
            this.open = true;
        }
        return true;
    }

    /**
     * Set the open boolean to false (if path is not blocked)
     *
     * @return boolean representing successful completion of action
     */
    public boolean setClosed(){
        if(this.blocked && this.open) {
            Logger.getInstance().outputToConsole("Window " + this.getName() +
                    " can not be closed because its path is blocked. \nWindow has remained open.");
            return false;
        }
        else
            this.open = false;
        return true;
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
     *
     * @return boolean boolean
     */
    public boolean getBlockedBoolean(){
        return blocked;
    }

    /**
     * Get the open boolean
     *
     * @return boolean boolean
     */
    public boolean getOpenOrClosed(){
        return open;
    }
}
