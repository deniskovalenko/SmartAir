package com.smartair.model.entity;


/**
 * Created by denis on 19.03.15.
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = DeviceModel.COLLECTION_NAME)
public class DeviceModel {
    public static final String COLLECTION_NAME = "device";
    @Id
    private String id;

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

        return id;
    }

    public void setDeviceId(String deviceId) {
        this.id = deviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
