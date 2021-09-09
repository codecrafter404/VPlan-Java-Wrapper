package me._4o4.vplanwrapper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me._4o4.vplanwrapper.models.Date;
import me._4o4.vplanwrapper.models.Day;
import me._4o4.vplanwrapper.models.StartTimes;
import me._4o4.vplanwrapper.models.Week;
import org.apache.commons.codec.digest.DigestUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class VPlanAPI {

    private final String host;
    private final String password;
    private final boolean isMD5Password;
    private final int version = 32;

    public VPlanAPI(String host, String password, boolean isMD5Password) {
        this.host = host;
        this.password = password;
        this.isMD5Password = isMD5Password;
    }

    //Fetch JSON
    private String downloadJson(String _class, List<Date> days) throws IOException {
        final String baseurl = "https://" + host + UrlPaths.GET_DAY.getPath()+ "?";
        final String args = String.format("v=%s&klasse=%s&pw=%s&data=%s", version, _class, isMD5Password ? password : DigestUtils.md5Hex(password), new Gson().toJson(days));

        // Actual Downloading
        return downloadData(new URL(baseurl + args), args.getBytes());
    }

    public boolean checkPassword(String password) throws IOException {
        final String baseUrl = String.format("https://%s?", host + UrlPaths.CHECK_PASSWORD.getPath());
        final String args = String.format("v=%s&checkpw=%s", version, DigestUtils.md5Hex(password));
        JsonObject object = new Gson().fromJson(downloadData(new  URL(baseUrl + args), args.getBytes()),JsonObject.class);
        return object.get("response").getAsString().equals("OK");
    }


    //Download Actual Data
    private String downloadData(URL paramURL, byte[] paramArrayOfbyte) throws IOException {
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection)paramURL.openConnection();
        httpsURLConnection.setRequestMethod("POST");
        httpsURLConnection.setDoInput(true);
        httpsURLConnection.setDoOutput(true);
        httpsURLConnection.setConnectTimeout(5000);
        httpsURLConnection.setReadTimeout(5000);
        httpsURLConnection.setUseCaches(false);
        httpsURLConnection.setRequestProperty("Content-Encoding", "UTF-8");
        httpsURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpsURLConnection.setRequestProperty("Content-Length", String.valueOf(paramArrayOfbyte.length));
        httpsURLConnection.connect();
        OutputStream outputStream = httpsURLConnection.getOutputStream();
        outputStream.write(paramArrayOfbyte);
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

    public Week getWeek(List<Date> days, String _class) throws IOException{
        String json = downloadJson(_class, days);
        List<Day> dates = new ArrayList<>();

        days.forEach(day ->{
            if(new Gson().fromJson(json, JsonObject.class).has(day.getDate())){
                Day d = new Gson().fromJson(
                        new Gson().fromJson(json, JsonObject.class).get(day.getDate()).getAsJsonObject().toString(),
                        Day.class);
                dates.add(d);
            }
        });

        StartTimes sTimes = null;
        if(new Gson().fromJson(json, JsonObject.class).has("stzeit")) {
            sTimes = new Gson().fromJson(
                    new Gson().fromJson(json, JsonObject.class).get("stzeit").getAsJsonObject().toString(),
                    StartTimes.class);
        }
        String error = new Gson().fromJson(json, JsonObject.class).has("error") ?
                new Gson().fromJson(json, JsonObject.class).get("error").getAsString() :
                "";

        return new Week(dates, sTimes, error);
    }
}
