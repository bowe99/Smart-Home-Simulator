package com.simulator.model;

import com.simulator.gui.Logger;

public class Permission
{
    private PERMISSION_TYPE parentPermission;
    private PERMISSION_TYPE childPermission;
    private PERMISSION_TYPE guestPermission;
    private PERMISSION_TYPE strangerPermission;

    public Permission()
    {
        parentPermission = PERMISSION_TYPE.NONE;
        childPermission = PERMISSION_TYPE.NONE;
        guestPermission = PERMISSION_TYPE.NONE;
        strangerPermission = PERMISSION_TYPE.NONE;
    }

    public Permission(PERMISSION_TYPE parentPermission, PERMISSION_TYPE childPermission, PERMISSION_TYPE guestPermission, PERMISSION_TYPE strangerPermission){
        this.parentPermission = parentPermission;
        this.childPermission = childPermission;
        this.guestPermission = guestPermission;
        this.strangerPermission = strangerPermission;
    }

    public void setPermissionForUserType(USER_TYPE userType, PERMISSION_TYPE permissionType)
    {
        switch(userType) {
            case PARENT:
                parentPermission = permissionType;
                break;
            case CHILD:
                childPermission = permissionType;
                break;
            case GUEST:
                guestPermission = permissionType;
                break;
            case STRANGER:
                strangerPermission = permissionType;
                break;
        }
    }

    public PERMISSION_TYPE getPermissionType(USER_TYPE userType)
    {
        switch(userType) {
            case PARENT:
                return parentPermission;
            case CHILD:
                return childPermission;
            case GUEST:
                return guestPermission;
            case STRANGER:
                return strangerPermission;
        }
        return PERMISSION_TYPE.NONE;
    }

    public boolean checkPermission(Profile requestingUser, Room operationLocation)
    {
        PERMISSION_TYPE permissionType = null;
        switch(requestingUser.getUserType()) {
            case PARENT:
                permissionType = parentPermission;
                break;
            case CHILD:
                permissionType = childPermission;
                break;
            case GUEST:
                permissionType = guestPermission;
                break;
            case STRANGER:
                permissionType = strangerPermission;
                break;
        }

        switch(permissionType) {
            case ALL:
                return true;
            case IN_HOUSE:
                if(requestingUser.getCurrentRoom() != null && !requestingUser.getCurrentRoom().equals("Away"))
                    return true;
                else
                    Logger.getInstance().ouputToConsole("User must be in the house to perform the requested action");
                return false;
            case IN_ROOM:
                if(requestingUser.getCurrentRoom().equals(operationLocation))
                    return true;
                else
                    Logger.getInstance().ouputToConsole("User must be in the room to perform the requested action");
                return false;
            case AWAY:
                if(requestingUser.getCurrentRoom().getName().equals("Away"))
                    return true;
                else
                    Logger.getInstance().ouputToConsole("User must be away to perform the requested action");
                return false;
        }
        Logger.getInstance().ouputToConsole("User is not allowed to perform the requested action");
        return false;
    }
}
