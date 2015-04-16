package com.breathe.model;

import java.util.Date;

/**
 * Created by denis on 16.04.15.
 */
public class ChartPoint {
    Date x;
//    int x;
    int y;

    public ChartPoint(Date x, int y) {
//    public ChartPoint(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public Date getX() {
        return x;
    }

    public void setX(Date x) {
        this.x = x;
    }

//
//    public int getX() {
//        return x;
//    }
//
//    public void setX(int x) {
//        this.x = x;
//    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
