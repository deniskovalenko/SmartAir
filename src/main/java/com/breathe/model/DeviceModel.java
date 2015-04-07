package com.breathe.model;

/**
 * Created by denis on 19.03.15.
 */

public class DeviceModel {
    private String deviceId;

    private String deviceName;

    private int delay;

    private int co2MinLevel;

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getCo2MinLevel() {
        return co2MinLevel;
    }

    public void setCo2MinLevel(int co2MinLevel) {
        this.co2MinLevel = co2MinLevel;
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
}
