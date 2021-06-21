package me._4o4.vplanwrapper.models;

public class Date {
    private String date;
    private int timestamp = 0;

    public Date(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public int getTimestamp() {
        return timestamp;
    }
}
