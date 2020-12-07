package com.simulator.model;

/**
 * Abstraction representing an entryway to the home within the simulation (windows, doors)
 */
public abstract class Entryway {
    private String name;
    private boolean open;

    /**
     * Constructor for a new entryway object
     * @param newName the new uniquely identifying name for the entryway
     */
    public Entryway(String newName){
        this.name = newName;
        this.open = false;
    }

    /**
     * Get name of this object
     * @return the name of this object
     */
    public String getName() {
        return name;
    }
}
