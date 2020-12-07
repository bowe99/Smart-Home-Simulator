package com.simulator.model;

import java.util.ArrayList;
import java.util.List;

import com.simulator.controller.Logger;

import javafx.scene.control.ToggleButton;

/**
 * Security module used to monitor presence of individuals in different rooms as well as invoke away mode
 */
public class SecurityModule extends SimulationObserver{
    private ToggleButton awayToggle;
    private boolean isAwayMode;
    private boolean detectionMonitored;
    private List<Light> lightsOnWhenAway;
    private int lightOnTime;
    private int lightOffTime;
    private int motionDetectedTime;
    private int timeOfDetection;
    private List<Profile> profiles;
    /**
     * The Away mode permission.
     */
    Permission awayModePermission = new Permission(PERMISSION_TYPE.AWAY, PERMISSION_TYPE.AWAY, PERMISSION_TYPE.NONE, PERMISSION_TYPE.NONE);

    /**
     * Constructor initializing attributes
     *
     * @param profiles   the profiles
     * @param awayToggle the away toggle
     * @param time       the time
     */
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
        this.lightOnTime = 64800;
        this.lightOffTime = 21600;
        this.timeOfDetection = -1;
    }

    /**
     * Add a light
     *
     * @param light the light
     */
    public void addLight(Light light){
        this.lightsOnWhenAway.add(light);
    }

    /**
     * Remove a light
     *
     * @param light the light
     */
    public void removeLight(Light light){
        this.lightsOnWhenAway.remove(light);
    }

    /**
     * Turn away mode on
     */
    public void toggleAwayMode(){
        if(this.isAwayMode == false){
            if(awayModePermission.checkPermission(SimulationParameters.getInstance().getCurrentUser(), SimulationParameters.getInstance().getCurrentUser().getCurrentRoom())) {
                for (Profile profile : profiles) {
                    if (!profile.getUserType().equals(USER_TYPE.STRANGER) && !profile.getCurrentRoom().getName().equals("Away")) {
                        Logger.getInstance().outputToConsole(String.format("Unable to enable away mode: %s is not Away", profile.getName()));
                        this.awayToggle.setSelected(false);
                        return;
                    }
                }
                this.isAwayMode = true;
                this.awayToggle.setText("On");
                Logger.getInstance().outputToConsole("Away mode has been successfully enabled");
                Logger.getInstance().outputToConsole("Windows will not open for security reasons");
            }
        }
        else{
            if(awayModePermission.checkPermission(SimulationParameters.getInstance().getCurrentUser(), SimulationParameters.getInstance().getCurrentUser().getCurrentRoom())) {
                this.isAwayMode = false;
                this.awayToggle.setText("Off");
                Logger.getInstance().outputToConsole("Away mode has been successfully disabled");
                Logger.getInstance().outputToConsole("Windows permitted to open");
            }
        }
    }

    /**
     * Get the status of away mode
     *
     * @return boolean boolean
     */
    public boolean getAwayMode(){
        return this.isAwayMode;
    }

    /**
     * Save the settings that were input
     *
     * @param startTime     the start time
     * @param endTime       the end time
     * @param detectionTime the detection time
     */
    public void saveSettings(int startTime, int endTime, int detectionTime){
        this.motionDetectedTime = detectionTime;
        this.lightOnTime = startTime;
        this.lightOffTime = endTime;
    }

    /** 
     * Update the location of the profile
     * @param profile
     */
    @Override
    public void updateLocation(Profile profile) {
        // Checks if current room is in the House
        if(this.isAwayMode){
            if (!profile.getCurrentRoom().getName().equals("Away") && profile.getUserType() != USER_TYPE.STRANGER){
                this.isAwayMode = false;
                this.awayToggle.setSelected(false);
                this.awayToggle.setText("Off");
                Logger.getInstance().outputToConsole(String.format("Away mode has been automatically disabled. The following recognized user has entered: %s", profile.getName()));
            }
            else if(!profile.getCurrentRoom().getName().equals("Away") && profile.getUserType() == USER_TYPE.STRANGER){
                Logger.getInstance().outputToConsole("WARNING: Unrecognized user has entered the house while in away mode");
                this.detectionMonitored = true;
            }
        }
    }
    
    /** 
     * Update the time 
     * @param time
     */
    @Override
    public void updateTime(int time) {
        if(this.isAwayMode){
            if(time == lightOnTime){
                for(Light light : lightsOnWhenAway){
                    light.setToOn();
                }
            }
            else if(time == lightOffTime){
                for(Light light : lightsOnWhenAway){
                    light.setToOff();

                }
            }
            else if(this.detectionMonitored){
                if(this.timeOfDetection == -1){
                    this.timeOfDetection = time + motionDetectedTime;
                }
                else if(time == this.timeOfDetection){
                    Logger.getInstance().outputToConsole("ALERT: Authorities have been notified due to unrecognized individual while in away mode");
                    this.timeOfDetection = -1;
                }
            }
        }
    }
}
