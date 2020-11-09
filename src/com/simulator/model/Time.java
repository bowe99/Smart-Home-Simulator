package com.simulator.model;

import java.util.Date;

public class Time {

    private int time; //represents minutes of the day (0-1440) 
    private int interval = 60000; //in milliseconds
    private Date date = new Date(); //current date
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
        if (time == 1440){
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
        interval = 60000/factor;
    }

    
    /** 
     * Get time
     * @return int
     */
    public int getTime(){
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
}
