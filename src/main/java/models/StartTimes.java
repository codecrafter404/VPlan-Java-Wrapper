package models;

import com.google.gson.annotations.SerializedName;

public class StartTimes {

    @SerializedName("1")
    private String first;
    @SerializedName("2")
    private String two;
    @SerializedName("3")
    private String three;
    @SerializedName("4")
    private String fourth;
    @SerializedName("5")
    private String fifth;
    @SerializedName("6")
    private String sixth;
    @SerializedName("7")
    private String seventh;
    @SerializedName("8")
    private String eight;

    public String[] getTimes(){
        return new String[]{first, two, three, fourth, fifth, sixth, seventh, eight};
    }

    public String getTime(int x){
        String[] startTime= getTimes();
        if(x >= startTime.length) return null;
        return startTime[x];
    }
}
