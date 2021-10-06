package me._4o4.vplanwrapper.api;

public class Teacher {
    String name;
    boolean failure;

    public Teacher(String name, boolean failure) {
        this.name = name;
        this.failure = failure;
    }

    public String getName() {
        return name;
    }

    public boolean isFailure() {
        return failure;
    }
}
