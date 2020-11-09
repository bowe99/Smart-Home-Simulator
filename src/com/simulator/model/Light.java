package com.simulator.model;

/**
 * Represents a light (or group of lights operated by a common switch) within the simulation
 */
public class Light {
    private String name;
    private boolean on;
    private boolean auto;

    /**
     * Contructor for a new light object
     * @param newName the new uniquely identifying name for the light
     */
    public Light(String newName){
        this.name = newName;
        this.on = false;
        this.auto = false;
    }

    /**
     * Get name of the light object
     * @return the name of the light
     */
    public String getName() {
        return name;
    }

    
    /** 
     * return the on boolean
     * @return boolean
     */
    public boolean getOnOff(){
        return on;
    }

    
    /** 
     * return the auto boolean
     * @return boolean
     */
    public boolean getAuto(){
        return auto;
    }
    /** 
     * set on boolean to true
     */
    public void setToOn(){
        this.on = true;
    }
    /** 
     * set on boolean to false
     */
    public void setToOff(){
        this.on = false;
    }
    /** 
     * set auto boolean to false
     */
    public void setAutoOn(){
        this.auto = true;
    }
    /** 
     * set auto boolean to false
     */
    public void setAutoOff(){
        this.auto = false;
    }


}
