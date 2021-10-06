package me._4o4.vplanwrapper.api;

import java.util.ArrayList;
import java.util.List;

public class Day {
    // UNIX-Timestamp when last changed, can be used to getting changes
    String timestamp;
    // determines that the Day has changed science last request (works only, if requested with timestamp else false)
    boolean requestChanged;
    // can contain null, if free hour e.g.: {null, null, third, fourth, fifth, sixth, seventh, null}
    List<Subject> subjects;
    // Only present if There was an error in the request(e.g.: requested plan for sunday...)
    String error;

    public Day(String timestamp, boolean requestChanged, List<Subject> subjects, String error) {
        this.timestamp = timestamp;
        this.requestChanged = requestChanged;
        this.subjects = subjects;
        this.error = error;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public boolean isRequestChanged() {
        return requestChanged;
    }

    // return the count of all subjects that aren't null; e.g.: {null, two, null, fourth, fifth, sixth, null, null} =  4
    public int getDayLength(){
        int hours = 0;
        for(Subject subject : subjects){
            if(subject != null){
                hours++;
            }
        }
        return hours;
    }

    // Return subjects but if there are free hours on end, they will get removed
    public List<Subject> getSubjects() {
        List<Subject> res = new ArrayList<>(subjects);

        for(int i = res.size() - 1; i >= 0; i -= 1){
            if(res.get(i) != null){
                break;
            }else{
                res.remove(i);
            }
        }
        return res;
    }

    public List<Subject> getSubjectsUnStripped() {
        return subjects;
    }

    public String getError() {
        return error;
    }
}
