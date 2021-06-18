package models;

import java.util.List;

public class Week {
    List<Day> days;
    StartTimes times;

    public Week(List<Day> days, StartTimes times) {
        this.days = days;
        this.times = times;
    }

    public List<Day> getDays() {
        return days;
    }

    public StartTimes getTimes() {
        return times;
    }
}
