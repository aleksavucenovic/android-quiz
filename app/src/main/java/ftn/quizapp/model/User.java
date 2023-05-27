package ftn.quizapp.model;

import android.graphics.Bitmap;

import java.util.HashMap;

public class User {

    private Integer id;
    private String username;
    private String password;
    private String email;
    private Bitmap image;
    private HashMap<String, GameStats> gameStats;

    public User(Integer id, String username, String password, String email, Bitmap image, HashMap<String, GameStats> gameStats) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.image = image;
        this.gameStats = gameStats;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public HashMap<String, GameStats> getGameStats() {
        return gameStats;
    }

    public void setGameStats(HashMap<String, GameStats> gameStats) {
        this.gameStats = gameStats;
    }
}
