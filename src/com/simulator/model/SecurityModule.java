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
    private Map<String, Integer> mappedLightsOnTimes;


    public SecurityModule(Profile profile){
        this.profile = profile;
        this.profile.attach(this);

        this.isAwayMode = false;
        this.detectionMonitored = false;
        this.lightsOnWhenAway = new ArrayList<Light>();
        this.mappedLightsOnTimes = new HashMap<String, Integer>();

    }

    public void addLight(Light light){
        this.lightsOnWhenAway.add(light);
    }

    @Override
    public void updateLocation() {
        // Checks if current room is in the House
        if (House.getInstance().getRoomByName(this.profile.getCurrentRoom().getName()).getName() == "Away"){
            this.isAwayMode = false;
        }
    }

    @Override
    public void updateTime() {
        if(this.isAwayMode){
            return;
        }
    }
    
}
