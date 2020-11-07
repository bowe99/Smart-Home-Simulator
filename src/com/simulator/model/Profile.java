package com.simulator.model;

public class Profile
{
    private String name;
    private USER_TYPE user_type;
    private Room currentRoom;

    public Profile(String newName, USER_TYPE newUserType, Room newCurrentRoom)
    {
        this.name = newName;
        this.user_type = newUserType;
        this.currentRoom = newCurrentRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public USER_TYPE getUserType() {
        return user_type;
    }

    public void setUser_type(com.simulator.model.USER_TYPE user_type) {
        this.user_type = user_type;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String toString(){
        return name + "\n" + user_type.toString() + "\n" + currentRoom.getName();
    }
}
