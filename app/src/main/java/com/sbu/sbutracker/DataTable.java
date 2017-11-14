package com.sbu.sbutracker;

/**
 * Created by sumukha on 11/4/2017.
 */

public class DataTable {
    //columns
    private double Longitude;
    private double Lattitude;
    private long Timestamp;

    public double getLongitude(){
        return this.Longitude;
    }

    public double getLattitude(){
        return this.Lattitude;
    }

    public void setLongitude(double longitude){
        this.Longitude=longitude;
    }

    public void setLattitude(double lattitude){
        this.Lattitude=lattitude;
    }

    public long getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(long timestamp) {
        Timestamp = timestamp;
    }
    //getter : value title/subtitle
    //setter  : set the values

}
