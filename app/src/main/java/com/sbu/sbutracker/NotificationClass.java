package com.sbu.sbutracker;

/**
 * Created by Sachin on 23-Nov-17.
 */

class NotificationClass {
    private String notificationHeading;
    private String notificationText;
    private long notificationTime;
    private Boolean notificationSeen;

    public NotificationClass() {

    }

    public String getNotificationHeading() {
        return notificationHeading;
    }

    public void setNotificationHeading(String notificationHeading) {
        this.notificationHeading = notificationHeading;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public long getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(long notificationTime) {
        this.notificationTime = notificationTime;
    }

    public Boolean getNotificationSeen() {
        return notificationSeen;
    }

    public void setNotificationSeen(Boolean notificationSeen) {
        this.notificationSeen = notificationSeen;
    }
}
