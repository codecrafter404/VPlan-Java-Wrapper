package me._4o4.vplanwrapper.api;

import java.time.LocalTime;
import java.util.List;

/**
 * A subject object, used in the day
 */
public class Subject {
    /**
     * Full Name of the subject e.g.: Geography
     */
    String name;

    /**
     * the shorted name, e.g.: GEO
     */
    String nameShort;

    /**
     * an info like "Exam moved to 7.10.2021"
     */
    String info;

    /**
     * determines if groups.length > 1
     */
    boolean isMultiGroup;

    /**
     * The Groups in the subject(only one if the subject isn't split into multiple groups)
     */
    List<Group> groups;

    /**
     * Start time of the subject
     */
    LocalTime start;

    /**
     * 45 minutes after the start of the subject
     */
    LocalTime end;

    /**
     * The subject's index of the day e.g.: it's the first hour it will be 0
     */
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
