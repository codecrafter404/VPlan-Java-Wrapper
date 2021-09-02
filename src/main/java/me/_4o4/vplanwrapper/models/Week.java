package me._4o4.vplanwrapper.models;

import java.util.List;

public class Week {
    List<Day> days;
    StartTimes times;
    String error;

    public Week(List<Day> days, StartTimes times, String error) {
        this.days = days;
        this.times = times;
        this.error = error;
    }

    public List<Day> getDays() {
        return days;
    }

    public StartTimes getTimes() {
        return times;
    }

    public String getError() {
        return error;
    }
}
