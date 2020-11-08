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

    public void update (){
        time++;
        if (time == 1440){
             time = 0;
            long currentTime = date.getTime();
            date = new Date(currentTime + MILLISECOND_IN_A_DAY ); 
        }     
    }

    public void changeInterval(int factor){
        interval = 60000/factor;
    }

    public int getTime(){
        return time;
    }
    public int getInterval(){
        return interval;
    }
    public void setTime(int time)
    {
        this.time = time;
    }
    public Date getDate(){
        return date;
    }
    public void setDate(Date date)
    {
        this.date = date;
    }
}
