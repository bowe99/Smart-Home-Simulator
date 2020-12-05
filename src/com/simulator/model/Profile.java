package com.simulator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class type profile used to create user profiles
 */
public class Profile
{
    private List<SimulationObserver> observers = new ArrayList<SimulationObserver>();
    private String name;
    private USER_TYPE user_type;
    private Room currentRoom;

    public Profile(String newName, USER_TYPE newUserType, Room newCurrentRoom)
    {
        this.name = newName;
        this.user_type = newUserType;
        this.currentRoom = newCurrentRoom;
    }

    
    /** 
     * return name
     * @return String
     */
    public String getName() {
        return name;
    }

    
    /** 
     * set the name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    
    /** 
     * return the user type
     * @return USER_TYPE
     */
    public USER_TYPE getUserType() {
        return user_type;
    }

    
    /** 
     * Set the user type
     * @param user_type
     */
    public void setUser_type(com.simulator.model.USER_TYPE user_type) {
        this.user_type = user_type;
    }

    
    /** 
     * Get the current room
     * @return Room
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    
    /** 
     * Set the current room
     * @param currentRoom
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
        notifyAllObservers();
    }

    
    /** 
     * Add an observer to the list of observers
     * @param observer
     */
    public void attach(SimulationObserver observer){
        observers.add(observer);		
     }

    
    /** 
     * Print the profiles specifications
     * @return String
     */
    public String toString(){
        return name + "\n" + user_type.toString() + "\n" + currentRoom.getName();
    }
    /** 
     * Notify all observers
     */
    public void notifyAllObservers(){
        for (SimulationObserver observer : observers) {
           observer.updateLocation(this);
        }
     } 
}
