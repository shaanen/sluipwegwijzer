/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import domain.Blacklist;

/**
 *
 * @author Daan
 */
public class DatabaseManager {
    
    public Blacklist getBlacklist(){
        return null;
    }
    
    private String URL;
    private String USER;
    private String PASSWORD;
    
    public DatabaseManager()
    {
        URL = "46.17.2.254:3306";
        USER = "ics7";
        PASSWORD = "EfGYm8SKb6vbCNjp";
    }
    
}
