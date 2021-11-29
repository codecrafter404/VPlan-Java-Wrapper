package me._4o4.vplanwrapper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me._4o4.vplanwrapper.api.*;
import me._4o4.vplanwrapper.models.*;
import me._4o4.vplanwrapper.util.TimeUtil;
import org.apache.commons.codec.digest.DigestUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * The API class
 */
public class VPlanAPI {


    private final String host;
    private final String password;
    private final boolean isMD5Password;
    private final int version = 32;

    /**
     * @param host the host to request from without the protocol e.g.: foo.com
     * @param password the password, can raw or in md5(requires "isMD5Password" to be true)
     * @param isMD5Password if it's true the given password will be recognised as a MD5 hash
     */
    public VPlanAPI(String host, String password, boolean isMD5Password) {
        if(host == null || password == null) throw new IllegalArgumentException();

        this.host = host;
        this.password = password;
        this.isMD5Password = isMD5Password;
    }


    /**
     * This method build the url, params and request with them
     * @param _class class to download for
     * @param days days to request
     * @return The downloaded Json data
     * @throws IOException  connection issues
     */
    private String downloadJson(String _class, List<RequestDate> days) throws IOException {
        if(_class == null || days == null) throw new IllegalArgumentException();
        final String baseurl = "https://" + host + UrlPaths.GET_DAY.getPath()+ "?";
        final String args = String.format("v=%s&klasse=%s&pw=%s&data=%s", version, _class, isMD5Password ? password : DigestUtils.md5Hex(password), new Gson().toJson(days));

        // Actual Downloading
        return downloadData(new URL(baseurl + args), args.getBytes());
    }

    /**
     * This method validates a given password, the in the constructor defined is irrelevant.
     *
     * @param password the password to check
     * @return true if password is right else false
     * @throws IOException connection issues
     */
    public boolean checkPassword(String password) throws IOException {
        if(password == null) throw new IllegalArgumentException();

        final String baseUrl = String.format("https://%s?", host + UrlPaths.CHECK_PASSWORD.getPath());
        final String args = String.format("v=%s&checkpw=%s", version, DigestUtils.md5Hex(password));
        JsonObject object = new Gson().fromJson(downloadData(new  URL(baseUrl + args), args.getBytes()),JsonObject.class);
        return object.get("response").getAsString().equals("OK");
    }



    /**
     * This method was reverse engineered from the VPlan app on android
     * I wasn't able to reproduce this with postman
     *
     * @param paramURL the full URL with arguments
     * @param paramArrayByte array of bytes from the arguments contacted and without "?" e.g.: x=hello&y=foo
     * @return A json string with the data
     * @throws IOException connectivity issues
     */
    protected String downloadData(URL paramURL, byte[] paramArrayByte) throws IOException {
        if(paramURL == null || paramArrayByte == null) throw new IllegalArgumentException();

        HttpsURLConnection httpsURLConnection = (HttpsURLConnection)paramURL.openConnection();
        httpsURLConnection.setRequestMethod("POST");
        httpsURLConnection.setDoInput(true);
        httpsURLConnection.setDoOutput(true);
        httpsURLConnection.setConnectTimeout(5000);
        httpsURLConnection.setReadTimeout(5000);
        httpsURLConnection.setUseCaches(false);
        httpsURLConnection.setRequestProperty("Content-Encoding", "UTF-8");
        httpsURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpsURLConnection.setRequestProperty("Content-Length", String.valueOf(paramArrayByte.length));
        httpsURLConnection.connect();
        OutputStream outputStream = httpsURLConnection.getOutputStream();
        outputStream.write(paramArrayByte);
        outputStream.flush();
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
        while (true) {
            String str = bufferedReader.readLine();
            if (str != null) {
                stringBuilder.append(str);
                continue;
            }
            return stringBuilder.toString();
        }
    }

    /**
     * This method is used to generate a parsed object wich can be used to convert into a user-friendly object
     *
     * @param days List of days to request
     * @param _class the class for used for requesting
     * @return Parsed json, not user-friendly
     * @throws IOException when an error occurred by download
     */
    private JsonWeek getJsonWeek(List<RequestDate> days, String _class) throws IOException{
        if(days == null || _class == null) throw new IllegalArgumentException();
        String json = downloadJson(_class, days);
        System.out.println(json);
        List<JsonDay> dates = new ArrayList<>();

        days.forEach(day ->{
            if(new Gson().fromJson(json, JsonObject.class).has(day.getDate())){
                JsonDay d = new Gson().fromJson(
                        new Gson().fromJson(json, JsonObject.class).get(day.getDate()).getAsJsonObject().toString(),
                        JsonDay.class);
                dates.add(d);
            }
        });

        JsonStartTimes sTimes = null;
        if(new Gson().fromJson(json, JsonObject.class).has("stzeit")) {
            sTimes = new Gson().fromJson(
                    new Gson().fromJson(json, JsonObject.class).get("stzeit").getAsJsonObject().toString(),
                    JsonStartTimes.class);
        }
        String error = new Gson().fromJson(json, JsonObject.class).has("error") ?
                new Gson().fromJson(json, JsonObject.class).get("error").getAsString() :
                "";

        return new JsonWeek(dates, sTimes, error);
    }


    /**
     * This method is used to download data and convert it to a user-friendly Week object wich can be used in production
     *
     * @param requestedDay the days to request
     * @param schoolClass class to request for
     * @return a user-friendly week object
     * @throws IOException when error occurred by getJsonWeek()
     */
    public Week getWeek(List<RequestDate> requestedDay, String schoolClass) throws IOException {
        if(requestedDay == null || schoolClass == null) throw new IllegalArgumentException();

        JsonWeek jsonWeek = getJsonWeek(requestedDay, schoolClass);
        //Convert JsonWeek to Week
        String weekError = jsonWeek.getError();
        if (!weekError.equals("")) {
            return new Week(
                    List.of(),
                    weekError,
                    null
            );
        }

        // TimeTables
        List<LocalTime> timeTables = new ArrayList<>();


        List<Day> days = new ArrayList<>();

        for (JsonDay day : jsonWeek.getDays()) {

            String dayError = day.getError();
            if (!dayError.equals("")) {
                days.add(
                        new Day(
                                "",
                                false,
                                List.of(),
                                dayError,
                                ""
                        )
                );
                continue;
            }
            if(jsonWeek.getTimes() != null){
                for (String time : jsonWeek.getTimes().getTimes()) {
                    timeTables.add(TimeUtil.getLocalTime(time));
                }
            }

            String dayTimestamp = day.getTimestamp();
            System.out.println(day.getChanged_info());
            boolean dayRequestChanged = !day.getChanged_info().equals("DATA_NOT_CHANGED") && !day.getChanged_info().equals("");
            List<Subject> daySubjects = new ArrayList<>();
            String dayInfo = day.getInfo();


            // Subjects
            int subIndex = 0;
            for (List<JsonSubject> subject : day.getSubjects()) {
                // NUll check
                if (subject == null) {
                    daySubjects.add(null);
                    subIndex += 1;
                    continue;
                }
                String subjectName;
                String subjectNameShort;
                boolean subjectIsMultiGroup = false;
                String subjectInfo = "";
                LocalTime subjectStart;
                LocalTime subjectEnd;
                List<Group> subjectGroup = new ArrayList<>();

                // Subject Info, simple algorithm CAN'T concat Infos
                for (JsonSubject jGroup : subject) {
                    if (!jGroup.getInfo().equals("")) {
                        subjectInfo = jGroup.getInfo();
                    }
                }

                // Subject Name
                switch (subject.size()) {
                    // 'Normal' Subject
                    case 1:
                        subjectName = subject.get(0).getName_full();
                        subjectNameShort = subject.get(0).getName_short();
                        break;

                    // A subject like ethics and religions
                    case 2:
                        subjectIsMultiGroup = true;
                        if (subject.get(0).getName_full().equalsIgnoreCase(subject.get(1).getName_full())) {
                            subjectName = subject.get(0).getName_full();
                            subjectNameShort = subject.get(0).getName_short();
                        } else {
                            subjectName = subject.get(0).getName_full() + '/' + subject.get(1).getName_full();
                            subjectNameShort = subject.get(0).getName_short() + '/' + subject.get(1).getName_short();
                        }
                        break;

                    // Multi Group
                    default:
                        subjectIsMultiGroup = true;
                        // Hard Coded...
                        if (subject.get(0).getName_full().toLowerCase().startsWith("profil")) {
                            subjectName = "Profil";
                            subjectNameShort = "P";
                            break;
                        }

                        // There we go we have a (currently) unsupported subject
                        subjectName = "<Not Defined>";
                        subjectNameShort = "<Not Defined>";
                        break;
                }

                // Subject Time
                if (subIndex < timeTables.size()) {
                    subjectStart = timeTables.get(subIndex);
                    // A normal subjects goes 45 Minutes
                    subjectEnd = subjectStart.plus(45, ChronoUnit.MINUTES);
                } else {
                    subjectStart = LocalTime.parse("00:00");
                    subjectEnd = LocalTime.parse("00:00");
                }

                // Groups
                for (JsonSubject group : subject) {
                    String groupName = group.getName_full();
                    String groupRoom = group.getRoom_name();
                    Teacher groupTeacher = new Teacher(
                            group.getTeacher().get(0).getName(),
                            group.getTeacher().get(0).isFailure()
                    );
                    GroupState groupState = new GroupState(
                            group.isChange(),
                            group.isRoom_change(),
                            group.isFailure()
                    );
                    // Add Group
                    subjectGroup.add(new Group(
                            groupName,
                            groupTeacher,
                            groupRoom,
                            groupState
                    ));
                }
                daySubjects.add(
                        new Subject(
                                subjectName,
                                subjectNameShort,
                                subjectInfo,
                                subjectIsMultiGroup,
                                subjectGroup,
                                subjectStart,
                                subjectEnd,
                                subIndex
                        )
                );

                subIndex += 1;
            }
            days.add(
                    new Day(
                            dayTimestamp,
                            dayRequestChanged,
                            daySubjects,
                            dayError,
                            dayInfo
                    )
            );
        }

        return new Week(
                days,
                weekError,
                timeTables
        );
    }
}
