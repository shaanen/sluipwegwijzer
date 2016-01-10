/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import domain.Blacklist;
import domain.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daan
 */
public class DatabaseManager {

    public Blacklist getBlacklist() {
        return null;
    }

    private final String URL;
    private final String USER;
    private final String PASSWORD;
    private final String DRIVER;

    private Connection con = null;
    private PreparedStatement p = null;
    private ResultSet rs = null;

    public DatabaseManager() throws SQLException {
        URL = "jdbc:mysql://46.17.2.254:3306/ics7_tracker";
        USER = "ics7";
        PASSWORD = "EfGYm8SKb6vbCNjp";
        DRIVER = "com.mysql.jdbc.Driver";
        con = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public User addUser(String name, String accessToken, String refreshToken, List<String> macs) {
        User user;
        try {
            p = con.prepareStatement("INSERT INTO USER(NAME, ACCESSTOKEN, REFRESHTOKEN) VALUES (NAME = ?, ACCESSTOKEN = ?, REFRESHTOKEN = ?)", Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            p.setString(i++, name);
            p.setString(i++, accessToken);
            p.setString(i++, refreshToken);
            p.executeQuery();
            rs = p.getGeneratedKeys();
            if (rs.next()) {
                int userId = rs.getInt(1);
                addMacs(macs, userId);
                return getUser(userId);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (p != null) {
                    p.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }

    public User getUser(int userId) {
        try {
            p = con.prepareStatement("SELECT * FROM USER WHERE USERID = ?");
            int i = 1;
            p.setInt(i++, userId);

            rs = p.executeQuery();
            if (rs.next()) {
                List<String> macs = getMacsByUserId(userId);
                return new User(userId, rs.getString(2), rs.getString(3), rs.getString(4), macs);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (p != null) {
                    p.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }

    public User getUser(String mac) {
        User user;
        return null;
        //return user;
    }

    public boolean addMacs(List<String> macs, int userId) {
        try {
            for (String mac : macs) {
                p = con.prepareStatement("INSERT INTO MAC(MAC, USERID) VALUES (mac = ?, userid = ?)");
                int i = 1;
                p.setString(i++, mac);
                p.setInt(i++, userId);
                p.executeQuery();                       
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (p != null) {
                    p.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return false;
    }

    public List<String> getMacsByUserId(int userId) {
        List<String> macs = new ArrayList<>();
        try {
            p = con.prepareStatement("SELECT * FROM MAC WHERE USERID = ?");
            int i = 1;
            p.setInt(i++, userId);
            rs = p.executeQuery();
            while (rs.next()) {
                macs.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (p != null) {
                    p.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return macs;
    }
}
