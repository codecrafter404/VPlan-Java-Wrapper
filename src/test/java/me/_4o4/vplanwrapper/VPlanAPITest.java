package me._4o4.vplanwrapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import me._4o4.vplanwrapper.models.RequestDate;
import me._4o4.vplanwrapper.util.LocalTimeConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class VPlanAPITest {
    @Spy
    VPlanAPI api = new VPlanAPI("foo", "bar", false);

    @Test
    void checkPassword() {
        api = spy(new VPlanAPI("foo", "bar", false));
        try{
            //Check null input
            assertThrows(IllegalArgumentException.class, () -> api.checkPassword(null));
            // Check wrong
            doReturn("{\"response\":\"WRONG\"}").when(api).downloadData(any(), any());
            assertFalse(api.checkPassword("foo"));
            // Check true
            doReturn("{\"response\":\"OK\"}").when(api).downloadData(any(), any());
            assertTrue(api.checkPassword("bar"));

        }catch (IOException e){
            fail("IO Exception");
        }
    }

    @Test
    void getWeek() {
        api = spy(new VPlanAPI("foo", "bar", false));
        try{

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(
                            LocalTime.class, new LocalTimeConverter()
                    )
                    .create();
            // Check null
            assertThrows(IllegalArgumentException.class, () -> api.getWeek(null, null));
            // Check normal
            doReturn("{\"2021-10-01\":{\"1\":[{\"f\":\"Profil/foo\",\"f_ori\":\"P:foo\",\"l\":[{\"n\":\"Example\"}],\"gr\":\"foo_I\",\"r\":\"GY1337\"},{\"f\":\"Profil/bar\",\"f_ori\":\"P:bar\",\"l\":[{\"n\":\"bar\"},{\"n\":\"bar II\"}],\"gr\":null,\"r\":\"FOO470\"},{\"f\":\"Profil/hello\",\"f_ori\":\"P:hello\",\"l\":[{\"n\":\"hello IV\"}],\"gr\":\"hello_I\",\"r\":\"BAR013\"}],\"2\":[{\"f\":\"Profil/foo\",\"f_ori\":\"P:foo\",\"l\":[{\"n\":\"Example\"}],\"gr\":\"foo_I\",\"r\":\"GY1337\"},{\"f\":\"Profil/bar\",\"f_ori\":\"P:bar\",\"l\":[{\"n\":\"bar\"},{\"n\":\"bar II\"}],\"gr\":null,\"r\":\"FOO470\"},{\"f\":\"Profil/hello\",\"f_ori\":\"P:hello\",\"l\":[{\"n\":\"hello IV\"}],\"gr\":\"hello_I\",\"r\":\"BAR013\"}],\"3\":[{\"f\":\"Informatik\",\"f_ori\":\"INF\",\"l\":[{\"n\":\"Example\"}],\"r\":\"GY1337\"},{\"f\":\"Informatik\",\"f_ori\":\"INF\",\"l\":[{\"n\":\"Example\"}],\"r\":\"HELLOTEST01\"}],\"4\":[{\"f\":\"Informatik\",\"f_ori\":\"INF\",\"l\":[{\"n\":\"Example\"}],\"r\":\"GY1337\"},{\"f\":\"Informatik\",\"f_ori\":\"INF\",\"l\":[{\"n\":\"Example\"}],\"r\":\"HELLOTEST01\"}],\"5\":[{\"f\":\"Ethik\",\"f_a\":true,\"f_ori\":\"ETH\",\"l\":[{\"n\":\"Example\"}],\"gr\":\"\",\"r\":\"A42\",\"au\":true},{\"f\":\"ev. Rel.\",\"f_ori\":\"RE/e\",\"l\":[{\"n\":\"Example\"}],\"r\":\"A1337\"}],\"6\":[{\"f\":\"Ethik\",\"f_a\":true,\"f_ori\":\"ETH\",\"l\":[{\"n\":\"Example\"}],\"gr\":\"\",\"r\":\"A42\",\"au\":true},{\"f\":\"ev. Rel.\",\"f_ori\":\"RE/e\",\"l\":[{\"n\":\"Example\"}],\"r\":\"A1337\"}],\"7\":[{\"f\":\"Geography\",\"f_a\":true,\"f_ori\":\"GEO\",\"l\":[{\"n\":\"Example\"}],\"gr\":\"\",\"r\":\"B1337\",\"ko\":\"Moved exam to 0.10.2021\",\"au\":true}],\"timestamp\":\"1337\",\"si\":[],\"x\":\"Today is A-Week, Test\"},\"stzeit\":{\"1\":\"00:00\",\"2\":\"00:00\",\"3\":\"00:00\",\"4\":\"00:00\",\"5\":\"00:00\",\"6\":\"00:00\",\"7\":\"00:00\",\"8\":\"00:00\"}}").when(api).downloadData(any(), any());
            assertEquals(
                    gson.toJson(api.getWeek(List.of(new RequestDate("2021-10-01", 0)), "0X")),
                    "{\"days\":[{\"timestamp\":\"1337\",\"requestChanged\":true,\"subjects\":[{\"name\":\"Profil\",\"nameShort\":\"P\",\"info\":\"\",\"isMultiGroup\":true,\"groups\":[{\"name\":\"Profil/foo\",\"teacher\":{\"name\":\"Example\",\"failure\":false},\"room\":\"GY1337\",\"state\":{\"change\":false,\"roomChange\":false,\"failure\":false}},{\"name\":\"Profil/bar\",\"teacher\":{\"name\":\"bar\",\"failure\":false},\"room\":\"FOO470\",\"state\":{\"change\":false,\"roomChange\":false,\"failure\":false}},{\"name\":\"Profil/hello\",\"teacher\":{\"name\":\"hello IV\",\"failure\":false},\"room\":\"BAR013\",\"state\":{\"change\":false,\"roomChange\":false,\"failure\":false}}],\"start\":\"00:00:00\",\"end\":\"00:45:00\",\"subjectIndex\":0},{\"name\":\"Profil\",\"nameShort\":\"P\",\"info\":\"\",\"isMultiGroup\":true,\"groups\":[{\"name\":\"Profil/foo\",\"teacher\":{\"name\":\"Example\",\"failure\":false},\"room\":\"GY1337\",\"state\":{\"change\":false,\"roomChange\":false,\"failure\":false}},{\"name\":\"Profil/bar\",\"teacher\":{\"name\":\"bar\",\"failure\":false},\"room\":\"FOO470\",\"state\":{\"change\":false,\"roomChange\":false,\"failure\":false}},{\"name\":\"Profil/hello\",\"teacher\":{\"name\":\"hello IV\",\"failure\":false},\"room\":\"BAR013\",\"state\":{\"change\":false,\"roomChange\":false,\"failure\":false}}],\"start\":\"00:00:00\",\"end\":\"00:45:00\",\"subjectIndex\":1},{\"name\":\"Informatik\",\"nameShort\":\"INF\",\"info\":\"\",\"isMultiGroup\":false,\"groups\":[{\"name\":\"Informatik\",\"teacher\":{\"name\":\"Example\",\"failure\":false},\"room\":\"GY1337\",\"state\":{\"change\":false,\"roomChange\":false,\"failure\":false}},{\"name\":\"Informatik\",\"teacher\":{\"name\":\"Example\",\"failure\":false},\"room\":\"HELLOTEST01\",\"state\":{\"change\":false,\"roomChange\":false,\"failure\":false}}],\"start\":\"00:00:00\",\"end\":\"00:45:00\",\"subjectIndex\":2},{\"name\":\"Informatik\",\"nameShort\":\"INF\",\"info\":\"\",\"isMultiGroup\":false,\"groups\":[{\"name\":\"Informatik\",\"teacher\":{\"name\":\"Example\",\"failure\":false},\"room\":\"GY1337\",\"state\":{\"change\":false,\"roomChange\":false,\"failure\":false}},{\"name\":\"Informatik\",\"teacher\":{\"name\":\"Example\",\"failure\":false},\"room\":\"HELLOTEST01\",\"state\":{\"change\":false,\"roomChange\":false,\"failure\":false}}],\"start\":\"00:00:00\",\"end\":\"00:45:00\",\"subjectIndex\":3},{\"name\":\"Ethik/ev. Rel.\",\"nameShort\":\"ETH/RE/e\",\"info\":\"\",\"isMultiGroup\":false,\"groups\":[{\"name\":\"Ethik\",\"teacher\":{\"name\":\"Example\",\"failure\":false},\"room\":\"A42\",\"state\":{\"change\":true,\"roomChange\":false,\"failure\":true}},{\"name\":\"ev. Rel.\",\"teacher\":{\"name\":\"Example\",\"failure\":false},\"room\":\"A1337\",\"state\":{\"change\":false,\"roomChange\":false,\"failure\":false}}],\"start\":\"00:00:00\",\"end\":\"00:45:00\",\"subjectIndex\":4},{\"name\":\"Ethik/ev. Rel.\",\"nameShort\":\"ETH/RE/e\",\"info\":\"\",\"isMultiGroup\":false,\"groups\":[{\"name\":\"Ethik\",\"teacher\":{\"name\":\"Example\",\"failure\":false},\"room\":\"A42\",\"state\":{\"change\":true,\"roomChange\":false,\"failure\":true}},{\"name\":\"ev. Rel.\",\"teacher\":{\"name\":\"Example\",\"failure\":false},\"room\":\"A1337\",\"state\":{\"change\":false,\"roomChange\":false,\"failure\":false}}],\"start\":\"00:00:00\",\"end\":\"00:45:00\",\"subjectIndex\":5},{\"name\":\"Geography\",\"nameShort\":\"GEO\",\"info\":\"Moved exam to 0.10.2021\",\"isMultiGroup\":false,\"groups\":[{\"name\":\"Geography\",\"teacher\":{\"name\":\"Example\",\"failure\":false},\"room\":\"B1337\",\"state\":{\"change\":true,\"roomChange\":false,\"failure\":true}}],\"start\":\"00:00:00\",\"end\":\"00:45:00\",\"subjectIndex\":6},null],\"error\":\"\",\"info\":\"Today is A-Week, Test\"}],\"error\":\"\",\"timeTable\":[\"00:00:00\",\"00:00:00\",\"00:00:00\",\"00:00:00\",\"00:00:00\",\"00:00:00\",\"00:00:00\",\"00:00:00\"]}"
            );
            // Check with timestamp
            doReturn("{\"2021-10-01\":{\"timestamp\":\"1337\",\"si\":[],\"info\":\"DATA_NOT_CHANGED\",\"x\":\"Today is A-Week\"}}").when(api).downloadData(any(), any());
            assertEquals(
                    gson.toJson(api.getWeek(List.of(new RequestDate("2021-10-01", 1337)), "0X")),
                    "{\"days\":[{\"timestamp\":\"1337\",\"requestChanged\":false,\"subjects\":[null,null,null,null,null,null,null,null],\"error\":\"\",\"info\":\"Today is A-Week\"}],\"error\":\"\",\"timeTable\":[]}"
            );
            // Check with week error
            doReturn("{\"error\":\"ERR_KLASSE_NOT_FOUND\"}").when(api).downloadData(any(), any());
            assertEquals(
                    gson.toJson(api.getWeek(List.of(new RequestDate("2021-10-01", 1337)), "0X")),
                    "{\"days\":[],\"error\":\"ERR_KLASSE_NOT_FOUND\"}"
            );
            // Check day error
            doReturn("{\"2021-10-01\":{\"err\":\"ERR_WEEKEND\"}}").when(api).downloadData(any(), any());
            assertEquals(
                    gson.toJson(api.getWeek(List.of(new RequestDate("2021-10-01", 1337)), "0X")),
                    "{\"days\":[{\"timestamp\":\"\",\"requestChanged\":false,\"subjects\":[],\"error\":\"ERR_WEEKEND\",\"info\":\"\"}],\"error\":\"\",\"timeTable\":[]}"
            );

        }catch (IOException e){
            fail("IO Exception");
        }
    }

}