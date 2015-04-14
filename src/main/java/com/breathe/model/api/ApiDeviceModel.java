package com.breathe.model.api;

import com.breathe.model.DeviceModel;

/**
 * Created by denis on 07.04.15.
 */
public class ApiDeviceModel extends DeviceModel {
    public int currentCO2;

    public double currentTemperature;

    public double currentHumidity;

    public int deltaCO2;

    public double deltaTemperature;

    public double deltaHumidity;

    public double getCurrentHumidity() {
        return currentHumidity;
    }

    public void setCurrentHumidity(double currentHumidity) {
        this.currentHumidity = currentHumidity;
    }

    public int getCurrentCO2() {
        return currentCO2;
    }

    public void setCurrentCO2(int currentCO2) {
        this.currentCO2 = currentCO2;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public int getDeltaCO2() {
        return deltaCO2;
    }

    public void setDeltaCO2(int deltaCO2) {
        this.deltaCO2 = deltaCO2;
    }

    public double getDeltaTemperature() {
        return deltaTemperature;
    }

    public void setDeltaTemperature(double deltaTemperature) {
        this.deltaTemperature = deltaTemperature;
    }

    public double getDeltaHumidity() {
        return deltaHumidity;
    }

    public void setDeltaHumidity(double deltaHumidity) {
        this.deltaHumidity = deltaHumidity;
    }
}
