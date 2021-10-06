package me._4o4.vplanwrapper.api;

import java.time.LocalTime;
import java.util.List;

public class Subject {
    // Full Name
    String name;
    // the shorted name, eg.: GEO -> geography
    String nameShort;

    String info;
    // determines if groups.length > 1
    boolean isMultiGroup;

    List<Group> groups;

    LocalTime start;
    LocalTime end;

    int subjectIndex;

    public Subject(String name, String nameShort, String info, boolean isMultiGroup, List<Group> groups, LocalTime start, LocalTime end, int subjectIndex) {
        this.name = name;
        this.nameShort = nameShort;
        this.info = info;
        this.isMultiGroup = isMultiGroup;
        this.groups = groups;
        this.start = start;
        this.end = end;
        this.subjectIndex = subjectIndex;
    }

    public String getName() {
        return name;
    }

    public String getNameShort() {
        return nameShort;
    }

    public String getInfo() {
        return info;
    }

    public boolean isMultiGroup() {
        return isMultiGroup;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public int getSubjectIndex() {
        return subjectIndex;
    }
}
