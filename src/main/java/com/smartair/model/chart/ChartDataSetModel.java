package com.smartair.model.chart;


import java.util.List;

/**
 * Created by denis on 15.04.15.
 */
public class ChartDataSetModel {
    String color;
    String key;
    List<ChartPoint> values;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<ChartPoint> getValues() {
        return values;
    }

    public void setValues(List<ChartPoint> values) {
        this.values = values;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
