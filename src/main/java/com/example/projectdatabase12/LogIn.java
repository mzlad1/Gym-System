package com.example.projectdatabase12;

public class LogIn {
    private String username;
    private String password;
    private String userType;
    private String GID ;

    public LogIn(String username, String password, String userType ,String GID) {
        this.username = username;
        this.password = password;
        this.GID = GID;
        this.userType = userType;
    }


    public LogIn(String username, String password, String userType ) {
        this.username = username;
        this.password = password;

        this.userType = userType;
    }


    public LogIn(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public String getUserType() {
        return userType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getGID() {
        return GID;
    }

    public void setGID(String GID) {
        this.GID = GID;
    }

    @Override
    public String toString() {
        return "LogIn [username=" + username + ", password=" + password + ", userType=" + userType + "]";
    }
}
