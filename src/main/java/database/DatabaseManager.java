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

    public DatabaseManager() throws SQLException {
        URL = "jdbc:mysql://46.17.2.254:3306/ics7_tracker";
        USER = "ics7";
        PASSWORD = "EfGYm8SKb6vbCNjp";
        DRIVER = "com.mysql.jdbc.Driver";
        con = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public User addUser(String name, String accessToken, String refreshToken, List<String> macs) {
        ResultSet rs = null;
        PreparedStatement p = null;
        User user = null;
        try {
            p = con.prepareStatement("INSERT INTO PERSON(NAME, ACCESSTOKEN, REFRESHTOKEN) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            p.setString(i++, name);
            p.setString(i++, accessToken);
            p.setString(i++, refreshToken);
            p.executeUpdate();
            rs = p.getGeneratedKeys();
            if (rs.next()) {
                int userId = rs.getInt(1);
                addMacs(macs, userId);
                user = getUser(userId);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            };
            try {
                if (p != null) {
                    p.close();
                }
            } catch (Exception e) {
            };
        }
        return user;
    }

    public boolean addUser(User user) {
        ResultSet rs = null;
        PreparedStatement p = null;
        try {
            p = con.prepareStatement("INSERT INTO PERSON(NAME, ACCESSTOKEN, REFRESHTOKEN) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            p.setString(i++, user.getName());
            p.setString(i++, user.getAccessToken());
            p.setString(i++, user.getRefreshToken());
            p.executeUpdate();
            rs = p.getGeneratedKeys();
            if (rs.next()) {
                int userId = rs.getInt(1);
                if (addMacs(user.getMacs(), userId)) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            };
            try {
                if (p != null) {
                    p.close();
                }
            } catch (Exception e) {
            };
        }
        return false;
    }

    public User getUser(int userId) {
        ResultSet rs = null;
        PreparedStatement p = null;
        User user = null;
        try {
            p = con.prepareStatement("SELECT * FROM PERSON WHERE USERID = ?");
            int i = 1;
            p.setInt(i++, userId);

            rs = p.executeQuery();
            if (rs.next()) {
                List<String> macs = getMacsByUserId(userId);
                user = new User(userId, rs.getString(2), rs.getString(3), rs.getString(4), macs);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            };
            try {
                if (p != null) {
                    p.close();
                }
            } catch (Exception e) {
            };
        }
        return user;
    }

    public List<User> getAllUsers() {
        ResultSet rs = null;
        PreparedStatement p = null;
        List<User> users = new ArrayList<>();
        try {
            p = con.prepareStatement("SELECT * FROM PERSON");
            rs = p.executeQuery();
            while (rs.next()) {
                users.add(getUser(rs.getInt(1)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            };
            try {
                if (p != null) {
                    p.close();
                }
            } catch (Exception e) {
            };
        }
        return users;
    }

    public User getUserByMac(String mac) {
        User user;
        return null;
        //return user;
    }

    public boolean addMacs(List<String> macs, int userId) {
        ResultSet rs = null;
        PreparedStatement p = null;
        for (String mac : macs) {
            try {
                p = con.prepareStatement("INSERT INTO MAC(MAC, USERID) VALUES (?,?)");
                int i = 1;
                p.setString(i++, mac);
                p.setInt(i++, userId);
                p.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return false;
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception e) {
                };
                try {
                    if (p != null) {
                        p.close();
                    }
                } catch (Exception e) {
                };
            }
        }
        return true;
    }

    public List<String> getMacsByUserId(int userId) {
        ResultSet rs = null;
        PreparedStatement p = null;
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
            } catch (Exception e) {
            };
            try {
                if (p != null) {
                    p.close();
                }
            } catch (Exception e) {
            };
        }
        return macs;
    }

    public boolean addUserToBlacklist(int userId) {
        ResultSet rs = null;
        PreparedStatement p = null;
        try {
            p = con.prepareStatement("INSERT INTO BLACKLIST(USERID) VALUES (?)");
            int i = 1;
            p.setInt(i++, userId);
            p.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            };
            try {
                if (p != null) {
                    p.close();
                }
            } catch (Exception e) {
            };
        }
        return false;
    }

    public boolean removeUserFromBlacklist(int userId) {
        PreparedStatement p = null;
        try {
            p = con.prepareStatement("DELETE FROM BLACKLIST WHERE USERID = (?)");
            int i = 1;
            p.setInt(i++, userId);
            p.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (p != null) {
                    p.close();
                }
            } catch (Exception e) {
            };
        }
        return false;
    }

    public List<Integer> getUserIdsInBlacklist() {
        ResultSet rs = null;
        PreparedStatement p = null;
        List<Integer> blacklist = new ArrayList<>();
        List<String> macs = new ArrayList<>();
        try {
            p = con.prepareStatement("SELECT * FROM BLACKLIST");
            rs = p.executeQuery();
            while (rs.next()) {
                blacklist.add(rs.getInt(2));
            }
            return blacklist;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            };
            try {
                if (p != null) {
                    p.close();
                }
            } catch (Exception e) {
            };
        }
        return null;
    }

    public boolean updateUser(User user) {
        PreparedStatement p = null;
        try {
            p = con.prepareStatement("UPDATE PERSON SET ACCESSTOKEN = (?), REFRESHTOKEN = (?) WHERE USERID = (?)");
            int i = 1;
            p.setString(i++, user.getAccessToken());
            p.setString(i++, user.getRefreshToken());
            p.setInt(i++, user.getUserId());
            p.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (p != null) {
                    p.close();
                }
            } catch (Exception e) {
            };
        }
        return false;
    }

}
