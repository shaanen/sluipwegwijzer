/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oauth;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import domain.CiscoPoint;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Daan
 */
public class RequestsManager {
    
    private HttpURLConnection connection;
    private final String baseurl;
    private final Gson gson;

    public RequestsManager() {
        baseurl = "https://tas.fhict.nl:443/api/v1";
        connection = null;
        gson = new Gson();
    }

    public CiscoPoint getCurrentLocation(String accessToken) throws MalformedURLException, IOException {
        URL url = new URL(baseurl + "/location/current");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);

        int status = connection.getResponseCode();
        System.out.println(status);

        InputStream response = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response));
        String result = bufferedReader.readLine();

        JsonObject res = gson.fromJson(result, JsonObject.class);
        double x = res.get("mapCoordinate").getAsJsonObject().get("x").getAsDouble();
        double y = res.get("mapCoordinate").getAsJsonObject().get("y").getAsDouble();

        System.out.println("X = " + x + ", " + "Y = " + y);
        System.out.println("Last located : " + res.get("statistics").getAsJsonObject().get("lastLocatedTime").getAsString());
        return new CiscoPoint(x, y, res.get("statistics").getAsJsonObject().get("lastLocatedTime").getAsString());
    }

    public String getName(String accessToken) throws MalformedURLException, IOException {
        URL url = new URL(baseurl + "/people/me");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);

        int status = connection.getResponseCode();
        System.out.println(status);

        InputStream response = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response));
        String result = bufferedReader.readLine();

        JsonObject res = gson.fromJson(result, JsonObject.class);
        return res.get("displayName").getAsString();
    }
}
