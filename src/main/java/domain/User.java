/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.List;

/**
 *
 * @author Daan
 */
public class User {
 
    private final int userId;
    private final String name;
    private String accessToken;
    private String refreshToken;
    private List<User> friends;

    public User(int userId, String name, String accessToken, String refreshToken) {
        this.userId = userId;
        this.name = name;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    
    public void addFriend(User friend){
        friends.add(friend);
    }
}
