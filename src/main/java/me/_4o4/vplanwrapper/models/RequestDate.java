package me._4o4.vplanwrapper.models;

public class RequestDate {
    private final String date;
    private final int timestamp;

    public RequestDate(String date, int timestamp) {
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
