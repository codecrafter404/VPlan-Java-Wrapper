package me._4o4.vplanwrapper.models;

import com.google.gson.annotations.SerializedName;

public class JsonTeacher {
    @SerializedName("a")
    private final boolean failure = false;

    @SerializedName("n")
    private final String name = "";

    public boolean isFailure() {
        return failure;
    }

    public String getName() {
        return name;
    }
}
