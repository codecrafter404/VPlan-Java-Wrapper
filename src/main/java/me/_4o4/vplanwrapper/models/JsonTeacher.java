package me._4o4.vplanwrapper.models;

import com.google.gson.annotations.SerializedName;

public class JsonTeacher {
    @SerializedName("a")
    private boolean failure = false;

    @SerializedName("n")
    private String name = "";

    public boolean isFailure() {
        return failure;
    }

    public String getName() {
        return name;
    }
}
