package me._4o4.vplanwrapper.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Subject {
    @SerializedName("f")
    private String name_full = "";

    @SerializedName("f_ori")
    private String name_short = "";

    @SerializedName("f_a")
    private boolean change = false;

    @SerializedName("l")
    List<Teacher> teacher;

    @SerializedName("r_a")
    private boolean room_change = false;

    @SerializedName("r")
    private String room_name = "";

    @SerializedName("au")
    private boolean failure = false;


    public String getName_full() {
        return name_full;
    }

    public String getName_short() {
        return name_short;
    }

    public boolean isChange() {
        return change;
    }

    public List<Teacher> getTeacher() {
        return teacher;
    }

    public boolean isRoom_change() {
        return room_change;
    }

    public String getRoom_name() {
        return room_name;
    }

    public boolean isFailure() {
        return failure;
    }
}
