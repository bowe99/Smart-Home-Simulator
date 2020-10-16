package src.com.simulator.model;

/**
 * Abstraction representing an entryway to the home within the simulation (windows, doors)
 */
public abstract class Entryway {
    private String name;
    private boolean open;
    private EntrywaySensor entrywaySensor;

    /**
     * Constructor for a new entryway object
     * @param newName the new uniquely identifying name for the entryway
     */
    public Entryway(String newName){
        this.name = newName;
        this.open = false;
    }

    /**
     * Adds an entryway sensor to the entryway
     * @param newName the new uniquely identifying name for the sensor
     * @return an integer representing the success or failure of the request
     * a return value of 0 represents success
     * a return value of 1 represents failure
     */
    public int addEntrywaySensor(String newName) {
        this.entrywaySensor = new EntrywaySensor(newName);
        return 0;
    }

    /**
     * Get name of this object
     * @return the name of this object
     */
    public String getName() {
        return name;
    }
}
