package com.example.morgana.testeestagio;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Track {
    private String id;
    private String trackName;
    private String trackData;
    private String trackDesc;
    private String trackPriori;
    private String trackStatus;

    public  Track(){

    }

    public String getId() {
        return id;
    }

    public String getTrackData() {
        return trackData;
    }

    public String getTrackDesc() {
        return trackDesc;
    }

    public String getTrackPriori() {
        return trackPriori;
    }

    public String getTrackStatus() {
        return trackStatus;
    }

    public Track(String id, String trackName, String trackDesc, String trackData, String trackStatus, String trackPriori){
        this.trackName = trackName;
        this.id = id;
        this.trackDesc = trackDesc;
        this.trackData = trackData;
        this.trackStatus = trackStatus;
        this.trackPriori = trackPriori;
    }

    public String getTrackName() {
        return trackName;
    }
}
