package com.simulator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.simulator.gui.Logger;

import javafx.scene.control.ToggleButton;

/*
    Security module is the
*/
public class SecurityModule extends Observer{
    private ToggleButton awayToggle;
    private boolean isAwayMode;
    private boolean detectionMonitored;
    private List<Light> lightsOnWhenAway;
    private int lightOnTime;
    private int lightOffTime;
    private int motionDetectedTime;
    private int timeOfDetection;
    private List<Profile> profiles;
    Permission awayModePermission = new Permission(PERMISSION_TYPE.AWAY, PERMISSION_TYPE.AWAY, PERMISSION_TYPE.NONE, PERMISSION_TYPE.NONE);


    public SecurityModule(List<Profile> profiles, ToggleButton awayToggle, Time time){
        this.awayToggle = awayToggle;
        this.profiles = profiles;
        for(Profile profile : profiles){
            profile.attach(this);
        }
        time.attach(this);
        this.isAwayMode = false;
        this.detectionMonitored = false;
        this.lightsOnWhenAway = new ArrayList<Light>();
        this.motionDetectedTime = 0;
        this.lightOnTime = 1080;
        this.lightOffTime = 360;
        this.timeOfDetection = -1;
    }

    public void addLight(Light light){
        this.lightsOnWhenAway.add(light);
    }
    
    public void removeLight(Light light){
        this.lightsOnWhenAway.remove(light);
    }

    public void toggleAwayMode(){
        if(this.isAwayMode == false){
            if(awayModePermission.checkPermission(SimulationParameters.getInstance().getCurrentUser(), SimulationParameters.getInstance().getCurrentUser().getCurrentRoom())) {
                for (Profile profile : profiles) {
                    if (!profile.getUserType().equals(USER_TYPE.STRANGER) && !profile.getCurrentRoom().getName().equals("Away")) {
                        Logger.getInstance().ouputToConsole(String.format("Unable to enable away mode: %s is not Away", profile.getName()));
                        this.awayToggle.setSelected(false);
                        return;
                    }
                }
                this.isAwayMode = true;
                this.awayToggle.setText("On");
                Logger.getInstance().ouputToConsole("Away mode has been successfully enabled");
            }
        }
        else{
            if(awayModePermission.checkPermission(SimulationParameters.getInstance().getCurrentUser(), SimulationParameters.getInstance().getCurrentUser().getCurrentRoom())) {
                this.isAwayMode = false;
                this.awayToggle.setText("Off");
                Logger.getInstance().ouputToConsole("Away mode has been successfully disabled");
            }
        }
    }

    public boolean getAwayMode(){
        return this.isAwayMode;
    }


    public void saveSettings(int startTime, int endTime, int detectionTime){
        this.motionDetectedTime = detectionTime;
        this.lightOnTime = startTime;
        this.lightOffTime = endTime;
    }

    @Override
    public void updateLocation(Profile profile) {
        // Checks if current room is in the House
        if(this.isAwayMode){
            if (!profile.getCurrentRoom().getName().equals("Away") && profile.getUserType() != USER_TYPE.STRANGER){
                this.isAwayMode = false;
                this.awayToggle.setSelected(false);
                this.awayToggle.setText("Off");
                Logger.getInstance().ouputToConsole(String.format("Away mode has been automatically disabled. The following recognized user has entered: %s", profile.getName()));
            }
            else if(!profile.getCurrentRoom().getName().equals("Away") && profile.getUserType() == USER_TYPE.STRANGER){
                Logger.getInstance().ouputToConsole("WARNING: Unrecognized user has entered the house while in away mode");
                this.detectionMonitored = true;
            }
        }
    }

    @Override
    public void updateTime(int time) {
        if(this.isAwayMode){
            if(time == lightOnTime){
                for(Light light : lightsOnWhenAway){
                    light.setToOn();
                    Logger.getInstance().ouputToConsole(String.format("%s was turned on", light.getName()));
                }
            }
            else if(time == lightOffTime){
                for(Light light : lightsOnWhenAway){
                    light.setToOff();
                    Logger.getInstance().ouputToConsole(String.format("%s was turned off", light.getName()));

                }
            }
            else if(this.detectionMonitored){
                if(this.timeOfDetection == -1){
                    this.timeOfDetection = time + motionDetectedTime;
                }
                else if(time == this.timeOfDetection){
                    Logger.getInstance().ouputToConsole("ALERT: Authorities have been notified due to unrecognized individual while in away mode");
                    this.timeOfDetection = -1;
                }
            }
        }
    }
    
}
