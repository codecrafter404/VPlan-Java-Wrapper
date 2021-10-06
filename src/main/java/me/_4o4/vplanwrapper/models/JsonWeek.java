package me._4o4.vplanwrapper.models;

import java.util.List;

public class JsonWeek {
    List<JsonDay> jsonDays;
    JsonStartTimes times;
    String error;

    public JsonWeek(List<JsonDay> days, JsonStartTimes times, String error) {
        this.jsonDays = days;
        this.times = times;
        this.error = error;
    }

    public List<JsonDay> getDays() {
        return jsonDays;
    }

    public JsonStartTimes getTimes() {
        return times;
    }

    public String getError() {
        return error;
    }
}
