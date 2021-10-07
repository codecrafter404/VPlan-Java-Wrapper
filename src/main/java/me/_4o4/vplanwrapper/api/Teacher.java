package me._4o4.vplanwrapper.api;

/**
 * The teacher object used in a group
 */
public class Teacher {
    /**
     * The name of the teacher without pronoun for example for Mr. Example -> "Example"
     */
    String name;

    /**
     * has the Teacher a failure?
     */
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
