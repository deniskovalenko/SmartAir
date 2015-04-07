package com.breathe.model;

/**
 * Created by denis on 07.04.15.
 */
public class ApiDeviceModel extends DeviceModel {
    public double currentCO2;

    public double currentTemperature;

    public double currentHumidity;

    public double deltaCO2;

    public double deltaTemperature;

    public double deltaHumidity;

    public double getCurrentHumidity() {
        return currentHumidity;
    }

    public void setCurrentHumidity(double currentHumidity) {
        this.currentHumidity = currentHumidity;
    }

    public double getCurrentCO2() {
        return currentCO2;
    }

    public void setCurrentCO2(double currentCO2) {
        this.currentCO2 = currentCO2;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public double getDeltaCO2() {
        return deltaCO2;
    }

    public void setDeltaCO2(double deltaCO2) {
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
