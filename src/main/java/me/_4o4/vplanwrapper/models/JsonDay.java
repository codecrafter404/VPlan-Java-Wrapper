package me._4o4.vplanwrapper.models;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class JsonDay {
    @SerializedName("timestamp")
    private final String timestamp = "";

    @SerializedName("x")
    private final String info = "";

    @SerializedName("1")
    private List<JsonSubject> first;

    @SerializedName("2")
    private List<JsonSubject> two;

    @SerializedName("3")
    private List<JsonSubject> three;

    @SerializedName("4")
    private List<JsonSubject> fourth;

    @SerializedName("5")
    private List<JsonSubject> fifth;

    @SerializedName("6")
    private List<JsonSubject> sixth;

    @SerializedName("7")
    private List<JsonSubject> seventh;

    @SerializedName("8")
    private List<JsonSubject> eight;

    @SerializedName("err")
    private final String error = "";

    //This can be 'DATA_NOT_CHANGED'
    @SerializedName("info")
    private final String changed_info  = "";

    public List<List<JsonSubject>> getSubjects(){
        return Arrays.asList(first, two, three, fourth, fifth, sixth, seventh, eight);
    }

    public List<JsonSubject> getSubject(int x){
        List<List<JsonSubject>> subjects = getSubjects();
        if(x >= subjects.size()) return null;

        return subjects.get(x);
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getInfo() {
        return info;
    }

    public String getChanged_info() {
        return changed_info;
    }

    public String getError() {
        return error;
    }
}
