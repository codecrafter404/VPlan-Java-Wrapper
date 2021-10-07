package me._4o4.vplanwrapper.api;

/**
 * The state of the group, used in Group
 */
public class GroupState {
    /**
     * has group changed?
     */
    boolean change;

    /**
     * has room changed?
     */
    boolean roomChange;

    /**
     * is failure?
     */
    boolean failure;

    public GroupState(boolean change, boolean roomChange, boolean failure) {
        this.change = change;
        this.roomChange = roomChange;
        this.failure = failure;
    }

    public boolean isChange() {
        return change;
    }

    public boolean isRoomChange() {
        return roomChange;
    }

    public boolean isFailure() {
        return failure;
    }
}
