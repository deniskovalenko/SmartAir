package com.breathe.model;

import java.util.List;

/**
 * Created by amira on 02.04.15.
 */
public class UserModel {
    private String username;
    private String email;
    private String password;
    private List<DeviceModel> devices;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<DeviceModel> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceModel> devices) {
        this.devices = devices;
    }
}
