package com.smartair.model.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by amira on 02.04.15.
 */
@Document(collection = UserModel.COLLECTION_NAME)
public class UserModel {
    public static final String COLLECTION_NAME = "users";

    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    private List<DeviceModel> devices;
    private String gcmToken;

    public UserModel() {}

    public String getUserId() {
        return id;
    }

    public void setUserId(String userId) {
        this.id = userId;
    }

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

    public String getGcmToken() {
        return gcmToken;
    }

    public void setGcmToken(String gcmToken) {
        this.gcmToken = gcmToken;
    }
}
