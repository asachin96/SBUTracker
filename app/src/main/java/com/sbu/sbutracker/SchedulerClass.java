package com.sbu.sbutracker;

/**
 * Created by Sachin on 23-Nov-17.
 */

class SchedulerClass {
    private String schedulerDate;
    private String schedulerDay;
    private String scheduleText1;
    private String scheduleText2;
    private String scheduleText3;

    public SchedulerClass(){

    }

    public SchedulerClass(String schedulerDate, String schedulerDay, String scheduleText1, String scheduleText2, String scheduleText3) {
        this.schedulerDate = schedulerDate;
        this.schedulerDay = schedulerDay;
        this.scheduleText1 = scheduleText1;
        this.scheduleText2 = scheduleText2;
        this.scheduleText3 = scheduleText3;
    }

    public String getSchedulerDate() {
        return schedulerDate;
    }

    public void setSchedulerDate(String schedulerDate) {
        this.schedulerDate = schedulerDate;
    }

    public String getSchedulerDay() {
        return schedulerDay;
    }

    public void setSchedulerDay(String schedulerDay) {
        this.schedulerDay = schedulerDay;
    }

    public String getScheduleText1() {
        return scheduleText1;
    }

    public void setScheduleText1(String scheduleText1) {
        this.scheduleText1 = scheduleText1;
    }

    public String getScheduleText2() {
        return scheduleText2;
    }

    public void setScheduleText2(String scheduleText2) {
        this.scheduleText2 = scheduleText2;
    }

    public String getScheduleText3() {
        return scheduleText3;
    }

    public void setScheduleText3(String scheduleText3) {
        this.scheduleText3 = scheduleText3;
    }
}
