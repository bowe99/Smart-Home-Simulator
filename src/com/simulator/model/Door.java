package com.simulator.model;

public class Door {
    private String name;
    private boolean locked;
    private boolean open;
    private EntrywaySensor entrywaySensor;

    public Door(String newName){
        this.name = newName;
        this.open = false;
        this.locked = false;
    }
    public int addEntrywaySensor(String newName) {
        return 0;
    }
}
