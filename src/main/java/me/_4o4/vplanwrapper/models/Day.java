package me._4o4.vplanwrapper.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day {
    @SerializedName("timestamp")
    private String timestamp = "";

    @SerializedName("x")
    private String info = "";

    @SerializedName("1")
    private List<Subject> first;

    @SerializedName("2")
    private List<Subject> two;

    @SerializedName("3")
    private List<Subject> three;

    @SerializedName("4")
    private List<Subject> fourth;

    @SerializedName("5")
    private List<Subject> fifth;

    @SerializedName("6")
    private List<Subject> sixth;

    @SerializedName("7")
    private List<Subject> seventh;

    @SerializedName("8")
    private List<Subject> eight;

    @SerializedName("err")
    private String error = "";

    public List<List<Subject>> getSubjects(){
        List<List<Subject>> subjects = Arrays.asList(first, two, three, fourth, fifth, sixth, seventh, eight);
        List<List<Subject>> subjects_nonNull = new ArrayList<>();
        subjects.forEach(sub ->{
            if(sub != null) subjects_nonNull.add(sub);
        });
        return subjects_nonNull;
    }

    public List<Subject> getSubject(int x){
        List<List<Subject>> subjects = getSubjects();
        if(x >= subjects.size()) return null;

        return subjects.get(x);
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getInfo() {
        return info;
    }

    public String getError() {
        return error;
    }
}
