package com.simulator.model;

public abstract class Entryway {
    private String name;
    private boolean open;
    private EntrywaySensor entrywaySensor;

    public Entryway(String newName){
        this.name = newName;
        this.open = false;
    }

    public int addEntrywaySensor(String newName) {
        this.entrywaySensor = new EntrywaySensor(newName);
        return 0;
    }

    public String getName() {
        return name;
    }
}
