/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import com.google.gson.Gson;
import database.DatabaseManager;
import domain.CiscoPoint;
import domain.User;
import java.awt.Point;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import oauth.PositionManager;
import oauth.RequestsManager;

/**
 *
 * @author Daan
 */
@Path("/sluipwegwijzerApi")
public class sluipwegwijzerApi {

    @GET
    @Produces("application/json")
    @Path("/hoi")
    public Response hoi() {

        Gson gson = new Gson();
        List<CiscoPoint> points = new ArrayList<>();
        points.add(new CiscoPoint(350, 327, "TIJD ENZO"));

        return Response.ok()
                .entity(gson.toJson(points))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }

    @GET
    @Produces("application/json")
    @Path("/currentLocations")
    public Response getCurrentLocations() {

        Gson gson = new Gson();

        try {
            DatabaseManager dbm = new DatabaseManager();
            RequestsManager r = new RequestsManager();
            List<CiscoPoint> points = new ArrayList<>();
            PositionManager pman = new PositionManager();

            for (User u : dbm.getAllUsers()) {
                if (u.getAccessToken() != null && !u.getAccessToken().equals("")) {
                    CiscoPoint p = r.getHistory(u.getAccessToken());

                    if (p != null) {
                        Point fixedPoint = pman.translateToImageXY(p.getX(), p.getY());
                        p.setX(fixedPoint.getX());
                        p.setY(fixedPoint.getY());
                        p.setName(u.getName());
                        points.add(p);
                    }
                }
            }

            return Response.ok()
                    .entity(gson.toJson(points))
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();

        } catch (SQLException ex) {
            Logger.getLogger(sluipwegwijzerApi.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Response.serverError().build();
    }
}
