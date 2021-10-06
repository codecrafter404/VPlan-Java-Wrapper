package me._4o4.vplanwrapper.api;

public class Group {
    String name;
    Teacher teacher;
    String room;
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
