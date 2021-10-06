package me._4o4.vplanwrapper.util;

import java.time.LocalTime;

public class TimeUtil {

    /*
    *
    *   This method returns LocalTime
    *   from a String: e.g.: "13:45" ->
    *   Hour: 13
    *   Minute: 45
    *
    */
    public static LocalTime getLocalTime(String date){
        return LocalTime.parse(date);
    }
}
