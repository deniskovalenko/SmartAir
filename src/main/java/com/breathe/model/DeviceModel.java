package com.breathe.model;

/**
 * Created by denis on 19.03.15.
 */

public class DeviceModel {
    private String deviceId;

    private String deviceName;

    private double delay;

    private double co2MinLevel;

    public double getDelay() {
        return delay;
    }

    public void setDelay(double delay) {
        this.delay = delay;
    }

    public double getCo2MinLevel() {
        return co2MinLevel;
    }

    public void setCo2MinLevel(double co2MinLevel) {
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
