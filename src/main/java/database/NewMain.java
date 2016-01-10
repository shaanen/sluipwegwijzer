/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import domain.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sjors
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        DatabaseManager man = new DatabaseManager();
        List<String> macs = new ArrayList<>();
        macs.add("192.168.0.1");
        macs.add("192.168.0.2");
        User user = man.addUser("Testpersoon 2", "123", "456", macs);
        System.out.println(user.getUserId());
        System.out.println(user.getName());
        System.out.println(user.getAccessToken());
        System.out.println(user.getRefreshToken());
        System.out.println(user.getMacs());
    }
    
}
