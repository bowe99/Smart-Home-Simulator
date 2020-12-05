package com.simulator.model;

public class Temperature {
    private double currentTemperature;
    private double temperatureMorning;
    private double temperatureDay;
    private double temperatureNight;
    private double overriddenTemperature;

    private double targetTemperature;

    Temperature(){

    }

    public void setAllTemperature(double temperature){
        this.currentTemperature = temperature;
        this.temperatureDay = temperature;
        this.temperatureMorning = temperature;
        this.temperatureNight = temperature;
    }

    public double getCurrentTemperature() {
        return this.currentTemperature;
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public double getTemperatureMorning() {
        return this.temperatureMorning;
    }

    public void setTemperatureMorning(double temperatureMorning) {
        this.temperatureMorning = temperatureMorning;
    }

    public double getTemperatureDay() {
        return this.temperatureDay;
    }

    public void setTemperatureDay(double temperatureDay) {
        this.temperatureDay = temperatureDay;
    }

    public double getTemperatureNight() {
        return this.temperatureNight;
    }

    public void setTemperatureNight(double temperatureNight) {
        this.temperatureNight = temperatureNight;
    }
    
    public double getTemperatureOverridden() {
        return this.overriddenTemperature;
    }

    public void setTemperatureOverridden(double overridenTemperature) {
        this.overriddenTemperature = overridenTemperature;
    }
    
    
    public double getTemperatureTarget() {
        return this.targetTemperature;
    }

    public void setTemperatureTarget(double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    @Override
    public String toString() {
        return "{" +
            " currentTemperature='" + getCurrentTemperature() + "'" +
            ", temperatureMorning='" + getTemperatureMorning() + "'" +
            ", temperatureDay='" + getTemperatureDay() + "'" +
            ", temperatureNight='" + getTemperatureNight() + "'" +
            "}";
    }


}
