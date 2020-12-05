package com.simulator.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The time class used to keep time and update the time
 */
public class Time {
    private List<SimulationObserver> observers = new ArrayList<SimulationObserver>();
    private int time; //represents seconds of the day (0-86400)
    private int interval = 1000; //in milliseconds
    private Date date; //current date
    final private long MILLISECOND_IN_A_DAY = 86400000;

    public Time(Date date, int time){
       this.date = date;
       this.time = time;
    }

    public Time (){
        this.date = new Date();
        this.time = 0;
    }
    
    /**
     * Update the time
     */
    public void update (){
        time++;
        if (time == 86400){
            time = 0;
            long currentTime = date.getTime();
            date = new Date(currentTime + MILLISECOND_IN_A_DAY ); 
        }     
    }

    
    /** 
     * Change the interval by a factor that is provided
     * @param factor
     */
    public void changeInterval(int factor){
        interval = 1000/factor;
    }

    
    /** 
     * Get time and update
     * @return int
     */
    public int getTimeAndUpdate(){
        // notifyObservers in here instead of update since the update method is running on a separate thread and 
        // was not updating the lights when it was calling notifyObservers
        notifyAllObservers();
        return time;
    }
    
    
    /** 
     * Get time and update
     * @return int
     */
    public int getTime(){
        // notifyObservers in here instead of update since the update method is running on a separate thread and 
        // was not updating the lights when it was calling notifyObservers
        return time;
    }


    
    /** 
     * Get interval
     * @return int
     */
    public int getInterval(){
        return interval;
    }
    
    /** 
     * Set the time
     * @param time
     */
    public void setTime(int time){

        this.time = time;
    }
    
    /** 
     * Get the date
     * @return Date
     */
    public Date getDate(){
        return date;
    }
    
    /** 
     * Set the date
     * @param date
     */
    public void setDate(Date date){
        this.date = date;
    }

    public void notifyAllObservers(){
        for (SimulationObserver observer : observers) {
           observer.updateTime(this.time);
        }
    }
    
    public void attach(SimulationObserver observer){
        observers.add(observer);		
     }
}
