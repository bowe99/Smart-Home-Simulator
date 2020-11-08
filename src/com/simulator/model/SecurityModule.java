package com.simulator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    Security module is the
*/
public class SecurityModule extends Observer{
    private boolean isAwayMode;
    private boolean detectionMonitored;
    private List<Light> lightsOnWhenAway;
    private int lightOnTime;
    private int lightOffTime;
    private int motionDetectedTime;


    public SecurityModule(List<Profile> profiles){
        for(Profile profile : profiles){
            profile.attach(this);
        }
        this.isAwayMode = false;
        this.detectionMonitored = false;
        this.lightsOnWhenAway = new ArrayList<Light>();
        this.motionDetectedTime = 0;
        this.lightOnTime = 1080;
        this.lightOffTime = 360;
    }

    public void addLight(Light light){
        this.lightsOnWhenAway.add(light);
    }
    
    public void removeLight(Light light){
        this.lightsOnWhenAway.remove(light);
    }

    public void toggleAwayMode(){
        this.isAwayMode = !this.isAwayMode;
    }

    public void saveSettings(int startTime, int endTime, int detectionTime){
        this.motionDetectedTime = detectionTime;
        this.lightOnTime = startTime;
        this.lightOffTime = endTime;
    }

    @Override
    public void updateLocation(Profile profile) {
        // Checks if current room is in the House
        if (!profile.getCurrentRoom().getName().equals("Away") && profile.getUserType() != USER_TYPE.STRANGER){
            this.isAwayMode = false;
        }
        else if(!profile.getCurrentRoom().getName().equals("Away") && profile.getUserType() == USER_TYPE.STRANGER){
            
        }
    }

    @Override
    public void updateTime() {
        if(this.isAwayMode){
            return;
        }
    }
    
}
