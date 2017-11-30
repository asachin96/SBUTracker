package com.sbu.sbutracker;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sachin on 23-Nov-17.
 */

class ActivityClass implements Parcelable {
    private int activityType;
    private long activityStartTime;
    private long activityEndTime;
    private double activityDistance;
    private double activityPace;

    protected ActivityClass(Parcel in) {
        activityType = in.readInt();
        activityStartTime = in.readLong();
        activityEndTime = in.readLong();
        activityDistance = in.readDouble();
        activityPace = in.readDouble();
    }

    public static final Creator<ActivityClass> CREATOR = new Creator<ActivityClass>() {
        @Override
        public ActivityClass createFromParcel(Parcel in) {
            return new ActivityClass(in);
        }

        @Override
        public ActivityClass[] newArray(int size) {
            return new ActivityClass[size];
        }
    };

    public ActivityClass() {

    }

    public void setActivityType(int activityType) {
        this.activityType = activityType;
    }

    public void setActivityStartTime(long activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public void setActivityEndTime(long activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public void setActivityDistance(double activityDistance) {
        this.activityDistance = activityDistance;
    }

    public void setActivityPace(double activityPace) {
        this.activityPace = activityPace;
    }

    public long getActivityStartTime() {
        return activityStartTime;
    }

    public long getActivityEndTime() {
        return activityEndTime;
    }

    public double getActivityDistance() {
        return activityDistance;
    }

    public double getActivityPace() {
        return activityPace;
    }

    public int getActivityType() {
        return activityType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(activityType);
        parcel.writeLong(activityStartTime);
        parcel.writeLong(activityEndTime);
        parcel.writeDouble(activityDistance);
        parcel.writeDouble(activityPace);
    }
}
