package com.smartair.model;

import org.bson.Document;

/**
 * Created by denis on 19.03.15.
 */

public class DeviceCreateModel {
    private String deviceId;

    private String deviceName;

    private int delay;

    private int co2MaxLevel;

    private String userId;

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getCo2MaxLevel() {
        return co2MaxLevel;
    }

    public void setCo2MaxLevel(int co2MaxLevel) {
        this.co2MaxLevel = co2MaxLevel;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceId() {

        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
