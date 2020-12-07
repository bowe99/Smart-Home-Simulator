package com.simulator.model;

/**
 * The type Temperature.
 */
public class Temperature {
    private double currentTemperature;
    private double temperatureMorning;
    private double temperatureDay;
    private double temperatureNight;
    private double overriddenTemperature;

    private double targetTemperature;

    /**
     * Set all temperature.
     *
     * @param temperature the temperature
     */
    public void setAllTemperature(double temperature){
        this.currentTemperature = temperature;
        this.temperatureDay = temperature;
        this.temperatureMorning = temperature;
        this.temperatureNight = temperature;
    }

    /**
     * Gets current temperature.
     *
     * @return the current temperature
     */
    public double getCurrentTemperature() {
        return this.currentTemperature;
    }

    /**
     * Sets current temperature.
     *
     * @param currentTemperature the current temperature
     */
    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    /**
     * Gets temperature morning.
     *
     * @return the temperature morning
     */
    public double getTemperatureMorning() {
        return this.temperatureMorning;
    }

    /**
     * Sets temperature morning.
     *
     * @param temperatureMorning the temperature morning
     */
    public void setTemperatureMorning(double temperatureMorning) {
        this.temperatureMorning = temperatureMorning;
    }

    /**
     * Gets temperature day.
     *
     * @return the temperature day
     */
    public double getTemperatureDay() {
        return this.temperatureDay;
    }

    /**
     * Sets temperature day.
     *
     * @param temperatureDay the temperature day
     */
    public void setTemperatureDay(double temperatureDay) {
        this.temperatureDay = temperatureDay;
    }

    /**
     * Gets temperature night.
     *
     * @return the temperature night
     */
    public double getTemperatureNight() {
        return this.temperatureNight;
    }

    /**
     * Sets temperature night.
     *
     * @param temperatureNight the temperature night
     */
    public void setTemperatureNight(double temperatureNight) {
        this.temperatureNight = temperatureNight;
    }

    /**
     * Gets temperature overridden.
     *
     * @return the temperature overridden
     */
    public double getTemperatureOverridden() {
        return this.overriddenTemperature;
    }

    /**
     * Sets temperature overridden.
     *
     * @param overridenTemperature the overriden temperature
     */
    public void setTemperatureOverridden(double overridenTemperature) {
        this.overriddenTemperature = overridenTemperature;
    }


    /**
     * Gets temperature target.
     *
     * @return the temperature target
     */
    public double getTemperatureTarget() {
        return this.targetTemperature;
    }

    /**
     * Sets temperature target.
     *
     * @param targetTemperature the target temperature
     */
    public void setTemperatureTarget(double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }
    /**
     * Prints out the temperature readings
     *
     */
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
