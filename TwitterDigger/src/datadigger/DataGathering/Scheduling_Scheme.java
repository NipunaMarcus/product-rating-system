/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datadigger.DataGathering;

import datadigger.DataProcessing.Database_Handler;
import java.sql.Time;
import java.sql.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nipuna
 */
public class Scheduling_Scheme {

    // Initialize the variables.
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;
    private long timeInterval;
    volatile static int productId = 0;
    private Logger logger = Logger.getLogger("MyLogger");

    // Class constructor.
    public Scheduling_Scheme() {
        this.startDate = new Date(System.currentTimeMillis());
        this.endDate = new Date(System.currentTimeMillis());
        this.startTime = new Time(System.currentTimeMillis());
        this.endTime = new Time(System.currentTimeMillis() + 3600);
        this.timeInterval = 15;
    }

    // Start the schedule.
    public boolean startSchedule() {
        boolean isStart = false;
        try {
            Timer timer = new Timer();
            twitterTask(timer);
            isStart = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "startSchedule: " + ex.getMessage());
        }
        return isStart;
    }

    // Data gathering task for twitter
    public void twitterTask(Timer timer) {
        timer.scheduleAtFixedRate(new Twitter_Task(timer, productId), 1000, 900000);
    }

    // Data gatherin task for facebook
    public void facebookTask() {
        //TODO: Attach the facebook api task
    }

    // Stop the schedule.
    public boolean stopSchedule(Timer timer) {
        boolean isStoped = false;
        try {
            timer.cancel();
            isStoped = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "stopSchedule: " + ex.getMessage());
        }
        return isStoped;
    }
}
