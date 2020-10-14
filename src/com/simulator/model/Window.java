package com.simulator.model;

public class Window {
    private String name;
    private boolean open;
    private EntrywaySensor entrywaySensor;

    public Window(String newName){
        this.name = newName;
        this.open = false;
    }

    public int addEntrywaySensor(String newName) {
        return 0;
    }
}
