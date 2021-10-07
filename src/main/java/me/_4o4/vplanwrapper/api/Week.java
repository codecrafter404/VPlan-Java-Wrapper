package me._4o4.vplanwrapper.api;

import java.time.LocalTime;
import java.util.List;

/**
 * A user-friendly week object, containing: days, error and the TimeTable
 */
public class Week {
    /**
     * Days for requested dates
     */
    List<Day> days;

    /**
     * If there is an error (wrong date format...), the message will appear here
     */
    String error;

    /**
     * Start times of every hour use .get(<hour index starting by 0>)
     */
    List<LocalTime> timeTable;

    public Week(List<Day> days, String error, List<LocalTime> timeTable) {
        this.days = days;
        this.error = error;
        this.timeTable = timeTable;
    }

    public List<Day> getDays() {
        return days;
    }

    public String getError() {
        return error;
    }

    public List<LocalTime> getTimeTable() {
        return timeTable;
    }
}
