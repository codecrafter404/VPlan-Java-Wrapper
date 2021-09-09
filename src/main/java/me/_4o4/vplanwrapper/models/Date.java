package me._4o4.vplanwrapper.models;

public class Date {
    private String date;
    private int timestamp;

    public Date(String date, int timestamp) {
        this.date = date;
        this.timestamp = timestamp;
    }

    public String getDate() {
        return date;
    }

    public int getTimestamp() {
        return timestamp;
    }
}
