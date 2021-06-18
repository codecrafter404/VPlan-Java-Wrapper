package models;

import com.google.gson.annotations.SerializedName;

public class Teacher {
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
