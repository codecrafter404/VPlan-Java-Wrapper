package me._4o4.vplanwrapper.api;

/**
 * A group object, used in a subject
 */
public class Group {
    /**
     * Name of the Group, can same as name of subject
     */
    String name;

    /**
     * The teacher of the group
     */
    Teacher teacher;

    /**
     * The room of the group e.g.: GY1337 or BAR042
     */
    String room;

    /**
     * The state of the Group(Failure, Change, ...)
     */
    GroupState state;

    public Group(String name, Teacher teacher, String room, GroupState state) {
        this.name = name;
        this.teacher = teacher;
        this.room = room;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public String getRoom() {
        return room;
    }

    public GroupState getState() {
        return state;
    }
}
