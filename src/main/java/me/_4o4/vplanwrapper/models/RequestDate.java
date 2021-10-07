package me._4o4.vplanwrapper.models;

public class RequestDate {
    private String date;
    private int timestamp;

    /**
     * @param date in format "YYYY-mm-dd"
     * @param timestamp UNIX-timestamp default 0; can be obtained with a request and is used for checking if the plan was edited
     */
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
