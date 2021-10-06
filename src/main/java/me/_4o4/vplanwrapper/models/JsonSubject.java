package me._4o4.vplanwrapper.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonSubject {
    @SerializedName("f")
    private final String name_full = "";

    @SerializedName("f_ori")
    private final String name_short = "";

    @SerializedName("f_a")
    private final boolean change = false;

    @SerializedName("l")
    List<JsonTeacher> teacher;

    @SerializedName("r_a")
    private final boolean room_change = false;

    @SerializedName("r")
    private final String room_name = "";

    @SerializedName("au")
    private final boolean failure = false;

    @SerializedName("ko")
    private final String info = "";


    public String getName_full() {
        return name_full;
    }

    public String getName_short() {
        return name_short;
    }

    public boolean isChange() {
        return change;
    }

    public List<JsonTeacher> getTeacher() {
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

    public String getInfo() {
        return info;
    }
}
