/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import database.DatabaseManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
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
    public Response hoi(){
        
        return Response.ok()
                .entity("Hallo")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }
    
    @GET
    @Produces("application/json")
    @Path("/currentLocations")
    public Response getCurrentLocations(){
       
        try {
            DatabaseManager dbm = new DatabaseManager();
            RequestsManager r = new RequestsManager();
            
            
        
            return Response.ok()
                    .entity("Hallo")
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();

        } catch (SQLException ex) {
            Logger.getLogger(sluipwegwijzerApi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Response.serverError().build();
    }   
}
